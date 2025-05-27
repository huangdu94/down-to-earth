package net.aio.handler;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * ServerWriteHandler
 *
 * @author huangdu
 * @version 2025/5/28
 */
public class ClientWriteHandler implements CompletionHandler<Integer, AsynchronousSocketChannel> {
    private final BufferPair bufferPair;

    public ClientWriteHandler(BufferPair bufferPair) {
        this.bufferPair = bufferPair;
    }

    @Override
    public void completed(Integer result, AsynchronousSocketChannel attachment) {
        ByteBuffer readBuffer = bufferPair.getReadBuffer();
        readBuffer.clear();
        // 异步读取服务器发送的消息
        attachment.read(readBuffer, attachment, new ClientReadHandler(bufferPair));
    }

    @Override
    public void failed(Throwable exc, AsynchronousSocketChannel attachment) {
        System.out.println("client write error:" + exc.getMessage());
        try {
            attachment.close();
        } catch (IOException e) {
            System.out.println(exc.getMessage());
        }
    }
}
