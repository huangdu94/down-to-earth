package annotation;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/11/25
 */
@MyAnnotation
public class Test<@MyAnnotation T> {
    @MyAnnotation
    private T hello;

    @MyAnnotation
    public Test(@MyAnnotation T hello) {
        this.hello = hello;
    }

    public static void main(@MyAnnotation String[] args) {

    }
}