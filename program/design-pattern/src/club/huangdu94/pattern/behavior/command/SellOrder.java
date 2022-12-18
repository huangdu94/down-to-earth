package club.huangdu94.pattern.behavior.command;

/**
 * 卖命令
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/29 21:07
 */
public class SellOrder implements Order {
    private Stock abcStock;

    public SellOrder(Stock abcStock) {
        this.abcStock = abcStock;
    }

    @Override
    public void execute() {
        abcStock.sell();
    }
}
