package net.aio;

import net.aio.handler.BufferPair;
import net.aio.handler.ClientConnectHandler;

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
        client.connect(new InetSocketAddress("127.0.0.1", 8888), client, new ClientConnectHandler());
        // 等待连接处理完毕
        Thread.sleep(1000);
        // 关闭输入流和Socket通道
        client.shutdownInput();
        client.close();
    }
}
