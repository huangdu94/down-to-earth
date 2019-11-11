package collection;

import java.util.Stack;

/**
 * 栈
 * 栈用来存一组元素，但是存取元素必须遵循先进后出原则.通常使用栈来实现"后退"功能.
 * @author Bean
 */
public class StackDemo {	
	public static void main(String[] args) {
		Stack<String> stack=new Stack<String>();
//		Deque<String> stack=new LinkedList<String>();
		stack.push("one");
		stack.push("two");
		stack.push("three");
		stack.push("four");
		System.out.println(stack);
		
//		System.out.println(stack.pop());
//		System.out.println(stack);
//		
//		System.out.println(stack.pop());
//		System.out.println(stack);
//		
//		System.out.println(stack.pop());
//		System.out.println(stack);
//		
//		System.out.println(stack.pop());
//		System.out.println(stack);
		while(stack.size()>0) {
			System.out.println(stack.pop());
			System.out.println(stack);
		}
	}
}
