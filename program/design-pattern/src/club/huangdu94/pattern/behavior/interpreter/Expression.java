package club.huangdu94.pattern.behavior.interpreter;

/**
 * 表达式接口，封装一个单一的规则
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/29 21:32
 */
public interface Expression {
    boolean interpret(String context);
}
