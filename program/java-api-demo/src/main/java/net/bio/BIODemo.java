package net.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 */
public class BIODemo {
    public static void main(String[] args) throws IOException {
        Server server = new Server();

        Socket socket = new Socket("localhost", 8088);
        try (OutputStream os = socket.getOutputStream(); PrintWriter pw = new PrintWriter(os)) {
            pw.println("我是狗！");
        }
        //Socket socket2 = new Socket("localhost", 8088);
        //Socket socket3 = new Socket("localhost", 8088);
        //Socket socket4 = new Socket("localhost", 8088);
    }
}
