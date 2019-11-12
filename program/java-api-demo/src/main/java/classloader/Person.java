package classloader;

public class Person {
    public void sayHello() {
        System.out.println("hello!!");
    }

    public void sayName() {
        System.out.println("xiaoze.");
    }

    public void sayInfo(String info) {
        System.out.println("这个参数是：" + info);
    }

    public void sayInfo(String name, int age) {
        System.out.println("我的名字是：" + name + "，我今年" + age + "岁");
    }

    private void sayHa() {
        System.out.println("我是私有方法，哈哈哈哈");
    }
}
