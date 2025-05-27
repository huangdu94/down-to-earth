package net.aio.handler;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * ServerReadHandler
 *
 * @author huangdu
 * @version 2025/5/28
 */
public class ServerReadHandler implements CompletionHandler<Integer, AsynchronousSocketChannel> {
    private final BufferPair bufferPair;

    public ServerReadHandler(BufferPair bufferPair) {
        this.bufferPair = bufferPair;
    }

    @Override
    public void completed(Integer len, AsynchronousSocketChannel attachment) {
        ByteBuffer readBuffer = bufferPair.getReadBuffer();
        readBuffer.flip();
        String message = new String(readBuffer.array(), 0, len);
        readBuffer.clear();
        System.out.println("收到客户端消息：" + message);
        // 异步发送消息给客户端
        ByteBuffer writeBuffer = bufferPair.getWriteBuffer();
        writeBuffer.put(message.getBytes());
        writeBuffer.flip();
        attachment.write(bufferPair.getWriteBuffer(), attachment, new ServerWriteHandler(bufferPair));
        // 继续读
        attachment.read(readBuffer, attachment, this);
    }

    @Override
    public void failed(Throwable exc, AsynchronousSocketChannel attachment) {
        System.out.println("server read error:" + exc.getMessage());
        try {
            attachment.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
