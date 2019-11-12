package file_stream;

import java.io.IOException;
import java.io.InputStream;

/**
 * System类里内置了三种流
 * System.in 键盘标准输入流
 * System.out 标准输出流
 * System.err 错误输出流
 *
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
