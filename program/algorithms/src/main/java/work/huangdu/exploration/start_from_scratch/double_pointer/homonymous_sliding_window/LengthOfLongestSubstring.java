package work.huangdu.exploration.start_from_scratch.double_pointer.homonymous_sliding_window;

/**
 * @author duhuang@iflytek.com
 * @version 2020/12/15 15:21
 */
public class LengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), max = 0, start = 0, end;
        boolean[] exists = new boolean[128];
        for (end = 0; end < n; end++) {
            int c = s.charAt(end);
            if (!exists[c]) {
                exists[c] = true;
            } else {
                max = Math.max(max, end - start);
                char temp;
                while ((temp = s.charAt(start++)) != c) {
                    exists[temp] = false;
                }
            }
        }
        return Math.max(max, end - start);
    }

    public static void main(String[] args) {
        LengthOfLongestSubstring lols = new LengthOfLongestSubstring();
        System.out.println(lols.lengthOfLongestSubstring("abcabcbb"));
    }
}
