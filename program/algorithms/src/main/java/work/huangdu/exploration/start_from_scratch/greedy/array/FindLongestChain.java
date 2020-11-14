package work.huangdu.exploration.start_from_scratch.greedy.array;

import java.util.Arrays;

/**
 * 646. 最长数对链
 * 给出 n 个数对。 在每一个数对中，第一个数字总是比第二个数字小。
 * 现在，我们定义一种跟随关系，当且仅当 b < c 时，数对(c, d) 才可以跟在 (a, b) 后面。我们用这种形式来构造一个数对链。
 * 给定一个数对集合，找出能够形成的最长数对链的长度。你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。
 * 示例：
 * 输入：[[1,2], [2,3], [3,4]]
 * 输出：2
 * 解释：最长的数对链是 [1,2] -> [3,4]
 * 提示：
 * 给出数对的个数在 [1, 1000] 范围内。
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/14 11:22
 */
public class FindLongestChain {
    public int findLongestChain(int[][] pairs) {
        // 排序，先按end升序排序，如果end相同再按start降序排序（这样排序的意义是保证前面的元素是优先用到的）
        Arrays.sort(pairs, (o1, o2) -> o1[1] == o2[1] ? o2[0] - o1[0] : o1[1] - o2[1]);
        // int n = pairs.length, end = Integer.MIN_VALUE, count = 0, i = 0;
        int n = pairs.length, end = pairs[0][1], count = 1, i = 1;
        while (i < n) {
            while (i < n && pairs[i][0] <= end) {
                i++;
            }
            if (i < n) {
                end = pairs[i][1];
                count++;
            }
        }
        return count;
    }
}
