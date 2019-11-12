package collection;

/**
 * JDK1.5之后推出的又一个新特性：增强for循环，又称为新循环，for each
 * 新循环并非新的语法，它不是JVM认可的，而是编译器认可的.作用是方便遍历集合或数组.
 *
 * @author Bean
 */
public class NewForDemo1 {
    public static void main(String[] args) {
        String[] array = {"one", "two", "three", "four", "five"};
        for (String str : array) {
            System.out.println(str);
        }
    }
}
