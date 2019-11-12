package binary;

/**
 * ~符号表示 按位取反运算符
 * 该例子演示 一个变量 按位取反加一后会得到自己的相反数
 * 除了最小值以外 例如Integer.MIN_VALUE(溢出)
 *
 * @author soft01
 */
public class Demo05 {
    public static void main(String[] args) {
        int n = 8;
//		n=00000000 00000000 00000000 00001000
        int m = ~n + 1;
//	~n=   11111111 11111111 11111111 11110111	-9
//	~n+1= 11111111 11111111 11111111 11111000	-8

        System.out.println(m);

        int a = Integer.MIN_VALUE;
//		n=10000000 00000000 00000000 00000000
        int b = ~a + 1;
//	~n=   01111111 11111111 11111111 11111111	Integer.MAX_VALUE
//	~n+1= 10000000 00000000 00000000 00000000	Integer.MIN_VALUE

        System.out.println(b);
    }
}
