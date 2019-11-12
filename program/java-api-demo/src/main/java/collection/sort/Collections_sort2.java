package collection.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 自定义类集合排序，有两种实现方式
 *
 * @author Bean
 */
public class Collections_sort2 {
    public static void main(String[] args) {
        List<Point2> list = new ArrayList<>();

        list.add(new Point2(2, 1));
        list.add(new Point2(3, 5));
        list.add(new Point2(4, 7));
        list.add(new Point2(8, 5));
        list.add(new Point2(7, 0));
        list.add(new Point2(2, 3));
        System.out.println(list);
        /* 方式一：
         * Collections的sort方法在排序List集合时要求该集合的元素必须实现Comparable接口.
         * 该接口规定了元素可以比较大小.
         */
//		Collections.sort(list);
        /* 方式二：
         * 排序自定义类型元素时，推荐使用下面的重载sort方法.该方法要求传入一个额外的比较器,
         * 使用匿名内部类即可.这样一句代码就可以完成排序操作.而无需在PointTwo类中实现接口
         * 并为其提供一个方法.当我们时用某个功能时，该功能要求我们为其修改代码越多，侵入性越强.
         * 实际开发中应该尽量避免侵入性强的功能.
         */
        Collections.sort(list, new Comparator<Point2>() {
            public int compare(Point2 o1, Point2 o2) {
                int value1 = o1.getX() * o1.getX() + o1.getY() * o1.getY();
                int value2 = o2.getX() * o2.getX() + o2.getY() * o2.getY();
                return value1 - value2;
            }
        });
        System.out.println(list);
    }
}
