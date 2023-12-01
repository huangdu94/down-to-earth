package net.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 */
public class NettyServer {

    public static void main(String[] args) {
        // 创建两个线程组bossGroup和workerGroup, 含有的子线程NioEventLoop的个数默认为cpu核数的两倍
        // bossGroup只是处理连接请求 ,真正的和客户端业务处理，会交给workerGroup完成
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(8);
        try {
            // 创建服务器端的启动对象
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // 使用链式编程来配置参数
            serverBootstrap
                // 设置两个线程组
                .group(bossGroup, workerGroup)
                // 使用NioServerSocketChannel作为服务器的通道实现
                .channel(NioServerSocketChannel.class)
                // 初始化服务器连接队列大小，服务端处理客户端连接请求是顺序处理的,所以同一时间只能处理一个客户端连接。
                // 多个客户端同时来的时候,服务端将不能处理的客户端连接请求放在队列中等待处理
                .option(ChannelOption.SO_BACKLOG, 64)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        // 对workerGroup的SocketChannel设置处理器
                        ch.pipeline().addLast(new NettyServerHandler());
                    }
                });
            System.out.println("服务器启动...");
            // 绑定一个端口并且同步, 生成了一个ChannelFuture异步对象，通过isDone()等方法可以判断异步事件的执行情况
            // 启动服务器(并绑定端口)，bind是异步操作，sync方法是等待异步操作执行完毕
            ChannelFuture cf = serverBootstrap.bind(6666).sync();
            // 给cf注册监听器，监听我们关心的事件
            cf.addListener((ChannelFutureListener)cf1 -> {
                if (cf1.isSuccess()) {
                    System.out.println("绑定端口成功.");
                } else {
                    System.out.println("绑定端口失败.");
                }
            });
            // 等待服务端监听端口关闭，closeFuture是异步操作
            // 通过sync方法同步等待通道关闭处理完毕，这里会阻塞等待通道关闭完成，内部调用的是Object的wait()方法
            cf.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
