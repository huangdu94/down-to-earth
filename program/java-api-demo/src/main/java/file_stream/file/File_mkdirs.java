package file_stream.file;

import java.io.File;

/**
 * 创建一个多级目录
 *
 * @author Bean
 */
public class File_mkdirs {
    public static void main(String[] args) {
        /*
         * 在当前目录中创建
         * a/b/c/d/e/f
         */
        File dir = new File("a" + File.separator + "b" + File.separator + "c" + File.separator + "d" + File.separator + "e" + File.separator + "f");
        if (!dir.exists()) {
            dir.mkdirs();
            System.out.println("目录创建成功!");
        } else {
            System.out.println("目录创建不成功!");
        }
    }
}
