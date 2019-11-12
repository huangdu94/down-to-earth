package file_stream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

/**
 * 对象输入流
 * 用于进行对象反序列化
 *
 * @author Bean
 */
public class ObjectInputStream_readObject {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        InputStream fis = new FileInputStream("Object.hd");
        ObjectInputStream ois = new ObjectInputStream(fis);
        /*
         * ObjectInputStream提供方法：
         * Object readObject()
         * 该方法可以读取字节并还原为指定的对象
         * 需要确保OIS读取的字节是通过对象输出流（OOS）
         * 将一个对象写出为字节.否则会抛出异常.
         */
        Person p = (Person) ois.readObject();
        ois.close();
        System.out.println(p);
    }
}
