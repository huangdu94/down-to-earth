package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Callable
 * FutureTask RunnableFuture Runnable Future
 * 线程类实现Callable接口call()方法，允许设置一个返回值
 * 该线程类作为参数构造FutureTask对象，该类实现了RunnableFuture接口，该接口继承了Runnable和Future接口
 * Future接口里有get()方法可以等待call()执行完之后获取其返回值
 * FutureTask对象作为Runnable构造Thread对象
 * 通过Thread对象的start()方法执行线程
 *
 * @author duhuang@iflytek.com
 * @version 2019/11/11 19:39
 */
public class CallableDemo {
    public static void main(String[] args) {
        MyCallable myCallable = new MyCallable();
        FutureTask<String> futureTask = new FutureTask<>(myCallable);
        new Thread(futureTask).start();
        new Thread(() -> {
            try {
                String back = futureTask.get();
                System.out.println(back);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }).start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myCallable.setClose(true);
    }
}

class MyCallable implements Callable<String> {
    private boolean close = false;

    public boolean isClose() {
        return close;
    }

    public void setClose(boolean close) {
        this.close = close;
    }

    @Override
    public String call() {
        while (!isClose()) {
            try {
                System.out.println("MyCallable正在执行...");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "MyCallable执行完了!";
    }
}
