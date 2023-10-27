package net.nio;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 */
public class NIODemo {
    public static void main(String[] args) throws InterruptedException {
        Server server = new Server(8099);
        Client client = new Client(8099);
        client.write("Hello World!");

        Thread.sleep(10000);
        client.write("Hello World!");
    }
}
