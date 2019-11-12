package collection;

import java.util.ArrayList;
import java.util.List;

/**
 * java.util.List
 * List是Collection的一个常用子接口.
 * List集合的特点是：可重复，并且有序.
 * List提供了一组独有的方法，特点是可以通过下标操作元素.（Set集合并不具备这些方法）
 *
 * @author Bean
 */
public class List_add_remove {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        System.out.println(list);

        /*
         * void add(int index,E e)
         * 将给定元素插入到指定位置
         */
        list.add(4, "five");
        System.out.println(list);
        /*
         * E remove(int index)
         * 从集合中删除指定位置的元素返回值为被删除的元素.
         */
        String old = list.remove(3);
        System.out.println(list);
        System.out.println("被删除的元素是：" + old);
    }
}
