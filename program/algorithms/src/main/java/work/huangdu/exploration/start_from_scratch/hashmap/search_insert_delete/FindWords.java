package work.huangdu.exploration.start_from_scratch.hashmap.search_insert_delete;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 500. 键盘行
 * 给定一个单词列表，只返回可以使用在键盘同一行的字母打印出来的单词。键盘如下图所示。
 * American keyboard
 * 示例：
 * 输入: ["Hello", "Alaska", "Dad", "Peace"]
 * 输出: ["Alaska", "Dad"]
 * 注意：
 * 你可以重复使用键盘上同一字符。
 * 你可以假设输入的字符串将只包含字母。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/11/1 18:06
 */
public class FindWords {
    private static final Set<Character> set1 = new HashSet<>();
    private static final Set<Character> set2 = new HashSet<>();
    private static final Set<Character> set3 = new HashSet<>();

    static {
        set1.add('q');
        set1.add('w');
        set1.add('e');
        set1.add('r');
        set1.add('t');
        set1.add('y');
        set1.add('u');
        set1.add('i');
        set1.add('o');
        set1.add('p');
        set2.add('a');
        set2.add('s');
        set2.add('d');
        set2.add('f');
        set2.add('g');
        set2.add('h');
        set2.add('j');
        set2.add('k');
        set2.add('l');
        set3.add('z');
        set3.add('x');
        set3.add('c');
        set3.add('v');
        set3.add('b');
        set3.add('n');
        set3.add('m');
    }

    public String[] findWords(String[] words) {
        List<String> list = new ArrayList<>();
        for (String word : words) {
            if (word.length() == 0) {
                list.add(word);
                continue;
            }
            String lowerWord = word.toLowerCase();
            char first = lowerWord.charAt(0);
            if (meet(lowerWord, set1.contains(first) ? set1 : (set2.contains(first) ? set2 : set3))) {
                list.add(word);
            }
        }
        String[] res = new String[list.size()];
        for (int i = 0, len = list.size(); i < len; i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    private boolean meet(String word, Set<Character> set) {
        for (int i = 1, len = word.length(); i < len; i++) {
            if (!set.contains(word.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
