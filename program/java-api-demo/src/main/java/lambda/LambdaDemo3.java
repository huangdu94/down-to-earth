package lambda;

/**
 * @author Bean
 */
public class LambdaDemo3 {
    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(i);
            }
        }
        ).start();
    }
}
