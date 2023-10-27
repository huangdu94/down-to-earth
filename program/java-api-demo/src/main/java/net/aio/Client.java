package net.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 */
public class Client {
    public static void main(String[] args) throws Exception {
        // 创建异步Socket通道
        AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
        // 异步连接服务器
        client.connect(new InetSocketAddress("127.0.0.1", 8888), null, new CompletionHandler<Void, Object>() {
            // 创建ByteBuffer
            final ByteBuffer buffer = ByteBuffer.wrap(("你好，靓仔！").getBytes());

            @Override
            public void completed(Void result, Object attachment) {
                // 异步发送消息给服务器
                client.write(buffer, null, new CompletionHandler<Integer, Object>() {
                    // 创建ByteBuffer
                    final ByteBuffer readBuffer = ByteBuffer.allocate(1024);

                    @Override
                    public void completed(Integer result, Object attachment) {
                        readBuffer.clear();
                        // 异步读取服务器发送的消息
                        client.read(readBuffer, null, new CompletionHandler<Integer, Object>() {
                            @Override
                            public void completed(Integer result, Object attachment) {
                                readBuffer.flip();
                                String msg = new String(readBuffer.array(), 0, result);
                                System.out.println("收到服务端消息：" + msg);
                            }

                            @Override
                            public void failed(Throwable exc, Object attachment) {
                                exc.printStackTrace();
                                try {
                                    client.close();
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
                            client.close();
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
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        // 等待连接处理完毕
        Thread.sleep(1000);
        // 关闭输入流和Socket通道
        client.shutdownInput();
        client.close();
    }
}
