package work.huangdu.exploration.start_from_scratch.string.character_statistics;

import java.util.*;

/**
 * 451. 根据字符出现频率排序
 * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 * 示例 1:
 * 输入:
 * "tree"
 * 输出:
 * "eert"
 * 解释:
 * 'e'出现两次，'r'和't'都只出现一次。
 * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
 * 示例 2:
 * 输入:
 * "cccaaa"
 * 输出:
 * "cccaaa"
 * 解释:
 * 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
 * 注意"cacaca"是不正确的，因为相同的字母必须放在一起。
 * 示例 3:
 * 输入:
 * "Aabb"
 * 输出:
 * "bbAa"
 * 解释:
 * 此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
 * 注意'A'和'a'被认为是两种不同的字符。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/9/24 20:45
 */
public class FrequencySort {
    public String frequencySort(String s) {
        if (s.length() == 0) return "";
        int len = s.length(), count = 0, j = 0;
        char cur = ' ';
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            map.merge(c, 1, Integer::sum);
        }
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> o2.getValue() - o1.getValue());
        for (int i = 0; i < len; i++) {
            if (count == 0) {
                Map.Entry<Character, Integer> entry = list.get(j++);
                cur = entry.getKey();
                count = entry.getValue();
            }
            chars[i] = cur;
            count--;
        }
        return new String(chars);
    }

    public String frequencySort2(String s) {
        if (s.length() == 0) return "";
        int index = 0;
        int[] counts = new int[128];
        char[] chars = s.toCharArray();
        for (char c : chars) {
            counts[c]++;
        }
        PriorityQueue<Character> heap = new PriorityQueue<>(128, (o1, o2) -> counts[o2] - counts[o1]);
        for (int i = 0; i < 128; i++) {
            if (counts[i] != 0) {
                heap.offer((char) i);
            }
        }
        while (!heap.isEmpty()) {
            char c = heap.remove();
            while (counts[c]-- > 0) {
                chars[index++] = c;
            }
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        FrequencySort sort = new FrequencySort();
        String s = "raaeaedere";
        System.out.println(sort.frequencySort2(s));
    }
}
