package com.iflytek.pattern.behavior.command;

/**
 * 买命令
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/29 21:07
 */
public class BuyOrder implements Order {
    private Stock abcStock;

    public BuyOrder(Stock abcStock) {
        this.abcStock = abcStock;
    }

    @Override
    public void execute() {
        abcStock.buy();
    }
}
