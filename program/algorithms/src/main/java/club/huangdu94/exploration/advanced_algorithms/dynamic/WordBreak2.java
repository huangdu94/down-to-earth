package club.huangdu94.exploration.advanced_algorithms.dynamic;

import club.huangdu94.data_structure.Trie;
import club.huangdu94.data_structure.TrieNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 单词拆分 II
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。
 * 说明：
 * 分隔时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 * 输入:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * 输出:
 * [
 * "cats and dog",
 * "cat sand dog"
 * ]
 * 示例 2：
 * 输入:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * 输出:
 * [
 * "pine apple pen apple",
 * "pineapple pen apple",
 * "pine applepen apple"
 * ]
 * 解释: 注意你可以重复使用字典中的单词。
 * 示例3：
 * 输入:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出:
 * []
 *
 * @author duhuang@iflytek.com
 * @version 2020/9/3 14:42
 * @see club.huangdu94.exploration.advanced_algorithms.dynamic.WordBreak
 */
public class WordBreak2 {
    private List<String> resList;

    public List<String> wordBreak(String s, List<String> wordDict) {
        resList = new ArrayList<>();
        dfs(s.toCharArray(), new StringBuilder(), new Trie(wordDict).getRoot(), 0, s.length(), new boolean[s.length()]);
        return resList;
    }

    private boolean dfs(char[] chars, StringBuilder res, TrieNode root, int start, int len, boolean[] memo) {
        if (start == len) {
            res.deleteCharAt(res.length() - 1);
            resList.add(res.toString());
            return true;
        }
        if (memo[start]) return false;
        int i = start;
        TrieNode cur = root;
        boolean flag = false;
        while (i < len && cur.containsKey(chars[i])) {
            cur = cur.get(chars[i++]);
            if (cur.isEnd()) {
                int temp = res.length();
                res.append(chars, start, i - start).append(' ');
                if (dfs(chars, res, root, i, len, memo)) flag = true;
                res.delete(temp, res.length());
            }
        }
        if (!flag) {
            memo[start] = true;
        }
        return flag;
    }
}
