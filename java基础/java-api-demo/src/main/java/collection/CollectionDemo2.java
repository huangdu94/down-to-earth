package collection;
import java.util.ArrayList;
import java.util.Collection;
import object_integer.Point;

/**
 * 集合存放的是元素的引用（地址）
 * @author Bean
 */
public class CollectionDemo2 {
	public static void main(String[] args) {
		Point p=new Point(1,2);
		Collection<Point> c=new ArrayList<Point>();
		c.add(p);
		System.out.println(p);
		System.out.println(c);
		
		p.setX(2);
		System.out.println(p);
		System.out.println(c);
	}
}