package exception;
/**
 * finally块
 * finally块只能定义在异常捕获机制的最后.即：
 * try后面或者最后一个catch之后.
 * 
 * finally块可以保证其中的代码一定执行，无论try
 * 语句块中的代码是否抛出异常.所以通常会将无关乎程序
 * 出错而都要执行的代码应当放在finally中确保运行.
 * 一般是将诸如释放资源这样的操作放在finally中，
 * 比如流的关闭操作.
 * @author Bean
 *
 */
public class Exception_finally {
	public static void main(String[] args) {
		System.out.println("程序开始了.");
		try {
			String str="";
			System.out.println(str.length());
			System.out.println(str.charAt(0));
		}catch(Exception e) {
			System.out.println("出错了!");
		}finally {
			System.out.println("finally中的代码运行了!");
		}
		System.out.println("程序结束了.");
	}
}
