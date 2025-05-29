package udp;

import io.netty.channel.local.LocalAddress;

import java.io.IOException;
import java.net.*;
/**
 * UdpDemo
 *
 * @author huangdu
 * @version 2025/5/29
 */
public class UdpDemo {
    static class Server {
        public void run() {
            try (DatagramSocket ds = new DatagramSocket(6666, InetAddress.getLocalHost())) {
                byte[] bytes = "hello world!".getBytes();
                DatagramPacket p = new DatagramPacket(bytes, bytes.length);
                p.setAddress(InetAddress.getLocalHost());
                p.setPort(8088);
                ds.send(p);
                System.out.println("SERVER--------------------");
                System.out.println("address: " + p.getAddress());
                System.out.println("port: " + p.getPort());
                System.out.println("socket address: " + p.getSocketAddress());
                System.out.println("data: " + new String(p.getData(), 0, p.getLength()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class Client implements Runnable {
        @Override
        public void run() {
            try (DatagramSocket ds = new DatagramSocket(8088, InetAddress.getLocalHost())) {
                DatagramPacket p = new DatagramPacket(new byte[1024], 1024);
                ds.receive(p);
                Thread.sleep(1000);
                System.out.println("CLIENT--------------------");
                System.out.println("address: " + p.getAddress());
                System.out.println("port: " + p.getPort());
                System.out.println("socket address: " + p.getSocketAddress());
                System.out.println("data: " + new String(p.getData(), 0, p.getLength()));
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Client()).start();
        Thread.sleep(2000);
        new Server().run();
    }
}
