package file_stream.file;
import java.io.File;
import java.io.FileFilter;

/**
 * File提供了一个重载的listFiles方法
 * 该方法允许我们传入一个文件过滤器FileFilter
 * 该方法会将File表示的目录中所有满足过滤器要求的子项返回，
 * 而不满足的则会被忽略.
 * @author Bean
 *
 */
public class File_listFiles2 {
	public static void main(String[] args) {
		/*
		 * 获取当前目录中所有名字以"."开头的子项
		 */
		File dir=new File(".");
		File[] list=dir.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				return pathname.getName().startsWith(".");
			}
		});
		for(int i=0;i<list.length;i++) {
			System.out.println(list[i]);
		}
	}
}
