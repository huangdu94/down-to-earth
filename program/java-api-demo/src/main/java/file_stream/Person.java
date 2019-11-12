package file_stream;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 使用该类测试对象流对java中对象的读写操作
 * 若一个类的实例希望被对象流读写，那么该类必须实现
 * 接口：java.io.Serializable
 *
 * @author Bean
 */
public class Person implements Serializable {
    /**
     * 当一个类实现了Serializable接口后，编译器会提示
     * 应当添加一个常量：serialVersionUID
     * 这个常量标识当前类的序列化版本号.若不指定编译器
     * 在编译当前类时会根据当前类的结构生成，但是只要类的
     * 结构发生了变化，那么版本号就会改变.
     * <p>
     * 版本号决定着一个对象是否可以反序列化成功.
     * 当对象输入流在将一个对象进行反序列化时会检查该
     * 对象的版本号与当前程序中这个类的版本号是否一致？
     * 若一致：反序列化成功，若当前类发生了改变，那么则
     * 还原依然存在的属性.
     * 若不一致，则反序列化方法readObject会抛出异常.
     */
    public static final long serialVersionUID = 1L;
    private String name;
    private int age;
    private String gender;
    /*
     * 当一个属性被transient修饰后，该属性在进行
     * 对象序列化时其值会被忽略.
     * 忽略不必要的属性值可以达到对象序列化"瘦身"的效果.
     */
    private transient String[] otherInfo;

    public Person() {
    }

    public Person(String name, int age, String gender, String[] otherInfo) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.otherInfo = otherInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String[] getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String[] otherInfo) {
        this.otherInfo = otherInfo;
    }

    public String toString() {
        return "name:" + name + ",age:" + age + ",gender:" + gender + ",otherInfo:" + Arrays.toString(otherInfo);
    }
}
