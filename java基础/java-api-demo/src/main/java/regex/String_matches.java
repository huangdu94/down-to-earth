package regex;
/**
 * 字符串支持正则表达式的相关方法：
 * 
 * boolean matches(String regex)
 * 使用给定的正则表达式验证当前字符串是否满足格式要求，满足则返回true.
 * 需要注意，正则表达式就算不添加边界符（^...$)也是做全匹配验证.
 * @author Bean
 *
 */
public class String_matches {
	public static void main(String[] args) {
		/*
		 * 验证邮箱的正则表达式：
		 * [a-zA-Z0-9_]+@[a-zA-Z0-9_]+(\.[a-zA-Z0-9_]+)+
		 * \w+@\w+(\.\w+)+
		 */
		String mail="fancq@tedu.cn";
		String regex="\\w+@\\w+(\\.\\w+)+";
		boolean match=mail.matches(regex);
		if(match) {
			System.out.println("是邮箱");
		}else {
			System.out.println("不是邮箱");
		}
	}
}
