package collection;
import java.util.ArrayList;
import java.util.List;

/**
 * List提供的基于下标操作方法之：get,set
 * @author Bean
 */
public class List_get_set {
	public static void main(String[] args) {
		List<String> list=new ArrayList<String>();
		list.add("one");
		list.add("two");
		list.add("three");
		list.add("four");

		/*
		 * E get(int index)
		 * 获取指定位置上的元素
		 */
		String str=list.get(0);
		System.out.println(str);
		for(int i=0;i<list.size();i++) {
			str=list.get(i);
			System.out.println(str);
		}
//		for(String s:list) {
//			System.out.println(s);
//		}
//		Iterator<String> it=list.iterator();
//		while(it.hasNext()) {
//			System.out.println(it.next());
//		}
		/*
		 * E set(int index,E e)
		 * 将给定元素设置到指定位置，返回值为原位置对应的元素.所以set方法是替换元素的操作.
		 */
		String old=list.set(0,"zero");
		System.out.println(list);
		System.out.println(old);
	}
}
