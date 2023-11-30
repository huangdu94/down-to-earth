package net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 */
public class Server {
    private final Selector selector;
    private final ServerSocketChannel serverSocketChannel;
    private final ExecutorService serverHandler;

    public Server(int port) {
        try {
            this.selector = Selector.open();
            this.serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(port));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.serverHandler = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new SynchronousQueue<>(), new ThreadFactoryBuilder().setNameFormat("server").build());
        serverHandler.submit(this::run);
    }

    public void run() {
        try {
            while (selector.select() > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    if (selectionKey.isAcceptable()) {
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    } else if (selectionKey.isReadable()) {
                        SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
                        ByteBuffer buffer = (ByteBuffer)selectionKey.attachment();
                        socketChannel.configureBlocking(false);
                        socketChannel.read(buffer);
                        socketChannel.register(selector, SelectionKey.OP_WRITE, buffer);
                    } else {
                        SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
                        ByteBuffer buffer = (ByteBuffer)selectionKey.attachment();
                        socketChannel.configureBlocking(false);
                        buffer.flip();
                        socketChannel.write(buffer);
                        buffer.clear();
                        socketChannel.register(selector, SelectionKey.OP_READ, buffer);
                    }
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void stop() {
        serverHandler.shutdown();
        try {
            serverSocketChannel.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}