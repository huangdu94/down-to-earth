package collection;
/**
 * 泛型 JDK1.5后推出的一个新特性
 * 泛型又称为参数化类型，允许使用方在实例化对象时传入具体类型来决定当前类中泛型的实际类型.
 * @author Bean
 * @param <E>
 */
public class Point<E> {
	private E x;
	private E y;
	public Point(E x,E y) {
		this.x=x;
		this.y=y;
	}
	public E getX() {
		return x;
	}
	public void setX(E x) {
		this.x = x;
	}
	public E getY() {
		return y;
	}
	public void setY(E y) {
		this.y = y;
	}
	
	public String toString() {
		return "("+x+","+y+")";
	}
}
