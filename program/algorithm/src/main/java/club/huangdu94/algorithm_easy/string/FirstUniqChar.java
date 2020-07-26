package club.huangdu94.algorithm_easy.string;

/**
 * 字符串中的第一个唯一字符
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 * 示例：
 * s = "leetcode"
 * 返回 0
 * s = "loveleetcode"
 * 返回 2
 * 提示：你可以假定该字符串只包含小写字母。
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/26 16:59
 */
public class FirstUniqChar {
    public int firstUniqChar(String s) {
        // int flag=0;
        // for(int i=0;i<s.length();i++){
        //     int bitIndex=s.charAt(i)-'a';
        //     if((flag&bitIndex)!=0)
        //         return i;
        //     flag|=(1<<bitIndex);
        // }
        // return -1;

        // Map<Character, Integer> map = new LinkedHashMap<>(26);
        // for (int i = 0; i < s.length(); i++) {
        //     char c = s.charAt(i);
        //     Integer count = map.get(c);
        //     if (count == null) {
        //         map.put(c, i);
        //     } else if (!count.equals(-1)) {
        //         map.put(c, -1);
        //     }
        // }
        // for (Map.Entry<Character, Integer> entry : map.entrySet()) {
        //     if (!entry.getValue().equals(-1)) {
        //         return entry.getValue();
        //     }
        // }
        // return -1;

        int len = s.length();
        int[] recode = new int[26];
        for (int i = 0; i < len; i++) {
            recode[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < len; i++) {
            if (recode[s.charAt(i) - 'a'] == 1)
                return i;
        }
        return -1;
    }
}
