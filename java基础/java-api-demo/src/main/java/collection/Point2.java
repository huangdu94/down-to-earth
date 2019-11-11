package collection;
/**
 * 使用当前类作为集合元素测试集合对自定义类型元素的排序
 * @author Bean
 */
public class Point2 implements Comparable<Point2>{
	private int x;
	private int y;
	
	public Point2(int x,int y) {
		this.x=x;
		this.y=y;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x=x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y=y;
	}
	
	public String toString() {
		return "("+x+","+y+")";
	}
	
	public boolean equals(Object obj) {
		if(obj==null) {
			return false;
		}
		if(obj==this) {
			return true;
		}
		if(obj instanceof Point2) {
			Point2 p=(Point2)obj;
			return p.x==this.x&&p.y==this.y;
		}else {
			return false;
		}
	}
	/**
	 * 当一个类实现了Comparable接口后，就要求必须重写方法:compareTo
	 * 该方法的作用是用当前对象（this）与参数对象o比较大小.
	 * 返回值是一个整数，返回值并不关注具体取值,而关注的是取值范围:
	 * 当返回值>0:即表示当前对象大于参数对象（this>o)
	 * 当返回值<0:即表示当前对象小于参数对象
	 * 当返回值=0:两个对象相等
	 */
	public int compareTo(Point2 o) {
		int value=this.x*this.x+this.y*this.y;
		int oValue=o.x*o.x+o.y*o.y;
		return value-oValue;
	}
}
