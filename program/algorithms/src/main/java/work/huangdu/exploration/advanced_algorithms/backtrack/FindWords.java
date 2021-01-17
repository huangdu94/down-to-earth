package work.huangdu.exploration.advanced_algorithms.backtrack;

import work.huangdu.data_structure.Trie;
import work.huangdu.data_structure.TrieNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 单词搜索 II
 * 给定一个二维网格board和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
 * 示例:
 * 输入:
 * words = {"oath","pea","eat","rain"} and board =
 * [
 * ['o','a','a','n'],
 * ['e','t','a','e'],
 * ['i','h','k','r'],
 * ['i','f','l','v']
 * ]
 * 输出:{"eat","oath"}
 * 说明:
 * 你可以假设所有输入都由小写字母 a-z组成。
 * 提示:
 * 你需要优化回溯算法以通过更大数据量的测试。你能否早点停止回溯？
 * 如果当前单词不存在于所有单词的前缀中，则可以立即停止回溯。什么样的数据结构可以有效地执行这样的操作？散列表是否可行？为什么？ 前缀树如何？如果你想学习如何实现一个基本的前缀树，请先查看这个问题： 实现Trie（前缀树）。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/8/14 12:02
 */
public class FindWords {
    // 优化点：
    // 1. 使用遍历过的点标记为特殊字符来代替visited数组
    // 2. 直接使用List存储结果，已经得到的结果从Trie中删除，避免重复结果(比set更快)
    // 3. 逐渐移除Trie中的无用子节点
    //private final Set<String> res = new HashSet<>();
    private final List<String> res = new LinkedList<>();
    private int m;
    private int n;
    private final StringBuilder sb = new StringBuilder();

    public List<String> findWords(char[][] board, String[] words) {
        // 1. 构建前缀树,得到前缀树的根
        Trie trie = new Trie();
        for (String word : words) trie.insert(word);
        TrieNode root = trie.getRoot();
        // 2. 遍历board,回溯得到答案
        m = board.length;
        n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (root.containsKey(board[i][j])) {
                    backtrack(board, i, j, root, root.get(board[i][j]));
                }
            }
        }
        //return new ArrayList<>(res);
        return res;
    }

    /**
     * 回溯算法
     */
    private void backtrack(char[][] board, int i, int j, TrieNode father, TrieNode node) {
        char ch = board[i][j];
        board[i][j] = '*';
        sb.append(ch);
        if (node.isEnd()) {
            res.add(sb.toString());
            node.setEnd(false);
        }
        if (i - 1 >= 0 && board[i - 1][j] != '*' && node.containsKey(board[i - 1][j])) {
            backtrack(board, i - 1, j, node, node.get(board[i - 1][j]));
        }
        if (i + 1 < m && board[i + 1][j] != '*' && node.containsKey(board[i + 1][j])) {
            backtrack(board, i + 1, j, node, node.get(board[i + 1][j]));
        }
        if (j - 1 >= 0 && board[i][j - 1] != '*' && node.containsKey(board[i][j - 1])) {
            backtrack(board, i, j - 1, node, node.get(board[i][j - 1]));
        }
        if (j + 1 < n && board[i][j + 1] != '*' && node.containsKey(board[i][j + 1])) {
            backtrack(board, i, j + 1, node, node.get(board[i][j + 1]));
        }
        board[i][j] = ch;
        sb.deleteCharAt(sb.length() - 1);
        // 删掉没用的trie分支(如果其子节点都为空,且代码执行到这里了,如果满足它已经被加到结果,所以可以被删掉了)
        if (node.isEmpty()) {
            father.put(ch, null);
        }
    }

    public static void main(String[] args) {
        FindWords findWords = new FindWords();
        char[][] board = {
                {'b', 'a', 'a', 'b', 'a', 'b'},
                {'a', 'b', 'a', 'a', 'a', 'a'},
                {'a', 'b', 'a', 'a', 'a', 'b'},
                {'a', 'b', 'a', 'b', 'b', 'a'},
                {'a', 'a', 'b', 'b', 'a', 'b'},
                {'a', 'a', 'b', 'b', 'b', 'a'},
                {'a', 'a', 'b', 'a', 'a', 'b'}};
        String[] words = {"bbaabaabaaaaabaababaaaaababb", "aabbaaabaaabaabaaaaaabbaaaba", "babaababbbbbbbaabaababaabaaa", "bbbaaabaabbaaababababbbbbaaa", "babbabbbbaabbabaaaaaabbbaaab", "bbbababbbbbbbababbabbbbbabaa", "babababbababaabbbbabbbbabbba", "abbbbbbaabaaabaaababaabbabba", "aabaabababbbbbbababbbababbaa", "aabbbbabbaababaaaabababbaaba", "ababaababaaabbabbaabbaabbaba", "abaabbbaaaaababbbaaaaabbbaab", "aabbabaabaabbabababaaabbbaab", "baaabaaaabbabaaabaabababaaaa", "aaabbabaaaababbabbaabbaabbaa", "aaabaaaaabaabbabaabbbbaabaaa", "abbaabbaaaabbaababababbaabbb", "baabaababbbbaaaabaaabbababbb", "aabaababbaababbaaabaabababab", "abbaaabbaabaabaabbbbaabbbbbb", "aaababaabbaaabbbaaabbabbabab", "bbababbbabbbbabbbbabbbbbabaa", "abbbaabbbaaababbbababbababba", "bbbbbbbabbbababbabaabababaab", "aaaababaabbbbabaaaaabaaaaabb", "bbaaabbbbabbaaabbaabbabbaaba", "aabaabbbbaabaabbabaabababaaa", "abbababbbaababaabbababababbb", "aabbbabbaaaababbbbabbababbbb", "babbbaabababbbbbbbbbaabbabaa"};
        List<String> res = findWords.findWords(board, words);
        System.out.println(res);
    }
}
