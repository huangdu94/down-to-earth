package work.huangdu.question_bank.medium;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 1202. 交换字符串中的元素
 * 给你一个字符串 s，以及该字符串中的一些「索引对」数组 pairs，其中 pairs[i] = [a, b] 表示字符串中的两个索引（编号从 0 开始）。
 * 你可以 任意多次交换 在 pairs 中任意一对索引处的字符。
 * 返回在经过若干次交换后，s 可以变成的按字典序最小的字符串。
 * 示例 1:
 * 输入：s = "dcab", pairs = [[0,3],[1,2]]
 * 输出："bacd"
 * 解释：
 * 交换 s[0] 和 s[3], s = "bcad"
 * 交换 s[1] 和 s[2], s = "bacd"
 * 示例 2：
 * 输入：s = "dcab", pairs = [[0,3],[1,2],[0,2]]
 * 输出："abcd"
 * 解释：
 * 交换 s[0] 和 s[3], s = "bcad"
 * 交换 s[0] 和 s[2], s = "acbd"
 * 交换 s[1] 和 s[2], s = "abcd"
 * 示例 3：
 * 输入：s = "cba", pairs = [[0,1],[1,2]]
 * 输出："abc"
 * 解释：
 * 交换 s[0] 和 s[1], s = "bca"
 * 交换 s[1] 和 s[2], s = "bac"
 * 交换 s[0] 和 s[1], s = "abc"
 * 提示：
 * 1 <= s.length <= 10^5
 * 0 <= pairs.length <= 10^5
 * 0 <= pairs[i][0], pairs[i][1] < s.length
 * s 中只含有小写英文字母
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/1/11 12:16
 */
public class SmallestStringWithSwaps {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        if (pairs.isEmpty()) {
            return s;
        }
        int n = s.length();
        UnionFindSet ufs = new UnionFindSet(n);
        for (List<Integer> pair : pairs) {
            ufs.union(pair.get(0), pair.get(1));
        }
        char[] chars = s.toCharArray();
        Map<Integer, PriorityQueue<Character>> map = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            int root = ufs.find(i);
            PriorityQueue<Character> queue = map.get(root);
            if (queue == null) {
                queue = new PriorityQueue<>();
                map.put(root, queue);
            }
            queue.offer(chars[i]);
        }
        StringBuilder sb = new StringBuilder(s.length());
        for (int i = 0; i < n; i++) {
            int root = ufs.find(i);
            sb.append(map.get(root).poll());
        }
        return sb.toString();
    }

    static class UnionFindSet {
        private final int[] parents;

        UnionFindSet(int n) {
            parents = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
        }

        int find(int x) {
            while (parents[x] != parents[parents[x]]) {
                parents[x] = parents[parents[x]];
            }
            return parents[x];
        }

        void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }
            parents[rootX] = rootY;
        }
    }
}
