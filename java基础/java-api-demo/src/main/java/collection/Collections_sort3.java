package collection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 字符串的排序
 * @author Bean
 */
public class Collections_sort3 {
	public static void main(String[] args) {
		List<String> list=new ArrayList<String>();
		list.add("A");
		list.add("B");
		list.add("a");
		list.add("b");
		list.add("1");
		list.add("2");
		list.add("Aa");
		list.add("Ab");
		list.add("苍老师");
		list.add("小泽老师");
		list.add("范老师");
		
		System.out.println(list);
//		Collections.sort(list);
		/*
		 * Comparator
		 */
		/*Collections.sort(list,new Comparator<String>(){
			public int compare(String o1,String o2) {
				return o1.length()-o2.length();
			}
		});*/
		Collections.sort(list,(o1,o2)->o1.length()-o2.length());
		System.out.println(list);
	}
}
