package string;
/**
 * chat charAt(int index)
 * 查看当前字符串中指定下标处对应的字符
 * @author Bean
 *
 */
public class String_chatAt {
	public static void main(String[] args) {
		String str="thinking in java";
		char c=str.charAt(3);
		System.out.println(c);

		String info="上海自来水来自海上";
		for(int i=0;i<info.length()/2;i++) {
			if(info.charAt(i)!=info.charAt(info.length()-i-1)) {
				System.out.println("不是回文");
				return;//return可以单独使用，作用是结束方法，即：return执行后，该方法原本可以执行的后续代码全部都不再运行.
			}
		}
			System.out.println("是回文");
	}
}
