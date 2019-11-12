package thread;

import java.util.LinkedList;
import java.util.List;

/**
 * 2019/7/4 18:00
 * 生产者、消费者模式
 * @author DuHuang
 */
public class ProducerConsumerDemo {
    public static void main(String[] args) {
        List<Integer> list=new LinkedList();//仓库
        int max=100;//仓库大小
        Producer p=new Producer("生产者",max,list);
        Consumer c=new Consumer("消费者",max,list);

        new Thread(p::produce).start();
        new Thread(c::consume).start();
    }
}

/**
 * 生产者
 */
class Producer{
    private String userName;
    private int max;
    private List<Integer> list;
    public Producer(String userName,int max,List<Integer> list){
        this.userName=userName;
        this.max=max;
        this.list=list;
    }
    public void produce(){
        while(true){
            synchronized (list){
                while(list.size()>=max){
                    System.out.println("当前仓库产品数量:"+list.size()+",仓库满了.生产者等待.");
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int product=(int)(Math.random()*100)+1;
                list.add(product);
                System.out.println(this.userName+"生产了产品:"+product+",当前仓库产品数量:"+list.size());
                list.notifyAll();
            }
        }
    }
}

/**
 * 消费者
 */
class Consumer{
    private String userName;
    private int max;
    private List<Integer> list;
    public Consumer(String userName,int max,List<Integer> list){
        this.userName=userName;
        this.max=max;
        this.list=list;
    }
    public void consume(){
        while (true) {
            synchronized (list){
                while(list.isEmpty()){
                    System.out.println("仓库空了,消费者等待.");
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int goods=list.remove(0);
                System.out.println(this.userName+"消费了商品:"+goods+",当前仓库产品数量:"+list.size());
                list.notifyAll();
            }
        }
    }
}