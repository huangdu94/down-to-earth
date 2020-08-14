package club.huangdu94.data_structure;

/**
 * Trie node.
 *
 * @author duhuang@iflytek.com
 * @version 2020/8/14 17:46
 */
public class TrieNode {
    /**
     * Letter count.
     */
    private static final int LETTER_COUNT = 26;

    /**
     * LETTER_COUNT links to node children.
     */
    private final TrieNode[] nextList;

    /**
     * Is end.
     */
    private boolean end;

    /**
     * Build a node.
     */
    public TrieNode() {
        nextList = new TrieNode[LETTER_COUNT];
    }

    /**
     * Returns if the letter is in the node's nextList.
     */
    public boolean containsKey(char ch) {
        return nextList[ch - 'a'] != null;
    }

    /**
     * On the contrary with containsKey.
     */
    public boolean notContainsKey(char ch) {
        return nextList[ch - 'a'] == null;
    }

    /**
     * Get next node by letter.
     */
    public TrieNode get(char ch) {
        return nextList[charToIndex(ch)];
    }

    /**
     * Create a new node by letter.
     */
    public void put(char ch) {
        nextList[charToIndex(ch)] = new TrieNode();
    }

    /**
     * Put a node at index transform by letter.
     */
    public void put(char ch, TrieNode node) {
        nextList[charToIndex(ch)] = node;
    }

    /**
     * Set node as end.
     */
    public void setEnd() {
        end = true;
    }

    /**
     * Set node's end field value.
     */
    public void setEnd(boolean _end) {
        end = _end;
    }

    /**
     * Returns if is end.
     */
    public boolean isEnd() {
        return end;
    }

    /**
     * Returns if this node has child element.
     */
    public boolean isEmpty() {
        for (TrieNode next : nextList) {
            if (next != null) return false;
        }
        return true;
    }

    /**
     * Transform a char to index.
     */
    private int charToIndex(char ch) {
        return ch - 'a';
    }
}
