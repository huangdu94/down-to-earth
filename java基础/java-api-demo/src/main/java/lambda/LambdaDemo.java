package lambda;

import java.util.ArrayList;
import java.util.List;

/**
 * Lambda表达式 实现函数式编程
 * Lambda是JDK8推出的一个新特性.
 * lambda常用在快捷方便的创建匿名内部类上.
 * 需要注意的是lambda创建匿名内部类要求该接口或者类只能有一个方法.
 * lambda是编译器认可，而非虚拟机,编译器最终会将其改写为内部类形式.
 * <p>
 * lambda表达式语法
 * （parameter)->expression 或
 * （parameter)->{statements;}
 * <p>
 * 例如：
 * （）->5				不需要参数，返回值为5
 * (x,y)->x-y		需要传入两个参数，返回值为x-y
 * （int x,int y)->x-y 需要传入两个int型参数，返回值为x-y
 * （s)->System.out.println(s)
 *
 * @author Bean
 */
public class LambdaDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        list.add("five");

        System.out.println(list);

        list.forEach((o) -> System.out.println(o));
    }
}
