package collection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 集合的排序
 * 需要注意，仅能对List集合进行排序.
 * 排序可以使用集合的工具类：java.util.Collections
 * 其提供了一个静态方法sort可以对List集合进行自然排序（从小到大）
 * @author Bean
 */
public class Collections_sort {
	public static void main(String[] args) {
		List<Integer> list=new ArrayList<Integer>();
		
		Random random=new Random();
		for(int i=0;i<10;i++) {
			list.add(random.nextInt(100));
		}
		System.out.println(list);
		Collections.sort(list);
		System.out.println(list);
	}
}
