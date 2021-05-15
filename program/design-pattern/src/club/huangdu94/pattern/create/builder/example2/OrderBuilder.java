package club.huangdu94.pattern.create.builder.example2;

import club.huangdu94.pattern.create.builder.example2.item.impl.ChickenBurger;
import club.huangdu94.pattern.create.builder.example2.item.impl.Coke;
import club.huangdu94.pattern.create.builder.example2.item.impl.Pepsi;
import club.huangdu94.pattern.create.builder.example2.item.impl.VegBurger;

/**
 * 建造类(建造订单)
 *
 * @author duhuang@iflytek
 * @date 2019/10/31 15:22
 */
public class OrderBuilder implements IBuilder {
    private Order order;

    public OrderBuilder() {
        order = new Order();
    }

    @Override
    public OrderBuilder orderChickenBurger() {
        order.addItem(new ChickenBurger());
        return this;
    }

    @Override
    public OrderBuilder orderVegBurger() {
        order.addItem(new VegBurger());
        return this;
    }

    @Override
    public OrderBuilder orderPepsi() {
        order.addItem(new Pepsi());
        return this;
    }

    @Override
    public OrderBuilder orderCoke() {
        order.addItem(new Coke());
        return this;
    }

    public Order toOrder() {
        return order;
    }
}
