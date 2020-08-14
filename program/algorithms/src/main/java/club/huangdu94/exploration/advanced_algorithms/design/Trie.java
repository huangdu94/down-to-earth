package club.huangdu94.exploration.advanced_algorithms.design;

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
    /**
     * Letter count.
     */
    private static final int LETTER_COUNT = 26;

    /**
     * Trie root.
     */
    private final TrieNode root = new TrieNode();

    /**
     * Initialize your data structure here.
     */
    public Trie() {
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        if (word == null || word.isEmpty()) return;
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.sons[charToIndex(c)] == null)
                cur.sons[charToIndex(c)] = new TrieNode();
            cur = cur.sons[charToIndex(c)];
        }
        cur.end = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        if (word == null || word.isEmpty()) return true;
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.sons[charToIndex(c)] == null) return false;
            cur = cur.sons[charToIndex(c)];
        }
        return cur.end;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        if (prefix == null || prefix.isEmpty()) return true;
        TrieNode cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.sons[charToIndex(c)] == null) return false;
            cur = cur.sons[charToIndex(c)];
        }
        return true;
    }

    /**
     * Transform letter to index.
     *
     * @param c letter
     * @return index
     */
    private static int charToIndex(char c) {
        return c - 'a';
    }

    /**
     * Trie node.
     */
    private static class TrieNode {
        TrieNode[] sons = new TrieNode[LETTER_COUNT];
        boolean end = false;

        TrieNode() {
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("app");
        trie.insert("apps");
        trie.insert("apple");
        System.out.println(trie.search("app"));
    }
}
/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */