package classloader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 调用一个含有参数的方法
 * @author Bean
 */
public class ClassDemo3 {
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		ClassLoader loader=ClassLoader.getSystemClassLoader();
		Class<?> c=loader.loadClass("day16.Person");
		
		//获取用于描述String的Class实例
//		Class strc=String.class;
//		strc=loader.loadClass("java.lang.String");
		/*
		 * 获取有参方法：
		 * 参数1：该方法的名字
		 * 参数2：这是一个Class类型的数组，数组中的每个元素表示要获取的参数类型.需要注意，这个数组中的元素个数，类型，顺序必须与要获取的方法的参数列表一致
		 * 下面方法获取的是Person中的方法：
		 * sayInfo(String s,int i)
		 */
		Method m=c.getDeclaredMethod("sayInfo",new Class[] {String.class,int.class});
		Object o=c.newInstance();
		/*
		 * 调用invoke时，第二个参数为一个对象数组.
		 * 数组中的每个元素就是调用这个方法时需要传入的实际参数.
		 * 下面代码等同调用：
		 * p.sayInfo("苍苍",55)
		 */
		m.invoke(o,new Object[] {"黄渡",18});
	}
}
