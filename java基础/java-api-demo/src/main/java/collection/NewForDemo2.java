package collection;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 使用新循环遍历集合
 * @author Bean
 */
public class NewForDemo2 {
	public static void main(String[] args) {
		Collection<String> c=new ArrayList<String>();
		c.add("one");
		c.add("#");
		c.add("two");
		c.add("three");
		c.add("four");
		
		/*
		 *编译器在编译源程序时会将新循环遍历集合改为使用迭代器遍历方式. 不允许在循环过程中，增删改数组
		 */
		for(String str:c) {
			System.out.println(str);
//			if("#".equals(str)) {
//				c.remove(str);
//			}
		}
		/*Iterator<String> it=c.iterator();
		while(it.hasNext()) {
			String str=it.next();
			System.out.println(str);
		}*/
	}
}
