package club.huangdu94.pattern.behavior.chain_of_responsibility;

/**
 * @author duhuang@iflytek.com
 * @version 2020/11/29 20:49
 */
public class FileLogger extends AbstractLogger {
    public FileLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("File::Logger: " + message);
    }
}
