package collection;
/**
 * 泛型是编译器认可，泛型可以不指定，若不指定则为原型Object.
 * 泛型只是用来告知编译器，让编译器可以在编译时检查实参是否符合泛型要求，当获取一个泛型值时，编译器也会根据泛型的实际类型做自动转换操作. 
 * @author Bean
 */
public class TestPoint2 {
	public static void main(String[] args) {
		Point<Integer> p1=new Point<Integer>(1,2);
		System.out.println("p1:"+p1);
		
		p1.setX(2);
		System.out.println("p1:"+p1);
		int x1=p1.getX();
		System.out.println("x1:"+x1);
		
//		Point p2=p1;//不指定泛型则为原型Object
//		System.out.println("p2:"+p2);
//		p2.setX("二");
//		System.out.println("p2:"+p2);
//		
//		x1=p1.getX();//类型造型异常!
//		System.out.println("x1:"+x1);
	}
}
