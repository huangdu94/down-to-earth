package file_stream.file;
import java.io.File;

/**
 * 删除一个文件
 * @author Bean
 *
 */
public class File_delete {
	public static void main(String[] args) {
		/*
		 * 将当前目录中的demo.txt文件删除
		 * 相对路径默认是从"当前目录"开始
		 * 所以
		 * new File("demo.txt")
		 * 等同
		 * new File("./demo.txt")
		 */
		File file=new File("demo.txt");
		if(file.exists()) {
			file.delete();
			System.out.println("删除完毕.");
		}else {
			System.out.println("该文件不存在!");
		}
	}
}
