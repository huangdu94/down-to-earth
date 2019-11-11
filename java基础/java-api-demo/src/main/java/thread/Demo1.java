package thread;

import java.util.Scanner;

/**
 * 输入一个数字，从该数字开始倒数，直到0为止.
 * 没隔一秒倒数一次.
 * @author Bean
 */
public class Demo1 {
	public static void main(String[] args) {
		System.out.println("Please input a number that is great than zero:");
		Scanner scan=new Scanner(System.in);
		try {
			int number=scan.nextInt();//int number=System.in.read();
			System.out.println("Start...");
			for(int i=number;i>=0;i--) {
				Thread.sleep(1000);
				System.out.println(i);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("End.");
	}
}
