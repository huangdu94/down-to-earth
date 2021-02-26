package work.huangdu.question_bank.difficult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * 1178. 猜字谜
 * 外国友人仿照中国字谜设计了一个英文版猜字谜小游戏，请你来猜猜看吧。
 * 字谜的迷面 puzzle 按字符串形式给出，如果一个单词 word 符合下面两个条件，那么它就可以算作谜底：
 * 单词 word 中包含谜面 puzzle 的第一个字母。
 * 单词 word 中的每一个字母都可以在谜面 puzzle 中找到。
 * 例如，如果字谜的谜面是 "abcdefg"，那么可以作为谜底的单词有 "faced", "cabbage", 和 "baggage"；而 "beefed"（不含字母 "a"）以及 "based"（其中的 "s" 没有出现在谜面中）。
 * 返回一个答案数组 answer，数组中的每个元素 answer[i] 是在给出的单词列表 words 中可以作为字谜迷面 puzzles[i] 所对应的谜底的单词数目。
 * 示例：
 * 输入：
 * words = ["aaaa","asas","able","ability","actt","actor","access"],
 * puzzles = ["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]
 * 输出：[1,1,3,2,4,0]
 * 解释：
 * 1 个单词可以作为 "aboveyz" 的谜底 : "aaaa"
 * 1 个单词可以作为 "abrodyz" 的谜底 : "aaaa"
 * 3 个单词可以作为 "abslute" 的谜底 : "aaaa", "asas", "able"
 * 2 个单词可以作为 "absoryz" 的谜底 : "aaaa", "asas"
 * 4 个单词可以作为 "actresz" 的谜底 : "aaaa", "asas", "actt", "access"
 * 没有单词可以作为 "gaswxyz" 的谜底，因为列表中的单词都不含字母 'g'。
 * 提示：
 * 1 <= words.length <= 10^5
 * 4 <= words[i].length <= 50
 * 1 <= puzzles.length <= 10^4
 * puzzles[i].length == 7
 * words[i][j], puzzles[i][j] 都是小写英文字母。
 * 每个 puzzles[i] 所包含的字符都不重复。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/2/26
 */
public class FindNumOfValidWords {
    private static final int PUZZLE_LEN = 7;

    // 超时
    public List<Integer> findNumOfValidWords2(String[] words, String[] puzzles) {
        int m = words.length, n = puzzles.length;
        List<Integer> result = new ArrayList<>(n);
        List<Set<Character>> wordCharSetList = new ArrayList<>(m), puzzleCharSetList = new ArrayList<>(n);
        for (String word : words) {
            Set<Character> set = new HashSet<>(8);
            for (int i = 0, len = word.length(); i < len; i++) {
                set.add(word.charAt(i));
                if (set.size() > 7) { break; }
            }
            if (set.size() < 8) { wordCharSetList.add(set); }
        }
        for (String puzzle : puzzles) {
            Set<Character> set = new HashSet<>(8);
            for (int i = 0; i < PUZZLE_LEN; i++) {
                set.add(puzzle.charAt(i));
            }
            puzzleCharSetList.add(set);
        }

        for (int i = 0; i < n; i++) {
            int count = 0;
            for (Set<Character> wordCharSet : wordCharSetList) {
                if (meet(wordCharSet, puzzles[i].charAt(0), puzzleCharSetList.get(i))) {
                    count++;
                }
            }
            result.add(count);
        }
        return result;
    }

    private boolean meet(Set<Character> wordCharSet, char first, Set<Character> puzzleCharSet) {
        if (!wordCharSet.contains(first)) { return false; }
        return puzzleCharSet.containsAll(wordCharSet);
    }

    // 二进制状态压缩
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        Map<Integer, Integer> statusCountMap = new HashMap<>();
        List<Integer> result = new ArrayList<>(puzzles.length);
        for (String word : words) {
            int status = 0, charKindCount = 0;
            for (int i = 0, len = word.length(); i < len && charKindCount <= PUZZLE_LEN; i++) {
                int mask = 1 << (word.charAt(i) - 'a');
                if ((status & mask) == 0) {
                    status |= mask;
                    charKindCount++;
                }
            }
            if (charKindCount <= PUZZLE_LEN) {
                statusCountMap.merge(status, 1, Integer::sum);
            }
        }
        for (String puzzle : puzzles) {
            int firstCharMask = 1 << (puzzle.charAt(0) - 'a'), meetCount = 0;
            for (int i = 0; i <= 0X3F; i++) {
                int status = 0, mask = i, index = 1;
                while (mask != 0) {
                    if ((mask & 1) == 1) {
                        status |= (1 << (puzzle.charAt(index) - 'a'));
                    }
                    mask >>= 1;
                    index++;
                }
                meetCount += statusCountMap.getOrDefault(status | firstCharMask, 0);
            }
            result.add(meetCount);
        }
        return result;
    }

    /*
    方法二：字典树
    思路与算法
    由于题目中规定word和puzzle均只包含小写字母，我们也可以考虑使用字典树来表示需要的「数据结构」。由于方法一中已经详细介绍了每一步的做法，因此方法二中只介绍与方法一不同的地方。
    对于每一个word对应的集合Sw，我们将Sw中的的字母按照字典序进行排序，组成一个字符串，加入字典树中。与方法一中的哈希映射类似，我们需要统计每个字符串在字典树中的出现次数。
    对于每一个puzzle对应的集合Sp，我们枚举Sp的子集，并将子集中的字母按照字典序进行排序，组成一个字符串，在字典树中查询得到其出现次数。需要注意的是，由于Sp
    只是一个普通的集合，而不是二进制表示，因此我们可以使用回溯的方法，在枚举子集的同时维护可以在字典树上进行查询的指针。详细的实现可以见下面的代码。
    细节
    在实际的实现中，我们无需显式地构造出加入字典树以及在字典树中查询需要的字符串，可以考虑使用一些等价的简单数据结构（例如列表）表示字符串。
     */
    static class Solution {
        TrieNode root;

        public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
            root = new TrieNode();
            for (String word : words) {
                // TreeSet去重加排序
                Set<Character> set = new TreeSet<>();
                for (int i = 0, len = word.length(); i < len && set.size() <= PUZZLE_LEN; i++) {
                    set.add(word.charAt(i));
                }
                if (set.size() <= PUZZLE_LEN) {
                    StringBuilder sb = new StringBuilder();
                    for (char c : set) {
                        sb.append(c);
                    }
                    // 加入字典树中
                    add(root, sb.toString());
                }
            }

            List<Integer> result = new ArrayList<>();
            for (String puzzle : puzzles) {
                char required = puzzle.charAt(0);
                char[] arr = puzzle.toCharArray();
                Arrays.sort(arr);
                result.add(find(new String(arr), required, root, 0));
            }
            return result;
        }

        public void add(TrieNode root, String word) {
            TrieNode cur = root;
            for (int i = 0, len = word.length(); i < len; ++i) {
                char ch = word.charAt(i);
                if (cur.child[ch - 'a'] == null) {
                    cur.child[ch - 'a'] = new TrieNode();
                }
                cur = cur.child[ch - 'a'];
            }
            cur.count++;
        }

        // 在回溯的过程中枚举 puzzle 的所有子集并统计答案
        // find(puzzle, required, cur, pos) 表示 puzzle 的首字母为 required, 当前搜索到字典树上的 cur 节点，并且准备枚举 puzzle 的第 pos
        // 个字母是否选择（放入子集中）
        // find 函数的返回值即为谜底的数量
        public int find(String puzzle, char required, TrieNode cur, int pos) {
            // 搜索到空节点，不合法，返回 0
            if (cur == null) {
                return 0;
            }
            // 整个 puzzle 搜索完毕，返回谜底的数量
            if (pos == 7) {
                return cur.count;
            }
            // 选择第 pos 个字母
            int ret = find(puzzle, required, cur.child[puzzle.charAt(pos) - 'a'], pos + 1);
            // 当 puzzle.charAt(pos) 不为首字母时，可以不选择第 pos 个字母
            if (puzzle.charAt(pos) != required) {
                ret += find(puzzle, required, cur, pos + 1);
            }
            return ret;
        }
    }

    static class TrieNode {
        int count;
        TrieNode[] child;

        public TrieNode() {
            count = 0;
            child = new TrieNode[26];
        }
    }

    public static void main(String[] args) {
        // [1,1,3,2,4,0]
        String[] words = {"aaaa", "asas", "able", "ability", "actt", "actor", "access"};
        String[] puzzles = {"aboveyz", "abrodyz", "abslute", "absoryz", "actresz", "gaswxyz"};
        System.out.println(new FindNumOfValidWords().findNumOfValidWords(words, puzzles));
        System.out.println(new Solution().findNumOfValidWords(words, puzzles));
    }
}
