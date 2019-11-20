package com.iflytek.pattern.create.builder;

import com.iflytek.pattern.create.builder.item.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * 被建造的东西(订单)
 *
 * @author duhuang@iflytek
 * @date 2019/10/31 15:17
 */
public class Order {
    private List<Item> items = new ArrayList<>();

    public void addItem(Item i) {
        items.add(i);
    }

    public double getCost() {
        double cost = 0.0;
        if (items.isEmpty()) {
            return cost;
        }
        for (Item i : items) {
            cost += i.price();
        }
        return cost;
    }

    public void showItems() {
        if (items.isEmpty()) {
            System.out.println("Items is empty.");
        }
        for (Item i : items) {
            System.out.print("Item: " + i.name());
            System.out.print(",Packing: " + i.packing().pack());
            System.out.println(",price: " + i.price());
        }
    }
}
