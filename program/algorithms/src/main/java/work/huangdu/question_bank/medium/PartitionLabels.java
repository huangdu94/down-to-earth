package work.huangdu.question_bank.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 763. 划分字母区间
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。
 * 示例 1：
 * 输入：S = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 * 提示：
 * S的长度在[1, 500]之间。
 * S只包含小写字母 'a' 到 'z' 。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/10/22 12:31
 */
public class PartitionLabels {
    public List<Integer> partitionLabels(String S) {
        List<Integer> res = new ArrayList<>();
        int n = S.length(), start = 0, count = 0;
        int[] lastIndex = new int[26];
        boolean[] exist = new boolean[26];
        Arrays.fill(lastIndex, -1);
        // 1. 记录每一个字母最后出现的index
        for (int i = 0; i < n; i++) {
            lastIndex[S.charAt(i) - 'a'] = i;
        }
        // 2. 逻辑部分
        for (int i = 0; i < n; i++) {
            int k = S.charAt(i) - 'a';
            if (lastIndex[k] == i) {
                if (exist[k]) {
                    --count;
                }
                if (count == 0) {
                    res.add(i - start + 1);
                    Arrays.fill(exist, false);
                    start = i + 1;
                }
            } else if (!exist[k]) {
                exist[k] = true;
                count++;
            }
        }
        // 3. 返回结果
        return res;
    }

    public static void main(String[] args) {
        PartitionLabels labels = new PartitionLabels();
        System.out.println(labels.partitionLabels(
                "ababcbacadefegdehijhklij"));
    }
}
