package binary;

/**
 * 该例子展示了计算机中正数用二进制的表示方法
 * 以及十六进制与二进制之间的转换
 *
 * @author soft01
 */
public class Demo02 {
    public static void main(String[] args) {
        for (int i = 1; i <= 50; i++) {
            System.out.print(Integer.toBinaryString(i) + '\t');
            if (i % 10 == 0 && i != 0) {
                System.out.println();
            }
        }

        int i = 0x6cfed5af;
        System.out.println(Integer.toBinaryString(i));
        //0110 1100 1111 1110 1101 0101 1010 1111
        int j = 0x6af555fa;
        System.out.println(Integer.toBinaryString(j));
        //0110 1010 1111 0101 0101 0101 1111 1010
        int k = 0x00000032;
        System.out.println(Integer.toBinaryString(k));
        //0000 0000 0000 0000 0000 0000 0011 0010
    }


}
