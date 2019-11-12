package file_stream.file;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * java.io.File
 * File表示操作系统中文件系统里的一个文件或目录
 * 使用File可以：
 * 1.访问一个文件或目录的属性信息
 * 2.操作一个文件或目录（创建，删除）
 * 3.访问一个目录的子项
 * 但是不能使用File访问文件数据.
 *
 * @author Bean
 */
public class FileDemo {
    public static void main(String[] args) {
        /*
         * 在创建File的时候书写路径尽量使用相对路径.避免平台差异性.
         * 目录层级分隔符应当使用File提供的一个常量：File.separator
         * 常见的相对路径：
         * 1".":表示当前目录，当前目录视运行环境不同路径也不同.
         * 在eclipse中运行java程序时的当前目录是当前类所在项目的根目录
         */
        File file = new File("." + File.separator + ".test.txt");
        //获得文件名
        String name = file.getName();
        System.out.println("文件名：" + name);
        //获得文件大小byte
        long length = file.length();
        System.out.println("文件大小：" + length);
        //可读可写可执行
        boolean canRead = file.canRead();
        boolean canWrite = file.canWrite();
        boolean canExecute = file.canExecute();
        System.out.println("可读：" + canRead);
        System.out.println("可写：" + canWrite);
        System.out.println("可执行：" + canExecute);
        //是否隐藏
        boolean isHidden = file.isHidden();
        System.out.println("是否为隐藏文件：" + isHidden);
        //文件最后修改时间
        long lastModified = file.lastModified();
        String lastModifiedStr = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date(lastModified));
        System.out.println("文件最后修改时间：" + lastModifiedStr);
    }
}
