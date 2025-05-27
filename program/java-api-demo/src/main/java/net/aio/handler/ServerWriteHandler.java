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
public class ServerWriteHandler implements CompletionHandler<Integer, AsynchronousSocketChannel> {
    private final BufferPair bufferPair;

    public ServerWriteHandler(BufferPair bufferPair) {
        this.bufferPair = bufferPair;
    }

    @Override
    public void completed(Integer result, AsynchronousSocketChannel attachment) {
        try {
            this.bufferPair.getWriteBuffer().clear();
            // 关闭输出流
            attachment.shutdownOutput();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void failed(Throwable exc, AsynchronousSocketChannel attachment) {
        System.out.println("server write error:" + exc.getMessage());
        try {
            attachment.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
