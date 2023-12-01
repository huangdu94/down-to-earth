package net.bio.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

/**
 * 聊天室客户端
 *
 * @author Bean
 */
public class Client {
    /**
     * java.net.Socket
     * 封装了TCP协议，使用它可以基于TCP协议与远端计算机的程序交互
     */
    private final Socket socket;
    private final Scanner scan = new Scanner(System.in);
    private final ExecutorService serverHandler;

    /**
     * 构造方法，用来初始化客户端
     */
    public Client() {
        /*
         * 实例化Socket的使用通常传入两个参数：
         * 1：远端计算机的地址信息(IP)
         * 2：端口号 通过端口号可以找到运行在远端计算机中需要通讯的应用程序.
         * 实例化Socket的过程就是发起连接的过程.若服务端没有响应，这里会抛出异常
         */
        String ip = "114.214.186.154";
        int port = 8088;
        System.out.println("客户端准备连接IP为：" + ip + "，端口号为：" + port + "的服务端.");
        try {
            socket = new Socket(ip, port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.serverHandler = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new SynchronousQueue<>(), new ThreadFactoryBuilder().setNameFormat("server").build());
        System.out.println("客户端连接成功.");
    }

    /**
     * 客户端开始工作的方法
     */
    public void start() throws IOException {
        /*
         * 若希望向服务端发送数据，需要通过Socket获取输出流，通过该流写出的数据就被发送到了服务端.
         *
         * Socket提供的方法：
         * OutputStream getOutputStream()
         */
        System.out.println("为客户端创建流");
        OutputStream out = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(out, StandardCharsets.UTF_8);
        PrintWriter pw = new PrintWriter(osw, true);
        System.out.println("客户端流创建完毕，等待用户输入：");
        serverHandler.submit(new ServerHandler());
        while (true) {
            String input = scan.nextLine();
            pw.println(input);
        }
    }

    private class ServerHandler implements Runnable {
        /**
         * 若希望可以随时收到服务端转发过来的消息，那么就不能让读取服务端消息的代码与给服务端发送消息的代码同步操作.否则互相会受影响.
         * 解决方法：将循环读取服务端发送过来的消息并输出到控制台的代码放到另一个线程中运行.
         */
        @Override
        public void run() {
            try {
                //建立好输入流
                InputStream in = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8);
                final BufferedReader br = new BufferedReader(isr);
                //读取服务端发送过来的数据，并输出到控制台
                String message;
                while ((message = br.readLine()) != null) {
                    System.out.println(message);
                }
                System.out.println("服务端已经关闭.");
            } catch (IOException ignored) {
            }
        }
    }

    public static void main(String[] args) {
        try {
            Client client = new Client();
            client.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
