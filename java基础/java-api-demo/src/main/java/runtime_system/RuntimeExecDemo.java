package runtime_system;

import java.io.IOException;
import java.io.InputStream;

/**
 * Runtime.exec()的使用
 *
 * @author duhuang@iflytek
 * @version 2019/11/11 11:15
 */
public class RuntimeExecDemo {
    public static void main(String[] args) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        //Linux String[] cmd = {"/bin/sh","-c","mkdir hello"};
        //想弹出cmd窗口 start mkdir hello
        String[] cmd = {"cmd","/C","mkdir hello"};
        Process process = runtime.exec(cmd);
    }
}
