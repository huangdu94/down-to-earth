package net.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 */
public class Server {
    public static void main(String[] args) throws Exception {
        // 创建异步通道组，处理IO事件
        AsynchronousChannelGroup group = AsynchronousChannelGroup.withFixedThreadPool(10, Executors.defaultThreadFactory());
        // 创建异步服务器Socket通道，并绑定端口
        AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open(group).bind(new InetSocketAddress(8888));
        System.out.println("=============AIO服务端启动=========");
        // 异步等待接收客户端连接
        server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            // 创建ByteBuffer
            final ByteBuffer buffer = ByteBuffer.allocate(1024);

            @Override
            public void completed(AsynchronousSocketChannel channel, Object attachment) {
                System.out.println("客户端连接成功");
                try {
                    buffer.clear();
                    // 异步读取客户端发送的消息
                    channel.read(buffer, null, new CompletionHandler<Integer, Object>() {
                        @Override
                        public void completed(Integer len, Object attachment) {
                            buffer.flip();
                            String message = new String(buffer.array(), 0, len);
                            System.out.println("收到客户端消息：" + message);

                            // 异步发送消息给客户端
                            channel.write(ByteBuffer.wrap(("你好，阿坤！").getBytes()), null, new CompletionHandler<Integer, Object>() {
                                @Override
                                public void completed(Integer result, Object attachment) {
                                    // 关闭输出流
                                    try {
                                        channel.shutdownOutput();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void failed(Throwable exc, Object attachment) {
                                    exc.printStackTrace();
                                    try {
                                        channel.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }

                        @Override
                        public void failed(Throwable exc, Object attachment) {
                            exc.printStackTrace();
                            try {
                                channel.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 继续异步等待接收客户端连接
                server.accept(null, this);
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                exc.printStackTrace();
                // 继续异步等待接收客户端连接
                server.accept(null, this);
            }
        });
        // 等待所有连接都处理完毕
        group.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
    }
}
