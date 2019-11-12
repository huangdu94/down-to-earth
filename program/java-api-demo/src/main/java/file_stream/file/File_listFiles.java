package file_stream.file;
import java.io.File;

/**
 * 获取一个目录中的所有子项
 * @author Bean
 *
 */
public class File_listFiles {
	public static void main(String[] args) {
		/*
		 * File[] listFiles()
		 * 获取当前目录中的所有子项
		 */
		File dir=new File(".");
		/*
		 * boolean isFile()
		 * 判断当前File表示的是否为一个文件
		 * 
		 * boolean isDirectory()
		 * 判断当前File表示的是否为一个目录
		 */
		if(dir.isDirectory()) {//dir肯定是目录，在此源文件中这是废话
			File[] subs=dir.listFiles();
			for(int i=0;i<subs.length;i++) {
				System.out.println((subs[i].isDirectory()?"目录：":"文件：")+subs[i].getName());
			}
		}
	}	
}
