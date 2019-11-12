package string;

/**
 * 字符串常量池
 * java对字符串有一个优化措施，就是在堆内存中开辟了一段空间专门用来存储曾经创建的字符串对象（字面量形式创建才会存储）
 * 以便重用它们.这样可以减少因大量内容相同的字符串对象的创建，降低内存开销.
 *
 * @author Bean
 */
public class StringConstantPool {
    public static void main(String[] args) {
        String s1 = "hello";
        String s2 = "h" + "e" + "l" + "l" + "o";
        String s3 = new String("hello");
        String s4 = new String("hello");
        System.out.println(s1 == s2);
        System.out.println(s2 == s3);
        System.out.println(s1 == s3);
        System.out.println(s3 == s4);

        String s5 = "hell";//修改字符串内容一定创建新对象
        String s6 = s5 + "o";
        System.out.println(s1 == s6);//java在编译期间会做一个优化 即：当一个计算表达式中参与计算的参数都是字面量时，编译器会直接将该计算表达式进行计算，并将结果代替该表达式编译到class文件中，这样可以避免JVM每次运行时再进行计算.
        //编译后的字节码文件中代码为：String s2="hello";

        s1 += "!";
        System.out.println(s1);//修改后s1指向一个新对象
        System.out.println(s2);
    }
}
