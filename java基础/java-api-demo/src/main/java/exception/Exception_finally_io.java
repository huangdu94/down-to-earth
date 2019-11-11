package exception;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 异常处理机制在IO中的应用
 * @author Bean
 *
 */
public class Exception_finally_io {
	public static void main(String[] args) {
		BufferedReader br=null;
		try {
			InputStream in=System.in;
			InputStreamReader isr=new InputStreamReader(in);
			br=new BufferedReader(isr);
			while(true) {
				String line=br.readLine();
				if(line.equals("exit")) {
					break;
				}
				System.out.println(line);
			}
		}catch(Exception e) {
			System.out.println("程序出错了");
		}finally {
			try {
				if(br!=null) {
					br.close();
				}
			}catch(Exception e) {
				System.out.println("关闭出错啦");
			}
		}
	}
}
