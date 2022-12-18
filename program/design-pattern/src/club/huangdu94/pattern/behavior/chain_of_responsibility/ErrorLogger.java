package club.huangdu94.pattern.behavior.chain_of_responsibility;

/**
 * @author duhuang@iflytek.com
 * @version 2020/11/29 20:49
 */
public class ErrorLogger extends AbstractLogger {
    public ErrorLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Error Console::Logger: " + message);
    }
}
