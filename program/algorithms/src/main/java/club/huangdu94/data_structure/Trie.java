package club.huangdu94.data_structure;

import java.util.List;

/**
 * Definition a trie.
 *
 * @author duhuang@iflytek.com
 * @version 2020/8/14 17:08
 */
public class Trie {
    /**
     * Trie root.
     */
    private final TrieNode root = new TrieNode();

    /**
     * Initialize your data structure here.
     */
    public Trie() {
    }

    public Trie(List<String> wordList) {
        insert(wordList);
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (cur.notContainsKey(ch)) cur.put(ch);
            cur = cur.get(ch);
        }
        cur.setEnd();
    }

    /**
     * Inserts a word list into the trie.
     */
    public void insert(List<String> wordList) {
        wordList.forEach(this::insert);
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode cur = searchPrefix(word);
        return cur != null && cur.isEnd();
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode cur = searchPrefix(prefix);
        return cur != null;
    }

    /**
     * Returns trie root.
     */
    public TrieNode getRoot() {
        return root;
    }

    /**
     * Search a prefix or whole key in trie and
     * returns the node where search ends
     */
    private TrieNode searchPrefix(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (cur.notContainsKey(ch)) return null;
            cur = cur.get(ch);
        }
        return cur;
    }
}