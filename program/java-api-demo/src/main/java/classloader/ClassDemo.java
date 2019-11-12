package classloader;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * java反射机制
 * 反射机制允许我们在程序运行期间动态获取一个类的信息，并实例化.
 * 通过反射机制还可以查看某个指定类的信息（有哪些属性哪些方法等），并可以动态调用它们.
 * java流行的框架中都用到了反射机制.
 * 有了反射机制，可以让程序执行代码从编码期确定调用关系转变为在运行期确定.大大提高了代码执行的灵活性.
 *
 * @author Bean
 */
public class ClassDemo {
    public static void main(String[] args) throws ClassNotFoundException {
//		Person p=new Person();
//		p.sayHello();
//		Class p=Teacher.class;
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入您想实例化的类：");
        String line = scanner.nextLine();

        //1创建类加载器，用于加载需要实例化的类的class文件
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        /*
         * Class类的每一个实例用于表示JVM加载的一个类的信息
         * 每个被JVM加载的类都有且只有一个Class的实例与之对应
         * 通过Class的实例可以获取它表示的这个类的相关信息：
         * 属性，方法等都可以获取到
         */
        Class c = loader.loadClass(line);
        System.out.println(line + "已实例化完毕!");
        System.out.println(c.getName());

        /*
         * Class定义的方法：
         * String getName()
         * 获取当前Class所表示的类的名字
         *
         * Method[] getMethods()
         * 获取当前Class所表示的类的所有方法（自己定义的和从父类继承的,不包括私有的)
         *
         * Method[] getDeclaredMethods()
         * 获取当前Class所表示的类自己定义的方法不含有从父类继承下来的(包括私有的)
         */

        Method[] methods = c.getMethods();
        System.out.println(c.getName() + "有" + methods.length + "个方法.");
        for (Method m : methods) {
            System.out.println(m.getName());
        }

        /*
         * 获取属性信息
         */
        Field[] fields = c.getDeclaredFields();
        System.out.println(c.getName() + "有" + fields.length + "个属性.");
        for (Field field : fields) {
            System.out.println(field.getName());
        }
    }
}
