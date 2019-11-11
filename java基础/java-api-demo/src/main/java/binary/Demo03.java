package binary;
/**
 * 该例子展示了计算机中负数用二进制的表示方法
 * @author soft01
 *
 */
public class Demo03 {

	public static void main(String[] args) {
//		int i=-3;
//		System.out.println(Integer.toBinaryString(i));
////		11111111111111111111111111111101
		
//		long l=-1l;
//		System.out.println(Long.toBinaryString(l));
//		
//		int j=-15;
//		System.out.println(Integer.toBinaryString(j));
//		
		for(int i=0;i>=-50;i--){
			System.out.println(i+":"+Integer.toBinaryString(i));
		}
		
	}
}
