package autocloseable;

/**
 * 实现AutoCloseable或者Closeable(其继承了AutoCloseable)接口的资源
 * 可以结合try-with-resource机制使用自动调用close()方法
 *
 * @author duhuang@iflytek
 * @version 2019/11/11 10:08
 */
public class AutoCloseableDemo {
    public static void main(String[] args) {
        try (MyResourse myResourse1 = new MyResourse(); MyResourse myResourse2 = new MyResourse()) {
            myResourse1.run();
            myResourse2.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}