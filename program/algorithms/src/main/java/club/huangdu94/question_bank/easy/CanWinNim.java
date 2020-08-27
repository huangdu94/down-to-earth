package club.huangdu94.question_bank.easy;

/**
 * 292. Nim 游戏
 * 你和你的朋友，两个人一起玩 Nim 游戏：桌子上有一堆石头，每次你们轮流拿掉 1 - 3 块石头。 拿掉最后一块石头的人就是获胜者。你作为先手。
 * 你们是聪明人，每一步都是最优解。 编写一个函数，来判断你是否可以在给定石头数量的情况下赢得游戏。
 * 示例:
 * 输入: 4
 * 输出: false
 * 解释: 如果堆中有 4 块石头，那么你永远不会赢得比赛；
 * 因为无论你拿走 1 块、2 块 还是 3 块石头，最后一块石头总是会被你的朋友拿走。
 *
 * @author duhuang@iflytek.com
 * @version 2020/8/27 18:53
 */
public class CanWinNim {
    /*
    获胜方式：
    1. 如果石头数量小于4个则直接拿完胜利；
    2. 只有4个石头的时候必输；
    3. 轮到对方拿石头的时候，留4个石头必赢，那么轮到自己的时候5,6,7必赢
    4. 只有8个的时候必输
    5. 只有9,10,11的时候必赢
    6. 只有12个的时候必输
    ......
     */
    public boolean canWinNim(int n) {
        //if (n <= 0) return n != 0;
        return n % 4 != 0;
    }
}
