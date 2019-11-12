package collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 数组转换为集合
 * 通过数组的工具类Arrays的方法asList实现.
 * 需要注意，该方法仅能将数组转换为List集合
 * (该List为Arrays类里的一个内部类的实例，API与ArrayList基本相同，但不支持扩容)
 *
 * @author Bean
 */
public class ArrayToListDemo {
    public static void main(String[] args) {
        String[] array = {"one", "two", "three", "four", "five"};

        List<String> list = Arrays.asList(array);
        System.out.println("size:" + list.size());
        System.out.println(list);
        /*
         * 将集合元素改变
         * 当将一个数组转换为集合后，对该集合元素操作就是对原数组对应元素的操作
         */
        list.set(1, "2");
        System.out.println(list);
        System.out.println(Arrays.toString(array));

        /*
         * 向集合中添加新元素：six
         */
		//list.add("six");//不受支持，因为会导致数组扩容
        List<String> listOne = new ArrayList<>(list);
        listOne.add("six");
        System.out.println(listOne);
    }
}
