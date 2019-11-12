package binary;

/**
 * 该例子展示了int类型最大值最小值的十六进制表示方法
 *
 * @author soft01
 */
public class Demo04 {

    public static void main(String[] args) {
        int max = Integer.MAX_VALUE;
        System.out.println(max);//2147483647
        System.out.println(Integer.toBinaryString(max));
//		01111111 11111111 11111111 11111111
        int min = Integer.MIN_VALUE;
        System.out.println(min);//-2147483648
        System.out.println(Integer.toBinaryString(min));
//		10000000000000000000000000000000

        int i = 0x7fffffff;
        System.out.println(Integer.toBinaryString(i));
        System.out.println(i);

        int j = 0x80000000;//可直接给计算机赋值16进制的数
        System.out.println(Integer.toBinaryString(j));
        System.out.println(j);
    }
}
