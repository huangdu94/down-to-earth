package club.huangdu94.pattern.create.factory;

import club.huangdu94.pattern.create.factory.food.IFood;
import club.huangdu94.pattern.create.factory.food.impl.Bread;
import club.huangdu94.pattern.create.factory.food.impl.Ham;
import club.huangdu94.pattern.create.factory.food.impl.Milk;

/**
 * 简单工厂模式
 * 此为简单工厂
 * 另一种 工厂模式 将工厂抽象出一个接口，每一个工厂实现类单独生产一种产品
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com) 2019/10/31 10:31
 */
public class Main {
    public static void main(String[] args) {
        FoodFactory foodFactory = new FoodFactory();
        IFood food1 = foodFactory.getFood("bread");
        IFood food2 = foodFactory.getFood("milk");
        IFood food3 = foodFactory.getFood("ham");
        food1.eat();
        food2.eat();
        food3.eat();

        IFood bread = foodFactory.getFood(Bread.class);
        IFood milk = foodFactory.getFood(Milk.class);
        IFood ham = foodFactory.getFood(Ham.class);
        bread.eat();
        milk.eat();
        ham.eat();
    }
}
