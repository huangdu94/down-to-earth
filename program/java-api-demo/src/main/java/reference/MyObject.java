package reference;

/**
 * MyObject
 *
 * @author duhuang@iflytek
 * @version 2019/11/20 15:12
 */
public class MyObject {
    @Override
    public void finalize() throws Throwable {
        super.finalize();
        // 被回收时输出
        System.out.println("MyObject's finalize called");
    }

    @Override
    public String toString() {
        return "I am MyObject.";
    }
}
