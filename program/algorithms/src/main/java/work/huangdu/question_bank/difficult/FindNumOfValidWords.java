package work.huangdu.question_bank.difficult;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        int n = puzzles.length;
        List<Integer> result = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            result.add(0);
        }
        List<Set<Character>> charSetList = new ArrayList<>(n);
        for (String puzzle : puzzles) {
            Set<Character> set = new HashSet<>(PUZZLE_LEN - 1);
            for (int i = 1; i < PUZZLE_LEN; i++) {
                set.add(puzzle.charAt(i));
            }
            charSetList.add(set);
        }
        for (String word : words) {
            for (int i = 0; i < n; i++) {
                if (meet(word, puzzles[i].charAt(0), charSetList.get(i))) {
                    result.set(i, result.get(i) + 1);
                }
            }
        }
        return result;
    }

    private boolean meet(String word, char first, Set<Character> charSet) {
        boolean flag = false;
        for (int i = 0, len = word.length(); i < len; i++) {
            char c = word.charAt(i);
            if (c == first) {
                flag = true;
            } else if (!charSet.contains(c)) {
                return false;
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        // [1,1,3,2,4,0]
        String[] words = {"aaaa", "asas", "able", "ability", "actt", "actor", "access"};
        String[] puzzles = {"aboveyz", "abrodyz", "abslute", "absoryz", "actresz", "gaswxyz"};
        System.out.println(new FindNumOfValidWords().findNumOfValidWords(words, puzzles));
    }
}
