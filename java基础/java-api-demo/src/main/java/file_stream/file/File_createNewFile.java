package file_stream.file;

import java.io.File;
import java.io.IOException;

/**
 * 使用File创建一个新文件
 *
 * @author Bean
 */
public class File_createNewFile {
    public static void main(String[] args) throws IOException {
        /*
         * 在当前目录下创建一个名为demo.txt的文件
         */
        File file = new File("." + File.separator + "demo.txt");
        /*
         * boolean exists()
         * 判断当前File表示的文件或目录是否真实存在
         */
        if (!file.exists()) {
            //创建该文件
            file.createNewFile();
            System.out.println("文件创建完毕");
        } else {
            System.out.println("该文件已存在!");
        }
    }
}
