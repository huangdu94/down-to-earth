package work.huangdu.exploration.start_from_scratch.string.character_statistics;

import java.util.HashMap;
import java.util.Map;

/**
 * 383. 赎金信
 * 给定一个赎金信 (ransom) 字符串和一个杂志(magazine)字符串，判断第一个字符串 ransom 能不能由第二个字符串 magazines 里面的字符构成。如果可以构成，返回 true ；否则返回 false。
 * (题目说明：为了不暴露赎金信字迹，要从杂志上搜索各个需要的字母，组成单词来表达意思。杂志字符串中的每个字符只能在赎金信字符串中使用一次。)
 * 注意：
 * 你可以假设两个字符串均只含有小写字母。
 * canConstruct("a", "b") -> false
 * canConstruct("aa", "ab") -> false
 * canConstruct("aa", "aab") -> true
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/9/23 21:53
 */
public class CanConstruct {
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) return false;
        int[] records = new int[26];
        int rLen = ransomNote.length(), mLen = magazine.length(), remain = rLen;
        for (int i = 0; i < rLen; i++) {
            records[ransomNote.charAt(i) - 'a']++;
        }
        for (int i = 0; i < mLen && remain > 0; i++) {
            int m = magazine.charAt(i) - 'a';
            if (records[m] != 0) {
                records[m]--;
                remain--;
            }
        }
        return remain == 0;
    }

    public boolean canConstruct2(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) return false;
        Map<Character, Integer> characterIntegerMap = new HashMap<>();
        int rLen = ransomNote.length(), mLen = magazine.length();
        for (int i = 0; i < rLen; i++) {
            characterIntegerMap.merge(ransomNote.charAt(i), 1, Integer::sum);
        }
        for (int i = 0; i < mLen && !characterIntegerMap.isEmpty(); i++) {
            char m = magazine.charAt(i);
            Integer count = characterIntegerMap.get(m);
            if (count != null) {
                if (--count == 0)
                    characterIntegerMap.remove(m);
                else
                    characterIntegerMap.put(m, count);
            }
        }
        return characterIntegerMap.isEmpty();
    }
}
