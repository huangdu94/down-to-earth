package club.huangdu94.pattern.behavior.template;

/**
 * 游戏模板
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/29 23:21
 */
public abstract class Game {
    abstract void initialize();

    abstract void startPlay();

    abstract void endPlay();

    //模板
    public final void play() {
        //初始化游戏
        initialize();
        //开始游戏
        startPlay();
        //结束游戏
        endPlay();
    }
}
