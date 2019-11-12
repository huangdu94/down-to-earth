package file_stream;
/**
 * ?????????.????????????
 * ?????????????.
 *
 * @author Bean
 */

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Note_bos {
    public static void main(String[] args) throws UnsupportedEncodingException, IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入文件名：");
        String fileName = scan.nextLine();
        FileOutputStream fos = new FileOutputStream(fileName);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        System.out.println("请输入内容，以exit结束");
        while (true) {
            String information = scan.nextLine();
            if (information.equals("exit")) {
                System.out.println("退出成功.");
                break;
            }
            bos.write(information.getBytes(StandardCharsets.UTF_8));
        }
        bos.close();
        scan.close();
    }
}
