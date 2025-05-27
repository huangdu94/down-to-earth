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
    private volatile boolean stop = false;

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
        new Thread(this::run, "server").start();
    }

    public void run() {
        try {
            while (!stop && selector.select() > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();
                    try {
                        handleInput(selectionKey);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            stop();
        }
    }

    public void stop() {
        this.stop = true;
        try {
            // 多路复用器关闭后，所有注册在上面的Channel和Pipe等资源都会被自动去注册并关闭，所以不需要重复释放资源
            selector.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleInput(SelectionKey selectionKey) throws IOException {
        if (!selectionKey.isValid()) {
            return;
        }
        if (selectionKey.isAcceptable()) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
        } else if (selectionKey.isReadable()) {
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
            // 返回读到的字节数量
            int readBytes = socketChannel.read(buffer);
            if (readBytes > 0) {
                socketChannel.register(selector, SelectionKey.OP_WRITE, buffer);
            } else if (readBytes < 0) {
                // 连接已关闭
                selectionKey.channel();
                socketChannel.close();
            }
        } else {
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();
            socketChannel.register(selector, SelectionKey.OP_READ, buffer);
        }
    }
}