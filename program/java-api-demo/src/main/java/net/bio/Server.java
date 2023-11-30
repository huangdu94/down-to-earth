package net.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 */
public class Server {
    private final ServerSocket serverSocket;
    private final ExecutorService clientHandlerPool;
    private final ExecutorService serverHandler;
    private volatile boolean stop = false;

    public Server() {
        try {
            this.serverSocket = new ServerSocket(8088);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.clientHandlerPool = new ThreadPoolExecutor(10, 100, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100), new ThreadFactoryBuilder().setNameFormat("client-handler-%d").build()) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("已连接.");
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("已断开.");
            }
        };
        this.serverHandler = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new SynchronousQueue<>(), new ThreadFactoryBuilder().setNameFormat("server").build());
        serverHandler.submit(this::waitConnect);
    }

    public void waitConnect() {
        while (!stop) {
            try {
                clientHandlerPool.submit(new ClientHandler(serverSocket.accept()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            while (!socket.isClosed()) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void stop() {
        this.stop = true;
        serverHandler.shutdown();
        clientHandlerPool.shutdown();
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
