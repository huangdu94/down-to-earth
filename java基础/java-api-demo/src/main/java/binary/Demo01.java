package binary;

/**
 * 包装类Integer Long中的
 * static String toBinaryString(...)方法可以将数转换为二进制字符串
 */
public class Demo01 {
    public static void main(String[] args) {
        int i = 10;
        System.out.println(i);
        System.out.println(Integer.toBinaryString(i));
        //0000 0000 0000 0000 0000 0000 0000 1010
    }

}
