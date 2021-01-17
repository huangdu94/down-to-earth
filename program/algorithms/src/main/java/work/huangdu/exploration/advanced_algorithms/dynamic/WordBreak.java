package work.huangdu.exploration.advanced_algorithms.dynamic;

import work.huangdu.data_structure.TrieNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 139. 单词拆分
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * 说明：
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * 示例 2：
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 * 注意你可以重复使用字典中的单词。
 * 示例 3：
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/9/3 14:41
 * @see WordBreak2
 */
public class WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        // return dfs(s.toCharArray(), new Trie(wordDict).getRoot(), 0, s.length(), new boolean[s.length()]);
        return dynamic(s, wordDict);
    }

    private boolean dfs(char[] chars, TrieNode root, int start, int len, boolean[] memo) {
        if (start == len) return true;
        if (memo[start]) return false;
        int i = start;
        TrieNode cur = root;
        while (i < len && cur.containsKey(chars[i])) {
            cur = cur.get(chars[i++]);
            if (cur.isEnd() && dfs(chars, root, i, len, memo)) {
                return true;
            }
        }
        memo[start] = true;
        return false;
    }

    // 超出内存限制
    /*private boolean bfs(char[] chars, TrieNode root) {
        int len = chars.length;
        Queue<Integer> startQueue = new ArrayDeque<>();
        startQueue.offer(0);
        while (!startQueue.isEmpty()) {
            int start = startQueue.poll();
            TrieNode cur = root;
            while (start < len && cur.containsKey(chars[start])) {
                cur = cur.get(chars[start++]);
                if (cur.isEnd()) {
                    if (start == len) return true;
                    startQueue.offer(start);
                }
            }
        }
        return false;
    }*/

    private boolean dynamic(String s, List<String> wordDict) {
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        Set<String> wordSet = new HashSet<>(wordDict);
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len];
    }

    public static void main(String[] args) {
        WordBreak wordBreak = new WordBreak();
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("a");
        wordDict.add("aa");
        wordDict.add("aaa");
        wordDict.add("aaaa");
        wordDict.add("aaaaa");
        wordDict.add("aaaaaa");
        wordDict.add("aaaaaaa");
        wordDict.add("aaaaaaaa");
        wordDict.add("aaaaaaaaa");
        wordDict.add("aaaaaaaaaa");
        System.out.println(wordBreak.wordBreak(s, wordDict));
    }
}
