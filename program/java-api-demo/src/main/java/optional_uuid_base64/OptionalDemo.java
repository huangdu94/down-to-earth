package optional_uuid_base64;

import java.util.Optional;

/**
 * @author DuHuang
 * @date 2019/11/4 20:30
 */
public class OptionalDemo {
    public static void main(String[] args) {
        //获取空的Optional实例
        Optional<Object> objectOptional = Optional.empty();
        //获取一个存有值得Optional实例 如果存入null则抛出NullPointerException
        //Optional<Object> objectOptional1 =Optional.of(null);
        //允许存入null如果存入null则相当于empty()
        Optional<Object> objectOptional2 =Optional.ofNullable(null);
        //System.out.println(.of(new Object()));
        //获取Optional内存入的值，如果为null则抛出NoSuchElementException
        //System.out.println(objectOptional2.get());
        //获取Optional内存入的值，如果为null则使用替代值
        System.out.println(objectOptional2.orElse(new Object()));
    }
}
