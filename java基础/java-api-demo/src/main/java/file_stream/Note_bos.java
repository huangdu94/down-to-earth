package file_stream;
/**
 * 完成简易记事本程序.使用文件流加缓冲流的形式
 * 向文件中写入用户输入的信息.
 * @author Bean
 *
 */
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Note_bos {
	public static void main(String[] args) throws UnsupportedEncodingException, IOException {
		Scanner scan=new Scanner(System.in);
		System.out.println("�������ļ�����");
		String fileName=scan.nextLine();
		FileOutputStream fos=new FileOutputStream(fileName);
		BufferedOutputStream bos=new BufferedOutputStream(fos);
		System.out.println("�ļ������ɹ������������ݣ�exit�˳�");
		while(true) {
			String information=scan.nextLine();
			if(information.equals("exit")) {
				System.out.println("�ļ�����ɹ�");
				break;
			}
			bos.write(information.getBytes("utf-8"));
		}
		bos.close();
		scan.close();
	}
}
