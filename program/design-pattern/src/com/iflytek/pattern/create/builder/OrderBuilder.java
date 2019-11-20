package com.iflytek.pattern.create.builder;

import com.iflytek.pattern.create.builder.item.impl.ChickenBurger;
import com.iflytek.pattern.create.builder.item.impl.Coke;
import com.iflytek.pattern.create.builder.item.impl.Pepsi;
import com.iflytek.pattern.create.builder.item.impl.VegBurger;

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

    public OrderBuilder orderChickenBurger() {
        order.addItem(new ChickenBurger());
        return this;
    }

    public OrderBuilder orderVegBurger() {
        order.addItem(new VegBurger());
        return this;
    }

    public OrderBuilder orderPepsi() {
        order.addItem(new Pepsi());
        return this;
    }

    public OrderBuilder orderCoke() {
        order.addItem(new Coke());
        return this;
    }

    public Order toOrder() {
        return order;
    }
}
