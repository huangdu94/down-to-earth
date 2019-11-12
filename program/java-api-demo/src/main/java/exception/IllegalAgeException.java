package exception;

/**
 * 年龄不合法异常
 * <p>
 * 自定义异常
 * 通常自定义异常是用来声明一个业务逻辑的异常.
 *
 * @author Bean
 */
public class IllegalAgeException extends Exception {
    private static final long serialVersionUID = 1L;

    public IllegalAgeException() {
        super();
    }

    public IllegalAgeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public IllegalAgeException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalAgeException(String message) {
        super(message);
    }

    public IllegalAgeException(Throwable cause) {
        super(cause);
    }
}
