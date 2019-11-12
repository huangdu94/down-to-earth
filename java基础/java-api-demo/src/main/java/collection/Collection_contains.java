package collection;

import object_integer.Point;

import java.util.ArrayList;
import java.util.Collection;

/**
 * boolean contains(E e)
 * 判断当前集合是否包含给定元素.
 *
 * @author Bean
 */
public class Collection_contains {
    public static void main(String[] args) {
        Collection<Point> c = new ArrayList<>();
        c.add(new Point(1, 2));
        c.add(new Point(2, 3));
        c.add(new Point(4, 5));
        System.out.println(c);

        Point p = new Point(2, 3);
        /*
         * 当前集合元素只要有与给定对象equals比较为true的，则认为当前集合包含该元素
         * 作为集合的元素，它的toString,equals方法会影响集合很多方法的结果.
         * 所以若集合存放的是我们自己写的类，那么就要妥善重写着两个方法
         */
        boolean contain = c.contains(p);
        System.out.println("是否包含：" + contain);
    }
}
