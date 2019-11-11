package object_integer;

public class IntegerDemo4 {
	public static void main(String[] args) {
		Integer i = new Integer(123);
		/*
		 * 在JDK1.5的时候推出了一个新的特性
		 * 自动拆装箱
		 * 自动拆装箱是编译器认可的特性，而非JVM认可，当编译器在编译源代码时发现了基本类型与对应的包装类之间互相赋值使用时，会自动补充代码完成他们之间的转换工作.
		 */
		/*
		 * 触发了拆箱特性，编译器会补充代码：
		 * int d=i.intValue();
		 * 将引用类型转换为基本类型.
		 */
		int d=i;
		/*
		 * 触发自动装箱特性，编译器会补充代码：
		 * Integer i=Integer.valueOf(d);
		 * 将基本类型转换为引用类型.
		 */
		i=d;
		System.out.println(i);
	}
}
