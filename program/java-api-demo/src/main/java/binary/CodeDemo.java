package binary;

/**
 * utf-8编码规则(两字节)
 * 编码后为三个字节
 * 第一个字节 高四位 前加 1110
 * 第二个字节 中间六位 前加 10
 * 第三个字节 低四位 前加10
 *
 */
public class CodeDemo {
    public static void main(String[] args) {
        //test1();
        //test2();
        test3();
    }

    public static void test1() {
        //11111111 11111111 11111111 11111111
        int n = 0xffffffff;
        //n>>>1
        //01111111 11111111 11111111 11111111
        int m = n >>> 1;//0x7fffffff
        System.out.println(n + "," + m);
        System.out.println(Integer.toBinaryString(m));
    }

    public static void test2() {
        int m = '中';
        System.out.println("中的二进制表示：" + Integer.toBinaryString(m));
        System.out.println("编码后：");
        int b3 = m & 0x3f | 0x80;
        System.out.println("b3:" + Integer.toBinaryString(b3));
        int b2 = (m >>> 6) & 0x3f | 0x80;
        System.out.println("b2:" + Integer.toBinaryString(b2));
        int b1 = (m >>> 12) & 0xf | 0xe0;
        System.out.println("b1:" + Integer.toBinaryString(b1));

//		byte[] bytes= {(byte)b1,(byte)b2,(byte)b3};
//		String str=new String(bytes);
//		System.out.println("解码后："+str);

        int ch = (b1 & 0xf) << 12 | (b2 & 0x3f) << 6 | b3 & 0x3f;
        System.out.println("解码后：" + (char) ch);
    }

    public static void test3() {
        int n = 50;
        System.out.println(Integer.toBinaryString(n));
        int m = n << 1;
        System.out.println(m);
        System.out.println(Integer.toBinaryString(m));
		//34相当于2 34%32
        int l = n << 34;
        System.out.println(l);
        System.out.println(Integer.toBinaryString(l));
    }
}
