package com.iflytek.pattern.create.builder;

/**
 * 建造类接口
 * 理论上建造者类的方法需要先抽象为接口
 * 不过在此例子中没有体现接口的优势
 * 可以参照https://www.jianshu.com/p/3d1c9ffb0a28中建造自行车的例子
 *
 * @author duhuang@iflytek
 * @date 2019/10/31 15:40
 */
public interface IBuilder {
    IBuilder orderChickenBurger();

    IBuilder orderVegBurger();

    IBuilder orderPepsi();

    IBuilder orderCoke();

    Order toOrder();
}
