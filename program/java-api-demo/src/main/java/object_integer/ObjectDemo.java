package object_integer;


/**
 * 测试Point重写的Object相关方法
 *
 * @author Bean
 */
public class ObjectDemo {
    public static void main(String[] args) {
        Point p = new Point(1, 2);
        /*
         * Object 定义了toString方法.
         * 该方法的意义是将当前字符串转换为一个
         * 字符串.
         * 通常该字符串的内容包含该对象的属性信息
         * Object已经实现了toString方法，返回的是当前对象的句柄(类名@地址)
         *
         * JAVA很多的API都会使用给定对象的toString方法.
         * System.out.println(Object obj)方法都使用了，该方法会将给定对象的toString返回的字符串输出到控制台.
         *
         * JAVA API中提供的类大部分都已经重写过toString方法.只有我们自己定义的类需要自行重写toString方法.
         */
//		String str=p.toString();
//		System.out.println(str);

        System.out.println(p);

        Point p1 = new Point(1, 2);
        Point p2 = new Point(1, 2);
        System.out.println("p1：" + p1);
        System.out.println("p2：" + p2);

        System.out.println(p1 == p2);
        /*
         * Object提供了equals方法，目的是比较两个对象的内容是否一样.Object实现的equals内部就是"=="比较的，所以若不重写该方法是没有实际意义的.
         * JAVA API中大部分类都重写了equals方法.只有自己定义的类我们在需要使用的时候必须重写它.
         */
        System.out.println(p1.equals(p2));

        /*
         * Object提供了clone方法，目的是克隆对象，需要实现Cloneable接口
         */
        Point p1_copy = p1.clone();
        System.out.println(p1.equals(p1_copy));
        System.out.println(p1 == p1_copy);
    }
}
