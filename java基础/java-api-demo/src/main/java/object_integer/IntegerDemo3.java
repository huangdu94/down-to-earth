package object_integer;
/**
 * 包装类提供了一个静态方法：parseXXX(String str)
 * 可以将字符串转换为对应的基本类型数据，前提是该字符
 * 串的内容必须能正确表示基本类型可以保存的值.
 * @author Bean
 *
 */
public class IntegerDemo3 {
	public static void main(String[] args) {
		//int a=new Integer(123);
		String str="123.123";
//		int d=Integer.parseInt(str);
//		System.out.println(d+1);
		
		double d=Double.parseDouble(str);
		System.out.println(d+1);
	}
}
