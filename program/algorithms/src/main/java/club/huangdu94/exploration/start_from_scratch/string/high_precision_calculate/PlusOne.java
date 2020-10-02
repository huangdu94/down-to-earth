package club.huangdu94.exploration.start_from_scratch.string.high_precision_calculate;

/**
 * @author duhuang@iflytek.com
 * @version 2020/10/2 10:40
 * @see club.huangdu94.exploration.primary_algorithms.array.PlusOne
 */
public class PlusOne {
    public int[] plusOne(int[] digits) {
        int n = digits.length, i = n - 1, add = 1;
        while (i >= 0) {
            int sum = digits[i] + add;
            digits[i] = sum % 10;
            add = sum / 10;
            if (add == 0) {
                return digits;
            }
            i--;
        }
        int[] res = new int[n + 1];
        res[0] = 1;
        return res;
    }
}
