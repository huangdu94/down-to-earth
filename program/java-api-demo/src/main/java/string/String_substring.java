package string;
/**
 * String substring(int start,int end)
 * 截取字符串
 * @author Bean
 *
 */
public class String_substring {
	public static void main(String[] args) {
		String str="thinking in java";
		str=str.substring(9,11); //含头不含尾
		System.out.println(str);
	}
}
