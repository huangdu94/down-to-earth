package string;

/**
 * java.lang.StringBuilder
 * 由于String的设计不利于频繁修改内容，所以java专门提供了一个用于修改字符串内容的类：StringBuilder
 * StringBuilder提供了一个可变的字符数组，所以字符串内容的修改都是在这个数组中进行，而不会每次修改都创建一个新对象.
 * java.lang.StringBuffer
 * 与StringBuilder功能类似，但是它是线程安全的
 *
 * @author Bean
 */
public class StringBuilderDemo {
    public static void main(String[] args) {
        String str = "你好，世界!";

        StringBuilder builder = new StringBuilder(str);
        System.out.println(builder.toString());//默认builder.toString()
        //你好，世界!

        builder.append("大象!");
        System.out.println(builder.toString());
        //你好，世界!大象!

        builder.delete(0, 3);
        System.out.println(builder.toString());
        //世界!大象!

        builder.insert(0, "我不好");
        System.out.println(builder.toString());
        //我不好世界!大象!

        builder.replace(6, 8, "猴子");
        System.out.println(builder.toString());
        //我不好世界!猴子!

        builder.reverse();
        System.out.println(builder.toString());
        //!子猴!界世好不我
    }
}
