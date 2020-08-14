package club.huangdu94.exploration.advanced_algorithms.design;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现 Trie (前缀树)
 * 实现一个 Trie (前缀树)，包含insert,search, 和startsWith这三个操作。
 * 示例:
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 * 说明:
 * 你可以假设所有的输入都是由小写字母a-z构成的。
 * 保证所有输入均为非空字符串。
 *
 * @author duhuang@iflytek.com
 * @version 2020/8/14 12:04
 */
public class Trie {
    private final List<String> datalist;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        datalist = new ArrayList<>();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        datalist.add(word);
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        return datalist.contains(word);
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        for (String word : datalist) {
            if (word.startsWith(prefix)) return true;
        }
        return false;
    }
}
/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */