package net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.Executors;

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
            this.socketChannel = SocketChannel.open(new InetSocketAddress(port));
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.readBuffer = ByteBuffer.allocate(1024);
        this.writeBuffer = ByteBuffer.allocate(1024);
        Executors.newSingleThreadExecutor().submit(this::run);
    }

    public void run() {
        try {
            while (selector.select() > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    if (selectionKey.isReadable()) {
                        System.out.println(read());
                    }
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void write(String message) {
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

    public String read() {
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
