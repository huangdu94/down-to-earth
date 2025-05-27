package net.aio;

import net.aio.handler.ServerAcceptHandler;

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
    private static final Server SERVER = new Server();

    private Server() {
    }

    public static void main(String[] args) throws Exception {
        // 创建异步通道组，处理IO事件
        AsynchronousChannelGroup group = AsynchronousChannelGroup.withFixedThreadPool(10, Executors.defaultThreadFactory());
        // 创建异步服务器Socket通道，并绑定端口
        AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open(group).bind(new InetSocketAddress(8888));
        System.out.println("=============AIO服务端启动=========");
        // 异步等待接收客户端连接
        server.accept(server, new ServerAcceptHandler());
        // 等待所有连接都处理完毕
        boolean success = group.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        System.out.println(success);
    }
}
