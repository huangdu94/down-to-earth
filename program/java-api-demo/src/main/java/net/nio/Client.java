package net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
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
public class Client {
    private final Selector selector;
    private final SocketChannel socketChannel;
    private final ByteBuffer readBuffer;
    private final ByteBuffer writeBuffer;

    public Client(int port) {
        try {
            this.selector = Selector.open();
            this.socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress(port));
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            readBuffer = ByteBuffer.allocate(1024);
            writeBuffer = ByteBuffer.allocate(1024);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ExecutorService clientHandler = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new SynchronousQueue<>(), new ThreadFactoryBuilder().setNameFormat("client").build());
        clientHandler.submit(this::run);
    }

    public void run() {
        try {
            while (selector.select() > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    if (selectionKey.isConnectable()) {
                        SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
                        if (socketChannel.finishConnect()) {
                            System.out.println("client: 连接成功.");
                            socketChannel.register(selector, SelectionKey.OP_READ);
                        } else {
                            System.out.println("client: 连接失败.");
                        }
                    } else if (selectionKey.isReadable()) {
                        System.out.println("client read: " + read());
                    }
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void write(String message) {
        while (!socketChannel.isConnected()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        writeBuffer.put(message.getBytes());
        writeBuffer.flip();
        try {
            socketChannel.write(writeBuffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            writeBuffer.clear();
        }
    }

    private String read() {
        try {
            socketChannel.read(readBuffer);
            readBuffer.flip();
            return new String(readBuffer.array(), 0, readBuffer.limit());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            readBuffer.clear();
        }
    }
}
