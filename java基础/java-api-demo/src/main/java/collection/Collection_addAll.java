package collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 集合操作
 * @author Bean
 */
public class Collection_addAll {
	public static void main(String[] args) {
		Collection<String> c1= new ArrayList<String>();
		c1.add("java");
		c1.add("c++");
		c1.add(".net");
		System.out.println("c1："+c1);
		Collection<String> c2=new HashSet<String>();
		c2.add("android");
		c2.add("ios");
		c2.add("java");
		System.out.println("c2："+c2);
		/*
		 * 将c2集合中的所有元素存入c1
		 * boolean addAll(Collection c);
		 * 将指定collection中的所有元素都添加到此collection中。
		 */
		c1.addAll(c2);
		System.out.println("执行c1.addAll(c2)后，c1："+c1);
		
//		c2.addAll(c1);
//		System.out.println(c2);
		/*
		 *boolean containsAll(Collection c)
		 *判断当前集合是否包含给定集合中的所有元素
		 */
		boolean contains=c1.containsAll(c2);
		System.out.println("c1包含c2所有元素："+contains);
		
		Collection<String> c3=new ArrayList<String>();
		c3.add("java");
		c3.add("android");
		c3.add("php");
		System.out.println("c3:"+c3);
		/*
		 * boolean removeAll(Collection c)
		 * 删除当前集合中与给定集合中的公有元素 删交集
		 */
		c1.removeAll(c3);
		System.out.println("执行c1.removeAll(c3)后，c1："+c1);
	}
}
