package club.huangdu94.pattern.behavior.strategy;

/**
 * @author duhuang@iflytek.com
 * @version 2020/11/29 23:12
 */
public class OperationAdd implements Strategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}
