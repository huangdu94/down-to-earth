package net.bio.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

/**
 * 聊天室服务端
 *
 * @author Bean
 */
public class Server {
    /**
     * 运行在服务端的java.net.ServerSocket
     * 主要有两个作用：
     * 1：向操作系统申请服务端口，客户端就是通过这个端口与服务端程序建立连接的.
     * 2：监听该服务端口，一旦一个客户端通过该端口尝试建立连接，ServerSocket就会实例化一个Socket与客户端进行通讯.
     */
    private final ServerSocket serverSocket;
    /**
     * 该数组用于存储所有客户端的输出流，做广播操作
     */
    private final Collection<PrintWriter> allOut;
    private final ExecutorService clientHandlerPool;

    /**
     * 无参构造 创建端口号8088等待服务器连接
     */
    public Server() throws IOException {
        // 1.实例化ServerSocket的同时，向系统申请服务端口.若当前操作系统的其他应用程序占用了这里申请的端口时，会抛出：Address already in use
        System.out.println("准备建立服务器，端口号8088.");
        this.serverSocket = new ServerSocket(8088);
        this.allOut = new ConcurrentLinkedQueue<>();
        this.clientHandlerPool = new ThreadPoolExecutor(10, 100, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100), new ThreadFactoryBuilder().setNameFormat("client-handler-%d").build()) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("一个客户端成功连接.");
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("一个客户端成功断开.");
            }
        };
        System.out.println("服务器建立完毕，等待客户端连接.");
    }

    /**
     * 用于服务器连接，与线程的创建
     */
    public void start() {
        /*
         * ServerSocket提供的方法：
         * Socket accept()
         * 该方法是一个阻塞方法，调用后程序就 阻塞了("卡住了")，监听服务端口，等待客户端的连接，一旦一个客户端建立了连接，那么该方法会返回一个Socket与该客户端通讯.
         */
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                // 启动一个线程来处理该客户端的交互
                clientHandlerPool.submit(new ClientHandler(socket));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 该线程负责并发运行处理指定客户端的数据交互
     *
     * @author Bean
     */
    private class ClientHandler implements Runnable {
        private final Socket socket;
        /**
         * 客户端地址信息
         */
        private final String host;

        public ClientHandler(Socket socket) {
            this.socket = socket;
            InetAddress address = socket.getInetAddress();
            host = address.getHostName();
        }

        @Override
        public void run() {
            PrintWriter pw = null;
            try {
                /*
                 * Socket提供的方法：
                 * InputStream getInputStream()
                 * 通过该输入流读取的字节就是远端计算机发送过来的字节.对于服务端这边而言，远端指的就是客户端.
                 */
                System.out.println("服务器为客户端创建流.");
                InputStream in = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
                // 建立好输出流
                OutputStream out = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                pw = new PrintWriter(osw, true);
                System.out.println("流创建完毕，等待接收客户端信息......");
                // 将当前ClientHandler所对应的客户端的输出流存入到共享集合中，便于其他ClientHandler使用.
                allOut.add(pw);
                System.out.println("当前在线人数：" + allOut.size());
                String message;
                while ((message = br.readLine()) != null) {
                    //读取客户端发送过来的数据，并输出到控制台
                    System.out.println(host + "说：" + message);
                    System.out.println("服务端广播该消息......");
                    synchronized (allOut) {
                        for (PrintWriter p : allOut) {
                            p.println(host + "说：" + message);
                        }
                    }
                }
            } catch (IOException ignored) {
            } finally {
                //处理客户端断开连接的操作
                System.out.println("一个客户端断开了.");
                allOut.remove(pw);
                System.out.println("当前在线人数：" + allOut.size());
                //将当前客户端的输出流从共享数组中删除
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            Server server = new Server();
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
