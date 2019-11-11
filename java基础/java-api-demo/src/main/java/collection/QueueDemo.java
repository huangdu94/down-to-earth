package collection;

//import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 队列
 * 队列用于存放一组元素，但是存取元素只能从"两端"进行且遵循"先进先出"原则.
 * FIFO，First Input First Output
 * 
 * java.util.Queue 队列接口，规定了队列相关方法
 * 
 * @author Bean
 */
public class QueueDemo {
	public static void main(String[] args) {
		Queue<String> queue=new LinkedList<String>();
		/*
		 * boolean offer(E e)
		 * 入队操作，向队列末尾追加给定元素
		 */
		queue.offer("one");
		queue.offer("two");
		queue.offer("three");
		queue.offer("four");

		//		Iterator<String> it=queue.iterator();
		//		while(it.hasNext()) {
		//			System.out.println(it);
		//		}
		/*		while(queue.size()>0) {
			String str=queue.poll();
			System.out.println(str);
		}*/
		for(String s:queue) {
			System.out.println(s);
		}
		System.out.println(queue);
	}
}
