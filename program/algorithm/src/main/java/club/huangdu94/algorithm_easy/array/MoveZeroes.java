package club.huangdu94.algorithm_easy.array;

/**
 * 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 示例:
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/26 15:38
 */
public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        int zeroIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0 && zeroIndex == -1)
                zeroIndex = i;
            else if (nums[i] != 0 && zeroIndex != -1 && i > zeroIndex) {
                nums[zeroIndex] = nums[i];
                nums[i] = 0;
                for (zeroIndex++; zeroIndex < nums.length && nums[zeroIndex] != 0; zeroIndex++) ;
                if (zeroIndex == nums.length)
                    return;
            }
        }
    }

    public void moveZeroes2(int[] nums) {
        int i = -1;
        // 数组的最后一位index
        int bound = nums.length - 1;
        while (i < bound && nums[++i] != 0) ;
        //说明数组最后一个才是0或者都不为0,不需要移动
        if (i == bound)
            return;
        //说明数组非最后一个为0
        // 0的数量
        int count = 1;
        // 0的位置
        int index = i;
        // 是否需要末尾置0,未发生移动则不需要置0
        boolean flag = false;
        do {
            if (nums[++i] == 0) {
                count++;
            } else {
                flag = true;
                nums[index++] = nums[i];
            }
        } while (i < bound);
        // 末尾置0
        if (flag) {
            for (i = 0; i < count; i++) {
                nums[bound - i] = 0;
            }
        }
    }
}
