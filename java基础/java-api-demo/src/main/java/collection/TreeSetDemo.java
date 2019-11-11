package collection;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author DuHuang
 * @date 2019/11/10 11:08
 */
public class TreeSetDemo {
    public static void main(String[] args) {
        Set<String> all = new TreeSet<>();
        all.add("张三");
        all.add("李四");
        all.add("王五");
        all.add("王五");
        all.forEach(System.out::println);
        System.out.println((int)'张');
        System.out.println((int)'李');
    }
}
