package club.huangdu94.exploration.start_from_scratch.string.character_statistics;

/**
 * 423. 从英文中重建数字
 * 给定一个非空字符串，其中包含字母顺序打乱的英文单词表示的数字0-9。按升序输出原始的数字。
 * 注意:
 * 输入只包含小写英文字母。
 * 输入保证合法并可以转换为原始的数字，这意味着像 "abc" 或 "zerone" 的输入是不允许的。
 * 输入字符串的长度小于 50,000。
 * 示例 1:
 * 输入: "owoztneoer"
 * 输出: "012" (zeroonetwo)
 * 示例 2:
 * 输入: "fviefuro"
 * 输出: "45" (fourfive)
 *
 * @author duhuang@iflytek.com
 * @version 2020/9/24 20:47
 */
public class OriginalDigits {
    /**
     * zero one two three four five six seven eight nine
     * x0 x1 x2 x3 x4 x5 x6 x7 x8 x9
     * count_e=x0+x1+2*x3+x5+2*x7+x8+x9
     * count_f=x4+x5
     * count_g=x8
     * count_h=x3+x8
     * count_i=x5+x6+x8+x9
     * count_n=x1+x7+2*x9
     * count_o=x0+x1+x2+x4
     * count_r=x0+x3+x4
     * count_s=x6+x7
     * count_t=x2+x3+x8
     * count_u=x4
     * count_v=x5+x7
     * count_w=x2
     * count_x=x6
     * count_z=x0
     */
    public String originalDigits(String s) {
        int[] letters = new int[128], counts = new int[10];
        for (char c : s.toCharArray()) letters[c]++;
        counts[0] = letters['z'];
        counts[2] = letters['w'];
        counts[4] = letters['u'];
        counts[6] = letters['x'];
        counts[8] = letters['g'];
        counts[3] = letters['h'] - counts[8];
        counts[5] = letters['f'] - counts[4];
        counts[7] = letters['s'] - counts[6];
        counts[9] = letters['i'] - counts[5] - counts[6] - counts[8];
        counts[1] = letters['o'] - counts[0] - counts[2] - counts[4];
        int len = 0;
        for (int i = 0; i < 10; i++) len += counts[i];
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < 10; i++) {
            char c = (char) ('0' + i);
            while (counts[i]-- > 0) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
