package club.huangdu94.pattern.behavior.command;

import java.util.ArrayList;
import java.util.List;

/**
 * 命令调用类
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/29 21:06
 */
public class Broker {
    private List<Order> orderList = new ArrayList<Order>();

    public void takeOrder(Order order) {
        orderList.add(order);
    }

    public void placeOrders() {
        for (Order order : orderList) {
            order.execute();
        }
        orderList.clear();
    }
}
