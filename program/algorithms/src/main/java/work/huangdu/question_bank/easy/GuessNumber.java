package work.huangdu.question_bank.easy;

/**
 * 374. 猜数字大小
 * 猜数字游戏的规则如下：
 * 每轮游戏，系统都会从 1 到 n 随机选择一个数字。 请你猜选出的是哪个数字。
 * 如果你猜错了，系统会告诉你，你猜测的数字比系统选出的数字是大了还是小了。
 * 你可以通过调用一个预先定义好的接口 guess(int num) 来获取猜测结果，返回值一共有 3 种可能的情况（-1，1 或 0）：
 * -1 : 你猜测的数字比系统选出的数字大
 * 1 : 你猜测的数字比系统选出的数字小
 * 0 : 恭喜！你猜对了！
 * 示例 :
 * 输入: n = 10, pick = 6
 * 输出: 6
 * *Forward declaration of guess API.
 * *@param  num   your guess
 * *@return 	     -1 if num is lower than the guess number
 * *			      1 if num is higher than the guess number
 * *              otherwise return 0
 * *int guess(int num);
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/9/5 22:14
 */
public class GuessNumber {

    private int guess(int num) {
        return 0;
    }

    public int guessNumber(int n) {
        int l = 1, r = n;
        while (true) {
            int m = l + ((r - l) >> 1);
            int res = guess(m);
            if (res == 0) {
                return m;
            } else if (res == 1) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
    }
}
