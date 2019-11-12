package lambda;

import java.util.ArrayList;
import java.util.Collections;
//import java.util.Comparator;
import java.util.List;

/**
 * 排序集合
 *
 * @author Bean
 */
public class LambdaDemo2 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("苍老师");
        list.add("传奇");
        list.add("小泽老师");
        list.add("晶");
        System.out.println(list);


//		Collections.sort(list,new Comparator<String>() {
//			public int compare(String o1, String o2) {
//				return o1.length()-o2.length();
//			}
//		});
        Collections.sort(list, (o1, o2) -> o1.length() - o2.length());

        System.out.println(list);
    }
}
