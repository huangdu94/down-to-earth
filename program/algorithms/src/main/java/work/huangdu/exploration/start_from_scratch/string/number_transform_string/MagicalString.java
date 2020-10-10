package work.huangdu.exploration.start_from_scratch.string.number_transform_string;

/**
 * 481. 神奇字符串
 * 神奇的字符串 S 只包含 '1' 和 '2'，并遵守以下规则：
 * 字符串 S 是神奇的，因为串联字符 '1' 和 '2' 的连续出现次数会生成字符串 S 本身。
 * 字符串 S 的前几个元素如下：S = “1221121221221121122 ......”
 * 如果我们将 S 中连续的 1 和 2 进行分组，它将变成：
 * 1 22 11 2 1 22 1 22 11 2 11 22 ......
 * 并且每个组中 '1' 或 '2' 的出现次数分别是：
 * 1 2 2 1 1 2 1 2 2 1 2 2 ......
 * 你可以看到上面的出现次数就是 S 本身。
 * 给定一个整数 N 作为输入，返回神奇字符串 S 中前 N 个数字中的 '1' 的数目。
 * 注意：N 不会超过 100,000。
 * 示例：
 * 输入：6
 * 输出：3
 * 解释：神奇字符串 S 的前 6 个元素是 “12211”，它包含三个 1，因此返回 3。
 *
 * @author duhuang@iflytek.com
 * @version 2020/9/28 22:07
 */
public class MagicalString {
    public int magicalString(int n) {
        if (n <= 0) return 0;
        if (n <= 3) return 1;
        int[] ms = new int[n + 1];
        ms[0] = 1;
        ms[1] = 2;
        ms[2] = 2;
        int all = 1, i = 2, last = 2, input = 3;
        while (input < n) {
            int count = ms[i++];
            last = (last & 1) + 1;
            ms[input++] = last;
            if (count == 1) {
                if (last == 1) all++;
            } else {
                ms[input++] = last;
                if (last == 1) {
                    all += input > n ? 1 : 2;
                }
            }
        }
        return all;
    }

    public static void main(String[] args) {
        MagicalString string = new MagicalString();
        System.out.println(string.magicalString(5));
    }
}
