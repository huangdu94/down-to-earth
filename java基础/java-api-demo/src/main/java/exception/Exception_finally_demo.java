package exception;
/**
 * 请分别描述final finally finalize
 * 
 * finalize是Object定义的一个方法，所以所有的类
 * 都具有该方法.该方法是GC在释放一个实例的时候
 * 调用的，这意味着该方法调用完毕这个对象就会被释放了.
 * 
 * 如果要重写finalize方法，注意finalize中不能进行
 * 耗费时间的操作.否则GC会卡在那里.
 * @author Bean
 *
 */
public class Exception_finally_demo {
	public static void main(String[] args) {
		System.out.println(test("0")+","+test("")+","+test(null));
	}
	public static char test(String str) {
		try {
			System.out.println("try");
			return str.charAt(0);
		}catch(StringIndexOutOfBoundsException e) {
			System.out.println("下标越界了");
			return '1';
		}catch(Exception e) {
			System.out.println("其它异常");
			return '2';
		}finally {
			//fjnally中不应当做返回结果操作
//			System.out.println("我说了算");
//			return '3';
		}
	}
}