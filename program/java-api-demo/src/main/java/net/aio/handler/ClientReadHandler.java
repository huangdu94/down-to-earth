package net.aio.handler;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * ClientReadHandler
 *
 * @author huangdu
 * @version 2025/5/28
 */
public class ClientReadHandler implements CompletionHandler<Integer, AsynchronousSocketChannel> {
    private final BufferPair bufferPair;

    public ClientReadHandler(BufferPair bufferPair) {
        this.bufferPair = bufferPair;
    }

    @Override
    public void completed(Integer result, AsynchronousSocketChannel attachment) {
        ByteBuffer readBuffer = bufferPair.getReadBuffer();
        readBuffer.flip();
        String msg = new String(readBuffer.array(), 0, result);
        System.out.println("收到服务端消息：" + msg);
    }

    @Override
    public void failed(Throwable exc, AsynchronousSocketChannel attachment) {
        System.out.println("client read error:" + exc.getMessage());
        try {
            attachment.close();
        } catch (IOException e) {
            System.out.println(exc.getMessage());
        }
    }
}
