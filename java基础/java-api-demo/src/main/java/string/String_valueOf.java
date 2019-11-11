package string;
/**
 * 字符串提供了一组重载的静态方法：valueOf
 * 作用是将java中其他类型转换为字符串
 * @author Bean
 *
 */
public class String_valueOf {
	public static void main(String[] args) {
		double pi=3.14159;
		String str1=String.valueOf(pi);//性能上比 str1=3.14159+"";效果好
		System.out.println(str1);
		int value=23413;
		String str2=String.valueOf(value);
		System.out.println(str2);
		boolean flag=true;
		String str3=String.valueOf(flag);
		System.out.println(str3);
		char[] charArr= {'a','b','c'};
		String str4=String.valueOf(charArr);
		System.out.println(str4);
	}
}
