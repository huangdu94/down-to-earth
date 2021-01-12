package work.huangdu.data_structure;

/**
 * Trie node.
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/8/14 17:46
 */
public class TrieNode {
    /**
     * Letter count.
     * If value is 26, It can only support lower case letter.
     * If value is 52, It can support lower and upper case letter.
     */
    private static final int LOWER_CASE = 26;
    private static final int UPPER_CASE = 52;
    private static final int LETTER_COUNT = LOWER_CASE;

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
        return nextList[charToIndex(ch)] != null;
    }

    /**
     * On the contrary with containsKey.
     */
    public boolean notContainsKey(char ch) {
        return nextList[charToIndex(ch)] == null;
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
     * A 65
     * a 97
     * -65 + 26 = -39
     */
    private static int charToIndex(char ch) {
        if (ch < 97) return ch - 39;
        return ch - 97;
    }

    /**
     * Transform index to a char.
     * A 65
     * a 97
     * -65 + 26 = -39
     */
    private static char indexToChar(int index) {
        if (index < 26) return (char) (index + 97);
        return (char) (index + 39);
    }
}
