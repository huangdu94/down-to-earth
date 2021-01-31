package work.huangdu.question_bank.difficult;

import java.util.*;

/**
 * 839. 相似字符串组
 * 如果交换字符串 X 中的两个不同位置的字母，使得它和字符串 Y 相等，那么称 X 和 Y 两个字符串相似。如果这两个字符串本身是相等的，那它们也是相似的。
 * 例如，"tars" 和 "rats" 是相似的 (交换 0 与 2 的位置)； "rats" 和 "arts" 也是相似的，但是 "star" 不与 "tars"，"rats"，或 "arts" 相似。
 * 总之，它们通过相似性形成了两个关联组：{"tars", "rats", "arts"} 和 {"star"}。注意，"tars" 和 "arts" 是在同一组中，即使它们并不相似。形式上，对每个组而言，要确定一个单词在组中，只需要这个词和该组中至少一个单词相似。
 * 给你一个字符串列表 strs。列表中的每个字符串都是 strs 中其它所有字符串的一个字母异位词。请问 strs 中有多少个相似字符串组？
 * 示例 1：
 * 输入：strs = ["tars","rats","arts","star"]
 * 输出：2
 * 示例 2：
 * 输入：strs = ["omv","ovm"]
 * 输出：1
 * 提示：
 * 1 <= strs.length <= 100
 * 1 <= strs[i].length <= 1000
 * sum(strs[i].length) <= 2 * 10^4
 * strs[i] 只包含小写字母。
 * strs 中的所有单词都具有相同的长度，且是彼此的字母异位词。
 * 备注：
 * 字母异位词（anagram），一种把某个字符串的字母的位置（顺序）加以改换所形成的新词。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2021/1/31
 */
public class NumSimilarGroups {
    public int numSimilarGroups(String[] strs) {
        Set<String> strSet = new HashSet<>(strs.length);
        Collections.addAll(strSet, strs);
        int n = strSet.size(), m = strs[0].length();
        UnionFindSet ufs = new UnionFindSet(n);
        strs = strSet.toArray(strs);
        for (int i = 0; i < n; i++) {
            String str1 = strs[i];
            for (int j = i + 1; j < n; j++) {
                if (similarity(str1, strs[j], m)) {
                    ufs.union(i, j);
                    if (ufs.getCount() == 1) {
                        return 1;
                    }
                }
            }
        }
        return ufs.getCount();
    }

    private boolean similarity(String str1, String str2, int len) {
        int flag = 0;
        for (int i = 0; i < len; i++) {
            char c1 = str1.charAt(i), c2 = str2.charAt(i);
            if (c1 != c2) {
                if (++flag > 2) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean similarity2(String str1, String str2, int len) {
        int flag = 0;
        char char1 = 0, char2 = 0;
        for (int i = 0; i < len; i++) {
            char c1 = str1.charAt(i), c2 = str2.charAt(i);
            if (c1 != c2) {
                switch (flag) {
                    case 0:
                        char1 = c1;
                        char2 = c2;
                        flag++;
                        break;
                    case 1:
                        if (c1 != char2 || c2 != char1) {
                            return false;
                        }
                        flag++;
                        break;
                    case 2:
                        return false;
                }
            }
        }
        return true;
    }


    private static class UnionFindSet {
        private final int[] parent;
        private int count;

        public UnionFindSet(int n) {
            count = n;
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (x == parent[x]) return x;
            return parent[x] = find(parent[x]);
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                parent[rootX] = rootY;
                count--;
            }
        }

        public int getCount() {
            return count;
        }
    }
}