package object_integer;
/**
 * 包装类
 * 由于基本类型没有面向对象特性，在实际开发中不能直接参与面向对象的开发环节，为此java为这8个基本类型提供了对应的包装类.
 * 其中6个数字类型的包装类都继承自Number，而char，boolean的包装类直接继承自Object.
 * @author Bean
 *
 */
public class IntegerDemo{
	public static void main(String[] args) {
		int i=127;
//		Integer i1=new Integer(i);
//		Integer i2=new Integer(i);
		
		Integer i1=Integer.valueOf(i);
		Integer i2=Integer.valueOf(i);
		
		System.out.println(i1==i2);
		System.out.println(i1.equals(i2));
	
		
		Double d1=Double.valueOf(123.123);
		Double d2=Double.valueOf(123.123);
		
		System.out.println(d1==d2);
		System.out.println(d1.equals(d2));
		
		double dd=d1.doubleValue();
		int di=d1.intValue();
		System.out.println(dd);
		System.out.println(di);
	}
}
