package club.huangdu94.question_easy;

/**
 * 696. 计数二进制子串
 * 给定一个字符串 s，计算具有相同数量0和1的非空(连续)子字符串的数量，并且这些子字符串中的所有0和所有1都是组合在一起的。
 * 重复出现的子串要计算它们出现的次数。
 * 示例 1 :
 * 输入: "00110011"
 * 输出: 6
 * 解释: 有6个子串具有相同数量的连续1和0：“0011”，“01”，“1100”，“10”，“0011” 和 “01”。
 * 请注意，一些重复出现的子串要计算它们出现的次数。
 * 另外，“00110011”不是有效的子串，因为所有的0（和1）没有组合在一起。
 * 示例 2 :
 * 输入: "10101"
 * 输出: 4
 * 解释: 有4个子串：“10”，“01”，“10”，“01”，它们具有相同数量的连续1和0。
 * 注意：
 * s.length 在1到50,000之间。
 * s 只包含“0”或“1”字符。
 *
 * @author duhuang@iflytek.com
 * @version 2020/8/10 1:16
 */
public class CountBinarySubstrings {
    public int countBinarySubstrings2(String s) {
        if (s.length() == 1) return 0;
        // 符合题目要求的子串数量
        int count = 0;
        // 最近的连续0子串元素个数
        int zeroCount = 0;
        // 最近的连续1子串元素个数
        int oneCount = 0;
        // 记录上一个字符
        int pre = 0;
        for (char cur : s.toCharArray()) {
            if (cur == '0') {
                if (pre == 1) {
                    zeroCount = 0;
                    pre = 0;
                }
                if (++zeroCount <= oneCount) count++;
            } else {
                if (pre == 0) {
                    oneCount = 0;
                    pre = 1;
                }
                if (++oneCount <= zeroCount) count++;
            }
        }
        return count;
    }

    public int countBinarySubstrings(String s) {
        if (s.length() == 1) return 0;
        int res = 0;
        int[] counts = new int[2];
        int pre = 0;
        for (char c : s.toCharArray()) {
            int cur = c - '0';
            if (pre != cur) {
                counts[cur] = 0;
                pre = cur;
            }
            if (++counts[cur] <= counts[(cur + 1) % 2]) res++;
        }
        return res;
    }

    public static void main(String[] args) {
        CountBinarySubstrings countBinarySubstrings = new CountBinarySubstrings();
        String s = "00110";
        int result = countBinarySubstrings.countBinarySubstrings(s);
        System.out.println(result);
    }
}