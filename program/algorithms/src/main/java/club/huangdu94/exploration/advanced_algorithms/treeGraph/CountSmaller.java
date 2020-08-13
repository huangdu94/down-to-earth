package club.huangdu94.exploration.advanced_algorithms.treeGraph;

import java.util.*;

/**
 * 计算右侧小于当前元素的个数
 * 给定一个整数数组 nums，按要求返回一个新数组counts。数组 counts 有该性质： counts[i] 的值是 nums[i] 右侧小于nums[i] 的元素的数量。
 * 示例：
 * 输入：[5,2,6,1]
 * 输出：[2,1,1,0]
 * 解释：
 * 5 的右侧有 2 个更小的元素 (2 和 1)
 * 2 的右侧仅有 1 个更小的元素 (1)
 * 6 的右侧有 1 个更小的元素 (1)
 * 1 的右侧有 0 个更小的元素
 *
 * @author duhuang@iflytek.com
 * @version 2020/8/11 11:33
 */
public class CountSmaller {
    /**
     * 暴力解思路：(超出时间限制)
     * 0. nums为null或nums.length==0则返回空list，否则进入1
     * 1. nums从后向前计算，res[nums.length-1]=0
     * ...
     * 2. 计算res[i-1]
     * 2.0 k=i-1
     * 2.1 k++，如果nums[i-1]>nums[k] res[i-1]++(直到k>nums.length-1)
     */
    public List<Integer> countSmaller2(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();
        int len = nums.length;
        List<Integer> res = new ArrayList<>(len);
        for (int i = 0; i < len; i++) res.add(0);
        for (int i = len - 2; i >= 0; i--) {
            int count = 0;
            for (int k = i + 1; k < len; k++)
                if (nums[i] > nums[k]) count++;
            res.set(i, count);
        }
        return res;
    }

    /**
     * 反向插入排序(155 ms,在所有Java提交中击败了8.66%的用户)
     */
    public List<Integer> countSmaller(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();
        int len = nums.length;
        List<Integer> res = new LinkedList<>();
        res.add(0, 0);
        for (int i = len - 2, j = i; i >= 0; j = --i) {
            int numi = nums[i];
            while (numi <= nums[j + 1]) {
                nums[j] = nums[j + 1];
                if (++j == len - 1)
                    break;
            }
            nums[j] = numi;
            res.add(0, len - j - 1);
        }
        return res;
    }

    public static void main(String[] args) {
        /*CountSmaller countSmaller = new CountSmaller();
        int[] nums = {2, 0, 1};
        List<Integer> res = countSmaller.countSmaller(nums);
        System.out.println(res);*/

        for (int i = 1; i <= 10; i++) {
            System.out.println(Integer.toBinaryString(i));
            System.out.println((int) (Math.log((i & (-i))) / Math.log(2)));
        }
    }

    /**
     * 离散化树状数组
     */
    private static class Solution1 {
        // 树状数组
        private int[] c;
        /* 去重后升序排序数组
           用于离散原数组
           value -> id 二分查找 返回index+1
           id -> value id-1作为index查*/
        private int[] a;

        public List<Integer> countSmaller(int[] nums) {
            int len = nums.length;
            List<Integer> resultList = new ArrayList<>(len);
            // 离散化: 去重 and 排序
            discretization(nums);
            // 初始化树状数组
            init(len + 1);
            // 从后往前遍历原数组
            for (int i = len - 1; i >= 0; --i) {
                // 二分查找得到离散id
                int id = getId(nums[i]);
                // 所有小于id的元素数量和加入结果list
                resultList.add(query(id - 1));
                // 根据id更新数量(所有包含id的和都加1)
                update(id);
            }
            // 反转结果list
            Collections.reverse(resultList);
            return resultList;
        }

        /**
         * 初始化树状数组
         * 数组长度为原数组长度加一即可，因为index=0我们不使用
         *
         * @param length 数组长度
         */
        private void init(int length) {
            c = new int[length];
        }

