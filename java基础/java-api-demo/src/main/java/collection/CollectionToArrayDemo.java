package collection;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 集合转换为数组
 * 所有的集合都可以转换为数组，Collection定义了集合转换为数组的方法：toArray
 * @author Bean
 */
public class CollectionToArrayDemo {
	public static void main(String[] args) {
		Collection<String> c=new ArrayList<String>();
		c.add("one");
		c.add("two");
		c.add("three");
		c.add("four");
		System.out.println(c);
		/*
		 * 将集合转换为数组
		 */
//		Object[] array=c.toArray();
		String[] array=c.toArray(new String[c.size()]);
		System.out.println("len:"+array.length);
		for(String str:array) {
			System.out.println(str);
		}
	}
}
