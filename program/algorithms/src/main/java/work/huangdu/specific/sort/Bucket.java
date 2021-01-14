package work.huangdu.specific.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 桶排序
 * 时间复杂度
 * 平均 O(n+k) 最好 O(n^2) 最坏 O(n)
 * 空间复杂
 * O(n+k)
 * 稳定
 * 桶排序是计数排序的升级版。它利用了函数的映射关系，高效与否的关键就在于这个映射函数的确定。桶排序 (Bucket sort)的工作的原理：假设输入数据服从均匀分布，将数据分到有限数量的桶里，每个桶再分别排序（有可能再使用别的排序算法或是以递归方式继续使用桶排序进行排）。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/7/18 16:33
 */
public class Bucket {
    // 控制每个桶中元素数量(每个桶分别使用插入排序)
    private static final int BUCKET_ELEMENT_COUNT = 46;

    /**
     * 需要根据数据情况选择映射函数，保证各桶中元素数量差不多
     */
    public static void sort(int[] nums) {
        long start = System.currentTimeMillis();
        // 采用均分布分桶
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n > max)
                max = n;
            else if (n < min)
                min = n;
        }
        int bucketCount = (max - min) / BUCKET_ELEMENT_COUNT + 1;
        List<List<Integer>> bucketList = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            bucketList.add(new ArrayList<>(BUCKET_ELEMENT_COUNT));
        }
        for (int n : nums) {
            int bucketNo = (n - min) / BUCKET_ELEMENT_COUNT;
            bucketList.get(bucketNo).add(n);
        }
        // 对每个桶分别排序，并输出到原数组
        int k = 0;
        for (List<Integer> bucket : bucketList) {
            insertion(bucket);
            for (int element : bucket) {
                nums[k++] = element;
            }
        }
        long end = System.currentTimeMillis();
        System.out.printf("桶排序耗时: %s ms%n", end - start);
        System.out.printf("排序结果: %s%n", Arrays.toString(nums));
    }

    private static void insertion(List<Integer> bucket) {
        for (int i = 0; i < bucket.size() - 1; i++) {
            int temp = bucket.get(i + 1);
            int j = i;
            while (temp < bucket.get(j)) {
                bucket.set(j + 1, bucket.get(j));
                if (--j < 0)
                    break;
            }
            bucket.set(j + 1, temp);
        }
    }
}
