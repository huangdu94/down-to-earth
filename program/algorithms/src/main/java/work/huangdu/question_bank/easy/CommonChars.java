package work.huangdu.question_bank.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1002. 查找常用字符
 * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
 * 你可以按任意顺序返回答案。
 * 示例 1：
 * 输入：["bella","label","roller"]
 * 输出：["e","l","l"]
 * 示例 2：
 * 输入：["cool","lock","cook"]
 * 输出：["c","o"]
 * 提示：
 * 1 <= A.length <= 100
 * 1 <= A[i].length <= 100
 * A[i][j] 是小写字母
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/10/14 12:25
 */
public class CommonChars {
    public List<String> commonChars(String[] A) {
        int n = A.length;
        List<String> res = new ArrayList<>();
        int[][] counts = new int[n][26];
        for (int i = 0; i < n; i++) {
            String str = A[i];
            int[] count = counts[i];
            for (int j = 0, len = str.length(); j < len; j++) {
                count[str.charAt(j) - 'a']++;
            }
        }
        for (int j = 0; j < 26; j++) {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (min > counts[i][j]) {
                    min = counts[i][j];
                    if (min == 0) {
                        break;
                    }
                }
            }
            String letter = String.valueOf((char) ('a' + j));
            for (int k = 0; k < min; k++) {
                res.add(letter);
            }
        }
        return res;
    }

    public List<String> commonChars2(String[] A) {
        List<String> res = new ArrayList<>();
        int[] min = new int[26];
        Arrays.fill(min, Integer.MAX_VALUE);
        for (String str : A) {
            int[] count = new int[26];
            for (int i = 0, len = str.length(); i < len; i++) {
                count[str.charAt(i) - 'a']++;
            }
            for (int i = 0; i < 26; i++) {
                if (count[i] < min[i]) {
                    min[i] = count[i];
                }
            }
        }
        for (int i = 0; i < 26; i++) {
            String letter = String.valueOf((char) ('a' + i));
            for (int k = 0; k < min[i]; k++) {
                res.add(letter);
            }
        }
        return res;
    }
}
