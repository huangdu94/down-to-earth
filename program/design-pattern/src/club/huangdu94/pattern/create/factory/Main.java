package club.huangdu94.pattern.create.factory;

import club.huangdu94.pattern.create.factory.food.IFood;

/**
 * 工厂模式
 * 适用于以下场景：
 * 1. 创建对象需要大量重复的代码
 * 2. 客户端（应用层）不依赖于产品类实例如何被创建、如何被实现等细节
 * 3. 一个类通过其子类来指定创建哪个对象
 * 缺点：
 * 1. 类的个数容易过多，增加复杂度
 * 2. 增加了系统的抽象性和理解难度
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/5/16
 */
public class Main {
    public static void main(String[] args) {
        IFoodFactory breadFactory = new BreadFactory();
        IFoodFactory milkFactory = new MilkFactory();
        IFoodFactory hamFactory = new HamFactory();
        IFood bread = breadFactory.getFood();
        IFood milk = milkFactory.getFood();
        IFood ham = hamFactory.getFood();
        bread.eat();
        milk.eat();
        ham.eat();
    }
}
