package thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * StringBuiler不是线程安全的，当多个线程操作同一个字符串时应当使用StringBuffer
 * 对于集合而言，常用的实现类：ArrayList,LinkedList,HashSet它们都不是线程安全的.
 * Collections可以将现有的集合转换为线程安全的.
 *
 * @author Bean
 */
public class SyncAPIDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        list.add("four");
        System.out.println(list);
        /*
         *将给定List集合转换为线程安全的List
         */
        list = Collections.synchronizedList(list);
        System.out.println(list);

        Set<String> set = new HashSet<>(list);
        System.out.println(set);
        /*
         * 将给定的Set集合转换为线程安全的
         */
        set = Collections.synchronizedSet(set);
        System.out.println(set);
        /*
         * 线程安全的集合也不与迭代器遍历集合元素的操作互斥，需要注意.
         */
    }
}
