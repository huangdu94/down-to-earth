package runtime_system;

import java.io.*;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

/**
 * 系统类，可以取得系统的相关信息
 *
 * @author duhuang@iflytek
 * @version 2019/11/11 10:51
 */
public class SystemDemo {
    public static void main(String[] args) {
        //获得当前毫秒数
        long start = System.currentTimeMillis();
        //获得系统环境变量
        System.out.println(System.getenv("JAVA_HOME"));
        Map<String, String> envMap = System.getenv();
        for (Map.Entry<String, String> entry : envMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        //提醒gc回收 内部调用了Runtime类的gc()方法
        System.gc();

        //三种内置流
        OutputStream innerOut = System.out; //标准输出流
        OutputStream innerErr = System.err; //错误输出流
        InputStream innerIn = System.in; //标准键盘输入

        //获得Java(TM) SE Runtime Environment 使用流输出到文件
        File file = new File("D:\\environment.txt");
        try (OutputStream out = new FileOutputStream(file); PrintWriter pw = new PrintWriter(out)) {
            Properties properties = System.getProperties();
            /*for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                pw.println(entry.getKey() + ": " + entry.getValue());
            }*/
            properties.store(pw,"duhuang输出");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("本程序耗时：" + (end - start) / 1000.0 + "秒");
    }
}
