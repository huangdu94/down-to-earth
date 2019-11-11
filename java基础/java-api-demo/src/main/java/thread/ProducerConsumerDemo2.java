package thread;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 2019/7/4 18:00
 * 生产者、消费者模式
 *
 * @author DuHuang
 */
public class ProducerConsumerDemo2 {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition empty = lock.newCondition();
        Condition full = lock.newCondition();
        List<Integer> list = new LinkedList();//仓库
        int max = 1000;//仓库大小
        Producer2 p = new Producer2("生产者", max, list, lock, empty, full);
        Consumer2 c = new Consumer2("消费者", max, list, lock, empty, full);

        new Thread(p::produce).start();
        new Thread(c::consume).start();
    }
}

/**
 * 生产者
 */
class Producer2 {
    private String userName;
    private int max;
    private List<Integer> list;
    private Lock lock;
    private Condition empty;
    private Condition full;

    public Producer2(String userName, int max, List<Integer> list, Lock lock, Condition empty, Condition full) {
        this.userName = userName;
        this.max = max;
        this.list = list;
        this.lock = lock;
        this.empty = empty;
        this.full = full;
    }

    public void produce() {
        while (true) {
            lock.lock();
            try {
                while (list.size() >= max) {
                    System.out.println("当前仓库产品数量:" + list.size() + ",仓库满了.生产者等待.");
                    full.await();
                }
                int product = (int) (Math.random() * 100) + 1;
                list.add(product);
                System.out.println(this.userName + "生产了产品:" + product + ",当前仓库产品数量:" + list.size());
                empty.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}

/**
 * 消费者
 */
class Consumer2 {
    private String userName;
    private int max;
    private List<Integer> list;
    private Lock lock;
    private Condition empty;
    private Condition full;

    public Consumer2(String userName, int max, List<Integer> list, Lock lock, Condition empty, Condition full) {
        this.userName = userName;
        this.max = max;
        this.list = list;
        this.lock = lock;
        this.empty = empty;
        this.full = full;
    }

    public void consume() {
        while (true) {
            lock.lock();
            try {
                while (list.isEmpty()) {
                    System.out.println("仓库空了,消费者等待.");
                    empty.await();
                }
                int goods = list.remove(0);
                System.out.println(this.userName + "消费了商品:" + goods + ",当前仓库产品数量:" + list.size());
                full.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}