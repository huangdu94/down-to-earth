package file_stream.file;

import java.io.File;

/**
 * 创建一个目录
 *
 * @author bean
 */
public class File_mkdir {
    /*
     * 在当前目录中创建一个名为demo的目录
     */
    public static void main(String[] args) {
        File dir = new File("Demo1");
        if (!dir.exists()) {
            dir.mkdir();
            System.out.println("创建完毕!");
        } else {
            System.out.println("该目录已存在!");
        }
    }
}
