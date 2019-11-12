package string;
/**
 * boolean startsWith(String str)
 * boolean endsWith(String str)
 * 判断当前字符串是否是以给定字符串开始或结尾的
 * @author Bean
 *
 */
public class String_startWith_endsWith {
	public static void main(String[] args) {
		String str="Thinking in JAVA";
		
		System.out.println(str.startsWith("Th"));
		System.out.println(str.endsWith("VA"));
	}
}
