package collection;

import object_integer.Point;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 删除集合元素
 *
 * @author Beans
 */
public class Collection_remove {
    public static void main(String[] args) {
        Collection<Point> c = new ArrayList<>();
        c.add(new Point(1, 2));
        c.add(new Point(2, 3));
        c.add(new Point(3, 4));
        System.out.println(c);

        Point p = new Point(2, 3);
        /*
         * boolean remove(E e)
         * 从集合中删除给定元素，删除成功则返回true
         *
         * 删除逻辑为：集合会顺序用给定元素与集合元素进行equals比较，删除第一个比较为true的元素.
         */
        c.remove(p);

        System.out.println(c);
    }
}
