package collection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 集合中广泛使用泛型，而泛型是用来约束集合中的元素类型.
 * @author Bean
 */
public class CollectionDemo3 {
	public static void main(String[] args) {
		Collection<String> c=new ArrayList<String>();
		c.add("3...");
		c.add("2...");
		c.add("1...");
		c.add("ready go!");
		System.out.println(c);
		
		Iterator<String> it=c.iterator();
		while(it.hasNext()) {
			String str=it.next();
			System.out.println(str);
		}
	} 
}
