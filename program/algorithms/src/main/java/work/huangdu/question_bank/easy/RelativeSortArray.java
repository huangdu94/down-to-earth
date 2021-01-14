package work.huangdu.question_bank.easy;

import java.util.Arrays;

/**
 * 1122. 数组的相对排序
 * 给你两个数组，arr1 和 arr2，
 * arr2 中的元素各不相同
 * arr2 中的每个元素都出现在 arr1 中
 * 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
 * 示例：
 * 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 * 输出：[2,2,2,1,4,3,3,9,6,7,19]
 * 提示：
 * arr1.length, arr2.length <= 1000
 * 0 <= arr1[i], arr2[i] <= 1000
 * arr2 中的元素 arr2[i] 各不相同
 * arr2 中的每个元素 arr2[i] 都出现在 arr1 中
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/11/14 10:41
 */
public class RelativeSortArray {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int j = 0;
        // 1. arr1计数
        int[] counts = new int[1001];
        for (int num : arr1) {
            counts[num]++;
        }
        // 2. 相对于arr2顺序输出，输出后count置0
        for (int num : arr2) {
            int count = counts[num];
            for (int k = 0; k < count; k++) {
                arr1[j++] = num;
            }
            counts[num] = 0;
        }
        // 3. arr1中剩余数进行升序排序
        for (int num = 0; num <= 1000; num++) {
            int count = counts[num];
            if (count != 0) {
                for (int k = 0; k < count; k++) {
                    arr1[j++] = num;
                }
            }
        }
        return arr1;
    }

    public static void main(String[] args) {
        int[] arr1 = {2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19};
        int[] arr2 = {2, 1, 4, 3, 9, 6};
        RelativeSortArray rsa = new RelativeSortArray();
        System.out.println(Arrays.toString(rsa.relativeSortArray(arr1, arr2)));
    }
}
