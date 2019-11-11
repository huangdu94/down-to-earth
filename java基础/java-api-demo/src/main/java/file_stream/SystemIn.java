package file_stream;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author DuHuang
 * @date 2019/11/9 20:01
 */
public class SystemIn {
    public static void main(String[] args) {
        InputStream in = System.in;
        System.out.println("hello");
        System.err.println("no");
        byte[] input = new byte[10000];
        int len = 0;
        try {
            len = in.read(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(new String(input, 0, len));
    }
}
