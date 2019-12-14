package jvm;

/**
 * 默认       23374
 * -Xss1m     50872
 *
 * @author duhuang@iflytek.com
 * @version 2019/12/14 11:55
 */
public class TestStack {
    private static int count = 0;

    private static void recursion() {
        count++;
        recursion();
    }

    public static void main(String[] args) {
        try {
            recursion();
        } catch (StackOverflowError e) {
            System.out.println(count);
            e.printStackTrace();
        }
    }
}
