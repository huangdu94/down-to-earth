package collection.map;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Map的遍历
 * 遍历Map有三种方式：
 * 1：遍历所有的key
 * 2：遍历所有的键值对（Entry）
 * 3：遍历所有的value
 * @author Bean
 */
public class Map_iterate {
	public static void main(String[] args) {
		Map<String,Integer> map=new HashMap<String,Integer>();//LinkedHashMap 有序
		map.put("体育",1000);
		map.put("人体彩绘",99);
		map.put("生理卫生",99);
		map.put("美术",80);
		System.out.println(map);
		
		/*
		 * 遍历所有的key
		 * Set<K> ketSet()
		 * 将当前Map中所有的key存入一个Set集合后返回.
		 */
		Set<String> keySet=map.keySet();
		keySet.forEach((k)->System.out.println(k));
		
//		for(String str:keySet) {
//			System.out.println(str);
//		}
//		
//		Iterator<String> it=keySet.iterator();
//		while(it.hasNext()) {
//			String str=it.next();
//			System.out.println(str);
//		}
		
		/*
		 * 遍历所有的键值对
		 * Map中每一组键值对有内部类Entry的实例表示Entry有两个常用方法：
		 * getKey,getValue.这两个方法用于获取当前Entry实例表示的这组键值对中的key与value
		 * 
		 * Set<Entry> entrySet()
		 * 将当前Map中每组键值对存入一个Set集合后返回
		 */
		Set<Entry<String, Integer>> entrySet=map.entrySet();
//		entrySet.forEach((k)->{
//			System.out.println(k.getKey());
//			System.out.println(k.getValue());
//		});
		
		for(Entry<String,Integer> e:entrySet) {
			String key=e.getKey();
			Integer value=e.getValue();
			System.out.println(key+":"+value);
		}
		
//		Iterator<Entry<String,Integer>> it=entrySet.iterator();
//		while(it.hasNext()) {
//			Entry<String,Integer> e=it.next();
//			System.out.println(e.getKey()+":"+e.getValue());
//		}
		
		/*
		 * 遍历所有的value
		 * Collection<V> values()
		 * 将当前Map中所有的value存入一个集合后返回.
		 */
		
		Collection<Integer> values=map.values();
//		values.forEach((k)->System.out.println(k));
		
//		for(Integer i:values) {
//			System.out.println(i);
//		}
		
		Iterator<Integer> it=values.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		
		/*
		 * HashMap本身不是线程安全的，若希望将现有的Map转换为线程安全的，可以使用Collections的静态方法将其转换.
		 */
		Collections.synchronizedMap(map);
	}
}
