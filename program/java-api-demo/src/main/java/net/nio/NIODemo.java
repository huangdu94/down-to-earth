package net.nio;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 */
public class NIODemo {
    public static void main(String[] args) throws InterruptedException, IOException {
        Server server = new Server(8911);
        Client client = new Client(8911);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if ("exit".equals(input)) {
                server.stop();
                client.stop();
                System.exit(1);
            } else if ("server stop".equals(input)) {
                server.stop();
            } else {
                client.write(input);
            }
        }
    }
}
