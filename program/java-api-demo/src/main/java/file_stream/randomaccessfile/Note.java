package file_stream.randomaccessfile;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 * 简易的记事本
 * 程序启动后，要求用户输入一个文件名
 * 然后对该文件写数据.
 * 用户输入的每一行内容都写入该文件.
 * 当用户输入exit时程序退出.
 *
 * @author Bean
 */
public class Note {
    public static void main(String[] args) throws IOException {
        start();
    }

    /**
     * 获取用户输入的文件
     *
     * @return File
     */
    private static File getUserFile() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("请输入一个文件名：");
            String fileName = scan.nextLine();
            File file = new File(fileName);
            if (file.exists()) {
                System.out.println(fileName + "文件已经存在!输入0重新设置，输入其它则继续使用该文件.");
                String str = scan.nextLine();
                if (str.equals("0")) {
                    continue;
                } else {
                    return file;
                }
            }
            System.out.println("文件创建完毕.");
            return file;
        }
    }

    /**
     * 程序开始
     */
    public static void start() {
        File file = getUserFile();
        try {
            writeUserInfo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取用户输入的每行信息并写入到指定文件
     */
    private static void writeUserInfo(File file) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入：（输入exit结束）");
        while (true) {
            String str = scan.nextLine();
            String exit = str.toUpperCase();
            if (exit.equals("EXIT")) {
                System.out.println("输入结束，文件已保存");
                break;
            }
            byte[] data = str.getBytes();
            raf.write(data);
        }
        raf.close();
    }
}
