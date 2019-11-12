package regex;

/**
 * 和谐用语
 *
 * @author Bean
 */
public class Test02 {
    public static void main(String[] args) {
        String regex = "(wqnmlgb|nc|mazz|mmp|dsb|tmd)";
        String message = "wqnmlgb!你怎么这么的nc!";
        message = message.replaceAll(regex, "***");
        System.out.println(message);
    }
}
