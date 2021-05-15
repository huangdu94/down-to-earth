package club.huangdu94.pattern.behavior.command;

/**
 * 命令模式
 * 命令模式（Command Pattern）是一种数据驱动的设计模式，它属于行为型模式。请求以命令的形式包裹在对象中，并传给调用对象。调用对象寻找可以处理该命令的合适的对象，并把该命令传给相应的对象，该对象执行命令。
 * 在软件系统中，行为请求者与行为实现者通常是一种紧耦合的关系，但某些场合，比如需要对行为进行记录、撤销或重做、事务等处理时，这种无法抵御变化的紧耦合的设计就不太合适。
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/29 21:09
 */
public class Main {
    public static void main(String[] args) {
        Stock abcStock = new Stock();

        BuyOrder buyStockOrder = new BuyOrder(abcStock);
        SellOrder sellStockOrder = new SellOrder(abcStock);

        Broker broker = new Broker();
        broker.takeOrder(buyStockOrder);
        broker.takeOrder(sellStockOrder);

        broker.placeOrders();
    }
}
