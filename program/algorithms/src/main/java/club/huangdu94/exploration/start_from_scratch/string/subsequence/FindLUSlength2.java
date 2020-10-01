package club.huangdu94.exploration.start_from_scratch.string.subsequence;

import java.util.*;

/**
 * 522. 最长特殊序列 II
 * 给定字符串列表，你需要从它们中找出最长的特殊序列。最长特殊序列定义如下：该序列为某字符串独有的最长子序列（即不能是其他字符串的子序列）。
 * 子序列可以通过删去字符串中的某些字符实现，但不能改变剩余字符的相对顺序。空序列为所有字符串的子序列，任何字符串为其自身的子序列。
 * 输入将是一个字符串列表，输出是最长特殊序列的长度。如果最长特殊序列不存在，返回 -1 。
 * 示例：
 * 输入: "aba", "cdc", "eae"
 * 输出: 3
 * 提示：
 * 所有给定的字符串长度不会超过 10 。
 * 给定字符串列表的长度将在 [2, 50 ] 之间。
 *
 * @author duhuang@iflytek.com
 * @version 2020/9/30 13:06
 */
public class FindLUSlength2 {
    public int findLUSlength(String[] strs) {
        // 1. 将strs按字符串长度分类
        Map<Integer, List<String>> lenStrMap = new HashMap<>(10);
        for (String str : strs) {
            if (str.length() > 0) {
                List<String> list = lenStrMap.computeIfAbsent(str.length(), k -> new ArrayList<>());
                list.add(str);
            }
        }
        Set<String> longStrSet = new HashSet<>(strs.length);
        // 2. 从长的字符串开始找最长特殊子序列
        for (int len = 10; len > 0; len--) {
            if (!lenStrMap.containsKey(len)) continue;
            List<String> sameLenStrList = lenStrMap.get(len);
            // 3. 统计sameLenStrList中各字符串的数量，找到仅有一个的字符串进行下一步判断
            Map<String, Integer> countMap = new HashMap<>(sameLenStrList.size());
            for (String str : sameLenStrList) {
                countMap.merge(str, 1, Integer::sum);
            }
            // 4.1. 如果此时该长度字符串是最大的，那么只要找到了一个仅有一个的字符串它就可以作为结果
            // 4.2. 否则的话，找到了仅有一个的，还需要跟所有比它更大的字符串比较，如果它不是任何一个比它长的字符串的子串则其就为结果
            if (longStrSet.isEmpty()) {
                for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
                    if (entry.getValue() == 1) {
                        return len;
                    }
                }
            } else {
                List<String> remainList = new ArrayList<>();
                for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
                    if (entry.getValue() == 1) {
                        remainList.add(entry.getKey());
                    }
                }
                for (String s : remainList) {
                    boolean flag = true;
                    for (String t : longStrSet) {
                        if (isSubsequence(s, t)) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        return len;
                    }
                }
            }
            longStrSet.addAll(sameLenStrList);
        }
        return -1;
    }

    private boolean isSubsequence(String s, String t) {
        int from = 0;
        for (char c : s.toCharArray()) {
            if ((from = t.indexOf(c, from) + 1) == 0) {
                return false;
            }
        }
        return true;
    }

    public int findLUSlength2(String[] strs) {
        Arrays.sort(strs, (o1, o2) -> o2.length() - o1.length());
        for (int i = 0, n = strs.length; i < n; i++) {
            String s = strs[i];
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                String t = strs[j];
                if (i == j || s.length() > t.length()) continue;
                if (isSubsequence(s, t)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return s.length();
            }
        }
        return -1;
    }
}
