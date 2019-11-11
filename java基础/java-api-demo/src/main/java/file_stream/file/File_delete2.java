package file_stream.file;
import java.io.File;

/**
 * 删除一个目录
 * @author Bean
 *
 */
public class File_delete2 {
	public static void main(String[] args) {
		/*
		 * 将当前目录中的demo目录删除
		 */
		File dir=new File("Demo1");
		if(dir.exists()) {
			dir.delete();//目录是空的才可以删掉
			System.out.println("目录已删除");
		}else {
			System.out.println("该目录不存在");
		}
	}
}
