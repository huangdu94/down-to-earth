package club.huangdu94.pattern.behavior.interpreter;

/**
 * 且
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/29 21:34
 */
public class AndExpression implements Expression {
    private Expression expr1 = null;
    private Expression expr2 = null;

    public AndExpression(Expression expr1, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public boolean interpret(String context) {
        return expr1.interpret(context) && expr2.interpret(context);
    }
}
