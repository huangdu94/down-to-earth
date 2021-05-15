package club.huangdu94.pattern.behavior.null_object;

/**
 * 在没有满足条件的值的时候，也返回一个对象，避免返回null，可能造成NullPointerException
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/29 22:57
 */
public class NullCustomer extends AbstractCustomer {
    @Override
    public String getName() {
        return "Not Available in Customer Database";
    }

    @Override
    public boolean isNil() {
        return true;
    }
}
