package string;
/**
 * int indexOf（String str）
 * 检索给定字符串str在当前字符串中的位置
 * @author Bean
 *
 */
public class String_indexOf {
	public static void main(String[] args) {
		String str="thinking in java";
		
		int index=str.indexOf("in");
		System.out.println(index);
		//从指定位置开始检索第一次出现指定字符串的位置
		index=str.indexOf("in",3);
		System.out.println(index);
		//检索最后一次出现指定字符串的位置
		index=str.lastIndexOf("in");
		System.out.println(index);
	}
}