        /**
         * 树状数组核心
         * 这里利用的负数的存储特性，负数是以补码存储的，对于整数运算 x&(-x)有
         * 1. 当x为0时，即 0 & 0，结果为0；
         * 2. 当x为奇数时，最后一个比特位为1，取反加1没有进位，故x和-x除最后一位外前面的位正好相反，按位与结果为0。结果为1。
         * 3. 当x为偶数，且为2的m次方时，x的二进制表示中只有一位是1（从右往左的第m+1位），其右边有m位0，故x取反加1后，从右到左第有m个0，第m+1位及其左边全是1。这样，x& (-x) 得到的就是x。
         * 4. 当x为偶数，却不为2的m次方的形式时，可以写作x= y * (2^k)。其中，y的最低位为1。实际上就是把x用一个奇数左移k位来表示。这时，x的二进制表示最右边有k个0，从右往左第k+1位为1。当对x取反时，最右边的k位0变成1，第k+1位变为0；再加1，最右边的k位就又变成了0，第k+1位因为进位的关系变成了1。左边的位因为没有进位，正好和x原来对应的位上的值相反。二者按位与，得到：第k+1位上为1，左边右边都为0。结果为2^k。
         * 总结一下：x&(-x)，当x为0时结果为0；x为奇数时，结果为1；x为偶数时，结果为x中2的最大次方的因子。
         */
        private int lowBit(int x) {
            return x & (-x);
        }

        /**
         * 树状数组性质
         * A[i] 包含于 C[i + 2k]、C[(i + 2k) + 2k]...；
         */
        private void update(int pos) {
            while (pos <= c.length) {
                c[pos] += 1;
                pos += lowBit(pos);
            }
        }

        /**
         * 树状数组性质
         * C[i] = A[i - 2k+1] + A[i - 2k+2] + ... + A[i]
         */
        private int query(int pos) {
            int ret = 0;
            while (pos > 0) {
                ret += c[pos];
                pos -= lowBit(pos);
            }
            return ret;
        }

        /**
         * 离散化原数组
         *
         * @param nums 原数组
         */
        private void discretization(int[] nums) {
            // 1. 集合去重
            Set<Integer> set = new HashSet<>();
            for (int num : nums) set.add(num);
            // 2. 集合转化为数组
            a = new int[set.size()];
            int index = 0;
            for (int num : set) a[index++] = num;
            // 3. 排序数组
            Arrays.sort(a);
        }

        /**
         * 二分查找去重后排序数组得到value对应的id
         *
         * @param x value值
         * @return 对应的id
         */
        private int getId(int x) {
            return Arrays.binarySearch(a, x) + 1;
        }
    }

    /**
     * 归并排序
     * 普通的归并排序稍微附加了一些技巧用于解题
     * 1. tempIndex 用于记录index在归并排序原数组元素顺序变化后，保证最初的index记录下来
     * 2. 结果list增加的时机
     * 2.1 归并中左边比右边大的时候结果list需要增加
     * 2.2 增加的值为此时归并排序右边一半已经完成排序的个数j - (mid + 1)
     */
    private static class Solution2 {
        private int[] index;

        private int[] temp;

        private int[] tempIndex;

        private List<Integer> ans;

        public void Merge(int[] a, int l, int mid, int r) {
            int i = l, j = mid + 1, p = l;
            while (i <= mid && j <= r) {
                if (a[i] <= a[j]) {
                    temp[p] = a[i];
                    tempIndex[p] = index[i];
                    //ans[index[i]] += (j - mid - 1);
                    ans.set(index[i], ans.get(index[i]) + (j - mid - 1));
                    ++i;
                    ++p;
                } else {
                    temp[p] = a[j];
                    tempIndex[p] = index[j];
                    ++j;
                    ++p;
                }
            }
            while (i <= mid) {
                temp[p] = a[i];
                tempIndex[p] = index[i];
                // 此时j一定为 r+1
                ans.set(index[i], ans.get(index[i]) + (r - mid));
                ++i;
                ++p;
            }
            while (j <= r) {
                temp[p] = a[j];
                tempIndex[p] = index[j];
                ++j;
                ++p;
            }
            for (int k = l; k <= r; ++k) {
                index[k] = tempIndex[k];
                a[k] = temp[k];
            }
        }

        public void MergeSort(int[] a, int l, int r) {
            if (l >= r) {
                return;
            }
            int mid = (l + r) >> 1;
            MergeSort(a, l, mid);
            MergeSort(a, mid + 1, r);
            Merge(a, l, mid, r);
        }

        public List<Integer> countSmaller(int[] nums) {
            int len = nums.length;
            this.temp = new int[len];
            this.index = new int[len];
            this.tempIndex = new int[len];
            this.ans = new ArrayList<>(len);
            for (int i = 0; i < len; ++i) {
                index[i] = i;
            }
            int l = 0, r = len - 1;
            MergeSort(nums, l, r);
            return ans;
        }
    }
}
