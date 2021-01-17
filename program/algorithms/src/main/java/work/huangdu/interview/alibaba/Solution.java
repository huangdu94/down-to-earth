package work.huangdu.interview.alibaba;

/**
 * 阿里巴巴 安全部 二面
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/11/27 18:55
 */
public class Solution {
    /**
     * 写一个字符串归一化程序，统计字符串中相同字符出现的次数，并按字典序输出字符及其出现次数。如字符串"babcc"归一化后为"a1b2c2"
     */
    private static String parse(String s) {
        int n = s.length();
        int[] count = new int[128];
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            // 大写转小写
            if (c <= 'Z') {
                c += 32;
            }
            count[c]++;
        }
        StringBuilder resBuilder = new StringBuilder();
        for (int i = 'a'; i <= 'z'; i++) {
            if (count[i] != 0) {
                resBuilder.append((char) i).append(count[i]);
            }
        }
        return resBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(parse("aageaGAEWGFWE"));
    }
}
