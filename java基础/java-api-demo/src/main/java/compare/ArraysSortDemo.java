package compare;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Arrays.sort() 数组排序 需要排序对象实现Comparable接口
 * 或者提供外置的Comparator接口实现的比较规则
 *
 * @author duhuang@iflytek.com
 * @version 2019/11/11 16:47
 */
public class ArraysSortDemo {
    public static void main(String[] args) {
        User[] users =new User[]{new User("李四",12),new User("张三",12),new User("王二麻子",8)};
        System.out.println(Arrays.toString(users));
        Arrays.sort(users);
        System.out.println(Arrays.toString(users));
        Arrays.sort(users, (o1, o2) -> o2.getAge()-o1.getAge()==0?o2.getUsername().compareTo(o1.getUsername()):(o2.getAge()-o1.getAge()));
        System.out.println(Arrays.toString(users));
    }
}
