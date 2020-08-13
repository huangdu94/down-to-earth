package club.huangdu94.exploration.primary_algorithms.array;

import java.math.BigInteger;

/**
 * @author duhuang@iflytek.com
 * @version 2020/7/26 15:31
 */
public class PlusOne {
    public int[] plusOne(int[] digits) {
        StringBuilder digitsStr = new StringBuilder();
        for (int i : digits) {
            digitsStr.append(i);
        }
        BigInteger bigIntegerAddOne = new BigInteger(digitsStr.toString()).add(BigInteger.valueOf(1L));
        String bigIntegerAddOneStr = bigIntegerAddOne.toString();
        int[] returnArr = new int[bigIntegerAddOneStr.length()];
        for (int i = 0; i < bigIntegerAddOneStr.length(); i++) {
            returnArr[i] = bigIntegerAddOneStr.charAt(i) - '0';
        }
        return returnArr;
    }

    public int[] plusOne2(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] == 9) {
                digits[i] = 0;
            } else {
                digits[i]++;
                return digits;
            }
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }
}
