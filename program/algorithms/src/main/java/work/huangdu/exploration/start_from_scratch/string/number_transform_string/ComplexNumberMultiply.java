package work.huangdu.exploration.start_from_scratch.string.number_transform_string;

/**
 * 537. 复数乘法
 * 给定两个表示复数的字符串。
 * 返回表示它们乘积的字符串。注意，根据定义 i2 = -1 。
 * 示例 1:
 * 输入: "1+1i", "1+1i"
 * 输出: "0+2i"
 * 解释: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i ，你需要将它转换为 0+2i 的形式。
 * 示例 2:
 * 输入: "1+-1i", "1+-1i"
 * 输出: "0+-2i"
 * 解释: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i ，你需要将它转换为 0+-2i 的形式。
 * 注意:
 * 输入字符串不包含额外的空格。
 * 输入字符串将以 a+bi 的形式给出，其中整数 a 和 b 的范围均在 [-100, 100] 之间。输出也应当符合这种形式。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/9/27 9:11
 */
public class ComplexNumberMultiply {
    public String complexNumberMultiply(String a, String b) {
        int[] res = compute(parse(a), parse(b));
        return generate(res[0], res[1]);
    }

    private int[] parse(String complex) {
        int i = complex.indexOf('+');
        int a = Integer.parseInt(complex.substring(0, i));
        int b = Integer.parseInt(complex.substring(i + 1, complex.length() - 1));
        return new int[]{a, b};
    }

    private int[] compute(int[] c1, int[] c2) {
        int a = c1[0] * c2[0] - c1[1] * c2[1];
        int b = c1[0] * c2[1] + c1[1] * c2[0];
        return new int[]{a, b};
    }

    private String generate(int a, int b) {
        return new StringBuilder().append(a).append('+').append(b).append('i').toString();
    }
}
