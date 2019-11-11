package object_integer;

import java.io.Serializable;

/**
 * Point 设计目的：表示直角坐标系上的一个点
 * 特征：x,y两个值
 * <p>
 * 使用这个类测试作为Object子类对相关方法的重写原则.
 * <p>
 * java中所有的类都继承自Object，当一个类没有使用extends显示继承任何一个类时，编译器在编译该类后默认会让其继承自Object.
 * <p>
 * 实现了两个标识接口：
 * Cloneable 可克隆
 * Serializable 可序列化
 *
 * @author Bean
 */
public class Point implements Cloneable, Serializable {
    private int x;
    private int y;

    public Point() {

    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * 当一个对象的toString被使用，那么就应当重写该方法.因为Object提供的该方法返回的句柄对实际开发的价值不大.
     * 重写toString方法的原则是返回的字符串中应当包含当前对象需要让外界了解的属性信息.格式没有要求，将来按照实际工作中的需求定.
     */
    public String toString() {
        return getClass().getName() + "[x=" + x + ",y=" + y + "]";
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof Point) {
            Point p = (Point) obj;
            return p.x == this.x && p.y == this.y;
        } else {
            return false;
        }
    }

    /**
	 * 需要实现Cloneable接口否则clone会报CloneNotSupportedException
     * 重写克隆方法
	 * 需要调用Object的clone()
     *
     * @return Clone的对象
     */
    @Override
    public Point clone() {
        Point p = null;
        try {
            p = (Point) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return p;
    }

	/**
	 * 对象被回收前调用的最后一个方法(jdk1.9之后机制改变)
	 * @throws Throwable
	 */
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
	}
}
