package exception;
/**
 * 异常常用方法
 * @author Bean
 */
public class Exception_api {
	public static void main(String[] args) {
		System.out.println("程序开始了");
		try {
			String str="A";
			System.out.println(Integer.parseInt(str));
		}catch(NumberFormatException e) {
			/*
			 * 输出错误堆栈信息，有助于快速定位异常出错的代码
			 * 以便解决它
			 */
			e.printStackTrace();
//			String message=e.getMessage();
//			System.out.println(message);
		}
		System.out.println("程序结束了");
	}
}
