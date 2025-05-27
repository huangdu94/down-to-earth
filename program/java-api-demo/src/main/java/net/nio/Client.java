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
    private volatile boolean stop = false;

    public Client(int port) {
        this("127.0.0.1", port);
    }

    public Client(String host, int port) {
        try {
            this.selector = Selector.open();
            this.socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            doConnect(new InetSocketAddress(host, port));
            readBuffer = ByteBuffer.allocate(1024);
            writeBuffer = ByteBuffer.allocate(1024);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        new Thread(this::run, "client").start();
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
            selector.close();
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

    private void doConnect(InetSocketAddress inetSocketAddress) throws IOException {
        if (socketChannel.connect(inetSocketAddress)) {
            socketChannel.register(selector, SelectionKey.OP_READ);
        } else {
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }
    }

    private void handleInput(SelectionKey selectionKey) throws IOException {
        if (!selectionKey.isValid()) {
            return;
        }
        if (selectionKey.isConnectable()) {
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            if (socketChannel.finishConnect()) {
                System.out.println("client: 连接成功.");
                socketChannel.register(selector, SelectionKey.OP_READ);
            } else {
                System.out.println("client: 连接失败.");
                // 连接失败，进程退出
                System.exit(1);
            }
        } else if (selectionKey.isReadable()) {
            String message = read();
            if (message == null) {
                System.out.println("server: 断开连接.");
                selectionKey.cancel();
                System.exit(1);
            }
            System.out.println("client read: " + message);
        }
    }

    private String read() {
        try {
            int readBytes = socketChannel.read(readBuffer);
            if (readBytes > 0) {
                readBuffer.flip();
                return new String(readBuffer.array(), 0, readBuffer.limit());
            } else if (readBytes < 0) {
                socketChannel.close();
                return null;
            } else {
                return "";
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            readBuffer.clear();
        }
    }
}
