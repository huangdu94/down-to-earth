package work.huangdu.exploration.start_from_scratch.string.number_transform_string;

/**
 * 273. 整数转换英文表示
 * 将非负整数转换为其对应的英文表示。可以保证给定输入小于 231 - 1 。
 * 示例 1:
 * 输入: 123
 * 输出: "One Hundred Twenty Three"
 * 示例 2:
 * 输入: 12345
 * 输出: "Twelve Thousand Three Hundred Forty Five"
 * 示例 3:
 * 输入: 1234567
 * 输出: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 * 示例 4:
 * 输入: 1234567891
 * 输出: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/9/29 10:35
 */
public class NumberToWords {
    private static final String[] zeroToTwenty = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen", "Twenty"};
    private static final String[] tens = {"Zero", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private static final int BILLION = 1000000000;
    private static final int MILLION = 1000000;
    private static final int THOUSAND = 1000;
    private static final int HUNDRED = 100;
    private static final int TWENTY = 20;
    private static final int TEN = 10;

    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        StringBuilder res = new StringBuilder();
        if (num >= BILLION) {
            int n = num / BILLION;
            res.append(zeroToTwenty[n]).append(" Billion");
            num -= n * BILLION;
        }
        if (num == 0) return res.toString();
        if (num >= MILLION) {
            int n = num / MILLION;
            if (res.length() > 0) res.append(' ');
            res.append(getString(n)).append(" Million");
            num -= n * MILLION;
        }
        if (num == 0) return res.toString();
        if (num >= THOUSAND) {
            int n = num / THOUSAND;
            if (res.length() > 0) res.append(' ');
            res.append(getString(n)).append(" Thousand");
            num -= n * THOUSAND;
        }
        if (num == 0) return res.toString();
        if (res.length() > 0) res.append(' ');
        res.append(getString(num));
        return res.toString();
    }

    private String getString(int num) {
        StringBuilder res = new StringBuilder();
        if (num >= HUNDRED) {
            int n = num / HUNDRED;
            res.append(zeroToTwenty[n]).append(" Hundred");
            num -= n * HUNDRED;
        }
        if (num == 0) return res.toString();
        if (res.length() > 0) res.append(' ');
        if (num <= TWENTY) {
            res.append(zeroToTwenty[num]);
        } else {
            res.append(tens[num / TEN]);
            if (num % TEN != 0) {
                res.append(' ').append(zeroToTwenty[num % TEN]);
            }
        }
        return res.toString();
    }
}
