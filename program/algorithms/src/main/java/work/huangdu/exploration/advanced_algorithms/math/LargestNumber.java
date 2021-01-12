package work.huangdu.exploration.advanced_algorithms.math;

import java.util.Arrays;

/**
 * 最大数
 * 给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。
 * 示例 1:
 * 输入: [10,2]
 * 输出: 210
 * 示例 2:
 * 输入: [3,30,34,5,9]
 * 输出: 9534330
 * 说明: 输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/8/6 16:28
 */
public class LargestNumber {
    public String largestNumber(int[] nums) {
        String[] numStrings = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numStrings[i] = Integer.toString(nums[i]);
        }
        Arrays.sort(numStrings, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
        /*Arrays.sort(numStrings, (o1, o2) -> {
            int len1 = o1.length();
            int len2 = o2.length();
            int i = 0, j = 0;
            while (i < len1 && j < len2) {
                char c1 = o1.charAt(i);
                char c2 = o2.charAt(j);
                if (c1 != c2) {
                    return c2 - c1;
                }
                i++;
                j++;
            }
            if (len1 == len2) {
                return 0;
            } else {
                int diff = 0;
                int first = o1.charAt(0);
                if (len1 > len2) {
                    while (i < len1 && diff == 0) {
                        diff = first - o1.charAt(i++);
                    }
                    if (diff == 0) {
                        return o2.charAt(j - 1) - first;
                    }
                } else {
                    while (j < len2 && diff == 0) {
                        diff = o2.charAt(j++) - first;
                    }
                    if (diff == 0) {
                        return first - o1.charAt(i - 1);
                    }
                }
                return diff;
            }
        });*/
        if ("0".equals(numStrings[0])) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        for (
                String numString : numStrings) {
            sb.append(numString);
        }
        return sb.toString();
    }

    // 读错题
    /*
    public String largestNumber(int[] nums) {
        int[] count = new int[10];
        for (int num : nums)
            if (num == 0) count[0]++;
            else
                while (num != 0) {
                    count[num % 10]++;
                    num /= 10;
                }
        StringBuilder sb = new StringBuilder();
        for (int i = 9; i >= 0; i--)
            while (count[i]-- > 0) sb.append(i);
        return sb.toString();
    }*/
    public static void main(String[] args) {
        LargestNumber largestNumber = new LargestNumber();
        int[] nums = {9051, 5526, 2264, 5041, 1630, 5906, 6787, 8382, 4662, 4532, 6804, 4710, 4542, 2116, 7219, 8701, 8308, 957, 8775, 4822, 396, 8995, 8597, 2304, 8902, 830, 8591, 5828, 9642, 7100, 3976, 5565, 5490, 1613, 5731, 8052, 8985, 2623, 6325, 3723, 5224, 8274, 4787, 6310, 3393, 78, 3288, 7584, 7440, 5752, 351, 4555, 7265, 9959, 3866, 9854, 2709, 5817, 7272, 43, 1014, 7527, 3946, 4289, 1272, 5213, 710, 1603, 2436, 8823, 5228, 2581, 771, 3700, 2109, 5638, 3402, 3910, 871, 5441, 6861, 9556, 1089, 4088, 2788, 9632, 6822, 6145, 5137, 236, 683, 2869, 9525, 8161, 8374, 2439, 6028, 7813, 6406, 7519};
        String res = largestNumber.largestNumber(nums);
        System.out.println(res);
    }

    public String largestNumber2(int[] nums) {
        int n = nums.length;
        if (n == 0) return "";
        if (n == 1) return Integer.toString(nums[0]);
        String[] strings = new String[n];
        for (int i = 0; i < n; i++) {
            strings[i] = Integer.toString(nums[i]);
        }
        Arrays.sort(strings, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
        if ("0".equals(strings[0])) return "0";
        StringBuilder sb = new StringBuilder();
        for (String s : strings) {
            sb.append(s);
        }
        return sb.toString();
    }
}
