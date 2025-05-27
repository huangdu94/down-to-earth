package net.aio.handler;


import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * ServerAcceptHandler
 *
 * @author huangdu
 * @version 2025/5/28
 */
public class ServerAcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AsynchronousServerSocketChannel> {
    @Override
    public void completed(AsynchronousSocketChannel channel, AsynchronousServerSocketChannel attachment) {
        System.out.println("客户端连接成功");
        try {
            // 每一个连接应该建一个独立的读缓冲区，和一个独立的写缓冲区
            BufferPair bufferPair = new BufferPair();
            // 异步读取客户端发送的消息
            channel.read(bufferPair.getReadBuffer(), channel, new ServerReadHandler(bufferPair));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // 继续异步等待接收客户端连接
        attachment.accept(attachment, this);
    }

    @Override
    public void failed(Throwable exc, AsynchronousServerSocketChannel attachment) {
        System.out.println("server accept error: " + exc.getMessage());
        // 继续异步等待接收客户端连接
        attachment.accept(attachment, this);
    }
}
