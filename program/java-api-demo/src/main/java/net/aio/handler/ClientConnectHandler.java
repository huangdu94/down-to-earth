package net.aio.handler;

import net.aio.Client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * ClientConnectHandler
 *
 * @author huangdu
 * @version 2025/5/28
 */
public class ClientConnectHandler implements CompletionHandler<Void, AsynchronousSocketChannel> {
    @Override
    public void completed(Void result, AsynchronousSocketChannel attachment) {
        BufferPair bufferPair = new BufferPair();
        ByteBuffer writeBuffer = bufferPair.getWriteBuffer();
        writeBuffer.put("client connect success.".getBytes());
        writeBuffer.flip();
        // 异步发送消息给服务器
        attachment.write(writeBuffer, attachment, new ClientWriteHandler(bufferPair));
    }

    @Override
    public void failed(Throwable exc, AsynchronousSocketChannel attachment) {
        System.out.println("client connect error:" + exc.getMessage());
        try {
            attachment.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
