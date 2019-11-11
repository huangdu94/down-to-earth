package regex;
/**
 * 字符串支持正则表达式方法三：
 * 
 * String replaceAll(String regex,String str)
 * 将当前字符串中满足正则表达式的部分替换为
 * 给定的字符串
 * 
 * @author Bean
 *
 */
public class String_replaceAll {
	public static void main(String[] args) {
		String str="abc123def456ghi789jkl";
		/*
		 * 将当前字符串中的数字部分替换为"#NUMBER#"
		 * 
		 * abc123def456ghi789jkl
		 * abc#NUMBER#def#NUMBER#ghi#NUMBER#jkl
		 */
		str=str.replaceAll("\\d+","#NUMBER#");
		System.out.println(str);
	}
}
