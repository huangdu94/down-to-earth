package net.nio;

import java.io.IOException;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 */
public class NIODemo {
    public static void main(String[] args) throws InterruptedException, IOException {
        Server server = new Server(8911);
        Client client = new Client(8911);
        Thread.sleep(1000);
        client.write("Hello World!");
        Thread.sleep(1000);
        client.write("Hello World!");
    }
}
