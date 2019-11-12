package classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

/**
 * 使用反射机制执行指定类的指定方法
 *
 * @author Bean
 */
public class ClassDemo2 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        /*
         * 执行Person类的sayHello方法
         */
//		Person p=new Person();
//		p.sayHello();

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入您想实例化的类：");
        String className = scanner.nextLine();
        System.out.println("请输入您想调用的" + className + "哪个方法：");
        String methodName = scanner.nextLine();
        /*
         * 反射机制实现
         * 1：加载Person类
         * 2：实例化该类的实例
         * 3：调用相应的方法
         */
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        Class<?> c = loader.loadClass(className);
        /*
         *Class提供了创建对应类实例的方法：
         *Object new Instance()
         *该方法会调用Class所表示的类的无参构造方法来实例化该对象
         */
        Object o = c.newInstance();
        /*
         * Class提供了获取指定名字和对应参数列表方法
         * Method getDeclaredMethod(String name,Class[] class)
         * 第一个参数为方法的名字
         * 第二个参数是一个Class数组，表示参数列表.若该方法没有参数，那么这个数组可以为null.
         */
        Method m = c.getDeclaredMethod(methodName, null);
        /*
         * Method类的每一个实例用于表示一个类中的一个方法.
         * Method的常用方法：
         * invoke(Object o,Object[] paras)
         * 该方法是用来调用当前Method方法.
         * 参数1：调用给定对象o的这个方法
         * 参数2：若Method所表示的该方法有参数，则该数组表示实际需要传入的参数.若该方法没有参数，则传入null即可
         */
        /*
         * 若Method表示的是一个私有方法
         * 需要开放权限
         * 这样会破坏程序设计中的封装性，慎用.
         */
        //判断是否为私有方法
        if (m.getModifiers() == Modifier.PRIVATE) {
            System.out.println(m.getName() + "是一个私有方法，已为你开启专属通道.");
            m.setAccessible(true);
        }

        Object returnValue = m.invoke(o, null);
        System.out.println("返回值：" + returnValue);
    }
}
