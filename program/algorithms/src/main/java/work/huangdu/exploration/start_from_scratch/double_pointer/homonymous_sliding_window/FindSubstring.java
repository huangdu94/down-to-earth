package work.huangdu.exploration.start_from_scratch.double_pointer.homonymous_sliding_window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 30. 串联所有单词的子串
 * 给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 * 示例 1：
 * 输入：
 * s = "barfoothefoobarman",
 * words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 * 示例 2：
 * 输入：
 * s = "wordgoodgoodgoodbestword",
 * words = ["word","good","best","word"]
 * 输出：[]
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/12/19 15:50
 */
public class FindSubstring {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        int n = words.length, len = words[0].length(), allLen = len * n, sLen = s.length();
        if (sLen < allLen) {
            return result;
        }
        Map<String, Integer> wordCounts = new HashMap<>();
        for (String word : words) {
            wordCounts.merge(word, 1, Integer::sum);
        }
        for (int i = 0; i <= sLen - allLen; i++) {
            if (check(s, i, n, len, wordCounts)) {
                result.add(i);
            }
        }
        return result;
    }

    private boolean check(String s, int start, int n, int len, Map<String, Integer> wordCounts) {
        Map<String, Integer> sCounts = new HashMap<>();
        int i = start, satisfy = 0;
        String sub;
        while (wordCounts.containsKey(sub = s.substring(i, i + len))) {
            if (sCounts.merge(sub, 1, Integer::sum) <= wordCounts.get(sub)) {
                if (++satisfy == n) {
                    return true;
                }
            } else {
                return false;
            }
            i += len;
        }
        return false;
    }
}
