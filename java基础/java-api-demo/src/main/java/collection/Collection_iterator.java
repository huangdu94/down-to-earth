package collection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


/**
 * 集合的遍历
 * Collection提供了统一的遍历集合元素的方式：
 * 迭代器模式（Iterator）
 * 对应方法：
 * Iterator iterator()
 * 该方法会返回一个可以遍历当前集合元素的迭代器实例.
 * 
 * java.util.Iterator接口
 * 该接口规定了迭代器用来遍历集合的相关方法.不同的集合都实现了一个可以用于遍历自身的迭代器实现类.
 * 我们无需记住每种集合提供的迭代器实现类的名字，只要将它们以Iterator接口看待，可以调用相关遍历
 * 方法达到遍历集合的目的就可以了.
 * 
 * 迭代器遍历集合遵循步骤：
 * 问，取，删（其中删除不是必须操作）
 * @author Bean
 */
public class Collection_iterator {
	public static void main(String[] args) {
		Collection<String> c=new ArrayList<String>();
		c.add("one");
		c.add("#");
		c.add("two");
		c.add("#");
		c.add("three");
		c.add("#");
		c.add("four");
		c.add("#");
		c.add("five");
		c.add("#");
		c.add("six");
		c.add("#");
		c.add("seven");
		c.add("#");
		c.add("eight");
		c.add("#");
		c.add("nine");
		
		Iterator<String> it=c.iterator();
		/*
		 * boolean hasNext()
		 * 通过迭代器判断集合是否还有元素可以遍历
		 */
		while(it.hasNext()) {
			/*
			 * E next()
			 * 获取集合中下一个元素
			 */
			String str=(String)it.next();
			System.out.println(str);
			if("#".equals(str)) {
				/*
				 * 在使用迭代器遍历集合的过程中不能通过集合的方法增删元素，否则会发生迭代错误.
				 */
//				c.remove(str);
				/*
				 * 迭代器的remove方法可以将通过next方法取出的元素从集合中删除
				 */
				it.remove();
			}
		}
		System.out.println(c);
	}
}
