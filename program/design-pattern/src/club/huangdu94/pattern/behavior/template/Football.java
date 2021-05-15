package club.huangdu94.pattern.behavior.template;

/**
 * @author duhuang@iflytek.com
 * @version 2020/11/29 23:22
 */
public class Football extends Game {
    @Override
    void endPlay() {
        System.out.println("Football Game Finished!");
    }

    @Override
    void initialize() {
        System.out.println("Football Game Initialized! Start playing.");
    }

    @Override
    void startPlay() {
        System.out.println("Football Game Started. Enjoy the game!");
    }
}
