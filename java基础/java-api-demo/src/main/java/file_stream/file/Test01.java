package file_stream.file;
import java.io.File;

/**
 * 1：编写一段程序，要求实现1+2+3+4+...100并输出结果.
 * 在这段代码中不得出现for,while关键字
 * 
 * 2：假如1块钱可以买1瓶汽水，3个瓶盖可以换一瓶汽水2个空瓶可以换一瓶汽水，编一段程序计算20块钱总共可以买到多少瓶汽水？
 * 
 * 练习：删除给定的File表示的文件或目录
 * @author Bean
 *
 */
public class Test01 {
	public static void main(String[] args) {
//		File file=new File("a");
//		delete(file);
//		System.out.println(add(100));
		System.out.println(change(20,20,20));
	}
	public static void delete(File f) {
		if(f.isDirectory()) {
			//先将该目录中所有子项删除
			File[] subs=f.listFiles();
			for(int i=0;i<subs.length;i++) {
				delete(subs[i]);//递归调用
			}
		}
		f.delete();
	}
	public static int add(int num) {
		if(num<1) {
			return -1;
		}else if(num==1){
			return 1;
		}else{
			return num+add(num-1);
		}
	}
	public static int change(int number,int caps,int bottles) {
		if(number==0) {
			return 0;
		}
		return number+change(caps/3+bottles/2,caps/3+bottles/2+caps%3,caps/3+bottles/2+bottles%2);
	}
}
