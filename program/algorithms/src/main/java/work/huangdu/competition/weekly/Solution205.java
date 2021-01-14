package work.huangdu.competition.weekly;

import java.util.Arrays;

/**
 * 第205场周赛
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/9/6 10:10
 */
public class Solution205 {
    /**
     * 5507. 替换所有的问号 题目难度Easy
     * 给你一个仅包含小写英文字母和 '?' 字符的字符串 s，请你将所有的 '?' 转换为若干小写字母，使最终的字符串不包含任何 连续重复 的字符。
     * 注意：你 不能 修改非 '?' 字符。
     * 题目测试用例保证 除 '?' 字符 之外，不存在连续重复的字符。
     * 在完成所有转换（可能无需转换）后返回最终的字符串。如果有多个解决方案，请返回其中任何一个。可以证明，在给定的约束条件下，答案总是存在的。
     * 示例 1：
     * 输入：s = "?zs"
     * 输出："azs"
     * 解释：该示例共有 25 种解决方案，从 "azs" 到 "yzs" 都是符合题目要求的。只有 "z" 是无效的修改，因为字符串 "zzs" 中有连续重复的两个 'z' 。
     * 示例 2：
     * 输入：s = "ubv?w"
     * 输出："ubvaw"
     * 解释：该示例共有 24 种解决方案，只有替换成 "v" 和 "w" 不符合题目要求。因为 "ubvvw" 和 "ubvww" 都包含连续重复的字符。
     * 示例 3：
     * 输入：s = "j?qg??b"
     * 输出："jaqgacb"
     * 示例 4：
     * 输入：s = "??yw?ipkj?"
     * 输出："acywaipkja"
     * 提示：
     * 1 <= s.length <= 100
     * s 仅包含小写英文字母和 '?' 字符
     */
    public String modifyString(String s) {
        int len = s.length();
        char[] chars = s.toCharArray();
        for (int i = 0; i < len; i++) {
            if (chars[i] == '?') {
                char prev = '*', next = '*';
                if (i != 0) {
                    prev = chars[i - 1];
                }
                if (i != len - 1 && chars[i + 1] != '?') {
                    next = chars[i + 1];
                }
                char c = 'a';
                while (true) {
                    if (c != prev && c != next) {
                        chars[i] = c;
                        break;
                    }
                    c += 1;
                }
            }
        }
        return new String(chars);
    }

    /**
     * 5508. 数的平方等于两数乘积的方法数
     * 给你两个整数数组 nums1 和 nums2 ，请你返回根据以下规则形成的三元组的数目（类型 1 和类型 2 ）：
     * 类型 1：三元组 (i, j, k) ，如果 nums1[i]2 == nums2[j] * nums2[k] 其中 0 <= i < nums1.length 且 0 <= j < k < nums2.length
     * 类型 2：三元组 (i, j, k) ，如果 nums2[i]2 == nums1[j] * nums1[k] 其中 0 <= i < nums2.length 且 0 <= j < k < nums1.length
     * 示例 1：
     * 输入：nums1 = [7,4], nums2 = [5,2,8,9]
     * 输出：1
     * 解释：类型 1：(1,1,2), nums1[1]^2 = nums2[1] * nums2[2] (4^2 = 2 * 8)
     * 示例 2：
     * 输入：nums1 = [1,1], nums2 = [1,1,1]
     * 输出：9
     * 解释：所有三元组都符合题目要求，因为 1^2 = 1 * 1
     * 类型 1：(0,0,1), (0,0,2), (0,1,2), (1,0,1), (1,0,2), (1,1,2), nums1[i]^2 = nums2[j] * nums2[k]
     * 类型 2：(0,0,1), (1,0,1), (2,0,1), nums2[i]^2 = nums1[j] * nums1[k]
     * 示例 3：
     * 输入：nums1 = [7,7,8,3], nums2 = [1,2,9,7]
     * 输出：2
     * 解释：有两个符合题目要求的三元组
     * 类型 1：(3,0,2), nums1[3]^2 = nums2[0] * nums2[2]
     * 类型 2：(3,0,1), nums2[3]^2 = nums1[0] * nums1[1]
     * 示例 4：
     * 输入：nums1 = [4,7,9,11,23], nums2 = [3,5,1024,12,18]
     * 输出：0
     * 解释：不存在符合题目要求的三元组
     * 提示：
     * 1 <= nums1.length, nums2.length <= 1000
     * 1 <= nums1[i], nums2[i] <= 10^5
     */
    public int numTriplets(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        return helper(nums1, nums2) + helper(nums2, nums1);
    }

    private int helper(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length, res = 0, i = 0;
        while (i < len1) {
            int iCount = 1;
            while (i < len1 - 1 && nums1[i] == nums1[i + 1]) {
                iCount++;
                i++;
            }
            long left = (long) nums1[i] * nums1[i];
            int j = 0, k = len2 - 1, jCount = 1, kCount = 1;
            while (j < k) {
                while (j < k && nums2[j] == nums2[j + 1]) {
                    jCount++;
                    j++;
                }
                if (j == k) break;
                while (k > j && nums2[k] == nums2[k - 1]) {
                    kCount++;
                    k--;
                }
                long right = (long) nums2[j] * nums2[k];
                if (left == right) {
                    res += (iCount * jCount * kCount);
                    jCount = 1;
                    kCount = 1;
                    j++;
                    k--;
                } else if (left > right) {
                    jCount = 1;
                    j++;
                } else {
                    kCount = 1;
                    k--;
                }
            }
            if ((long) nums2[j] * nums2[j] == left) {
                if (jCount > 1) {
                    res += iCount * jCount * (jCount - 1) / 2;
                } else if (kCount > 1) {
                    res += iCount * kCount * (kCount - 1) / 2;
                }
            }
            i++;
        }
        return res;
    }

    /**
     * 5509. 避免重复字母的最小删除成本
     * 给你一个字符串 s 和一个整数数组 cost ，其中 cost[i] 是从 s 中删除字符 i 的代价。
     * 返回使字符串任意相邻两个字母不相同的最小删除成本。
     * 请注意，删除一个字符后，删除其他字符的成本不会改变。
     * 示例 1：
     * 输入：s = "abaac", cost = [1,2,3,4,5]
     * 输出：3
     * 解释：删除字母 "a" 的成本为 3，然后得到 "abac"（字符串中相邻两个字母不相同）。
     * 示例 2：
     * 输入：s = "abc", cost = [1,2,3]
     * 输出：0
     * 解释：无需删除任何字母，因为字符串中不存在相邻两个字母相同的情况。
     * 示例 3：
     * 输入：s = "aabaa", cost = [1,2,3,4,1]
     * 输出：2
     * 解释：删除第一个和最后一个字母，得到字符串 ("aba") 。
     * 提示：
     * s.length == cost.length
     * 1 <= s.length, cost.length <= 10^5
     * 1 <= cost[i] <= 10^4
     * s 中只含有小写英文字母
     */
    public int minCost(String s, int[] cost) {
        int len = s.length(), i = 0, minCost = 0;
        char[] chars = s.toCharArray();
        while (i < len - 1) {
            int start = i;
            while (i < len - 1 && chars[i] == chars[i + 1]) {
                i++;
            }
            if (start != i) {
                int max = 0;
                for (int k = start; k <= i; k++) {
                    minCost += cost[k];
                    if (max < cost[k]) {
                        max = cost[k];
                    }
                }
                minCost -= max;
            }
            i++;
        }
        return minCost;
    }

    /**
     * 5510. 保证图可完全遍历
     * Alice 和 Bob 共有一个无向图，其中包含 n 个节点和 3  种类型的边：
     * 类型 1：只能由 Alice 遍历。
     * 类型 2：只能由 Bob 遍历。
     * 类型 3：Alice 和 Bob 都可以遍历。
     * 给你一个数组 edges ，其中 edges[i] = [typei, ui, vi] 表示节点 ui 和 vi 之间存在类型为 typei 的双向边。请你在保证图仍能够被 Alice和 Bob 完全遍历的前提下，找出可以删除的最大边数。如果从任何节点开始，Alice 和 Bob 都可以到达所有其他节点，则认为图是可以完全遍历的。
     * 返回可以删除的最大边数，如果 Alice 和 Bob 无法完全遍历图，则返回 -1 。
     * 示例 1：
     * 输入：n = 4, edges = [[3,1,2],[3,2,3],[1,1,3],[1,2,4],[1,1,2],[2,3,4]]
     * 输出：2
     * 解释：如果删除 [1,1,2] 和 [1,1,3] 这两条边，Alice 和 Bob 仍然可以完全遍历这个图。再删除任何其他的边都无法保证图可以完全遍历。所以可以删除的最大边数是 2 。
     * 示例 2：
     * 输入：n = 4, edges = [[3,1,2],[3,2,3],[1,1,4],[2,1,4]]
     * 输出：0
     * 解释：注意，删除任何一条边都会使 Alice 和 Bob 无法完全遍历这个图。
     * 示例 3：
     * 输入：n = 4, edges = [[3,2,3],[1,1,2],[2,3,4]]
     * 输出：-1
     * 解释：在当前图中，Alice 无法从其他节点到达节点 4 。类似地，Bob 也不能达到节点 1 。因此，图无法完全遍历。
     * 提示：
     * 1 <= n <= 10^5
     * 1 <= edges.length <= min(10^5, 3 * n * (n-1) / 2)
     * edges[i].length == 3
     * 1 <= edges[i][0] <= 3
     * 1 <= edges[i][1] < edges[i][2] <= n
     * 所有元组 (typei, ui, vi) 互不相同
     */
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        int len = edges.length;
        UnionFindSets ufs = new UnionFindSets(n + 1);
        for (int[] edge : edges) {
            if (edge[0] == 3) {
                ufs.union(edge[1], edge[2]);
                if (ufs.getCount() == 1) break;
            }
        }
        // n-common条公共边可用 common=1说明仅公共边就可以连通，其它都可删掉
        int common = ufs.getCount();
        if (common == 1) return len - n + 1;
        int[] commonParent = Arrays.copyOf(ufs.getParent(), n + 1);
        for (int[] edge : edges) {
            if (edge[0] == 1) {
                ufs.union(edge[1], edge[2]);
                if (ufs.getCount() == 1) break;
            }
        }
        if (ufs.getCount() != 1) return -1;
        ufs.reset(commonParent, common);
        for (int[] edge : edges) {
            if (edge[0] == 2) {
                ufs.union(edge[1], edge[2]);
                if (ufs.getCount() == 1) break;
            }
        }
        if (ufs.getCount() != 1) return -1;
        // len - (2 * (n - 1) - (n - common))
        return len - n - common + 2;
    }

    public static void main(String[] args) {
        Solution205 solution = new Solution205();
        String s = "?ab";
        //System.out.println(solution.modifyString(s));
        int[] nums1 = {1, 3, 1, 2};
        int[] nums2 = {2, 3, 5, 3, 2};
        System.out.println(solution.numTriplets(nums1, nums2));
    }
}

class UnionFindSets {
    private int count;
    private int[] parent;

    // 初始化
    public UnionFindSets(int n) {
        parent = new int[n];
        count = n - 1;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    // 初始化
    public UnionFindSets(int[] parent, int count) {
        this.parent = parent;
        this.count = count;
    }

    public void reset(int[] parent, int count) {
        this.parent = parent;
        this.count = count;
    }

    // 查找(路径压缩)
    public int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    // 合并
    public void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);
        if (parentA == parentB) return;
        count--;
        parent[parentA] = parentB;
    }

    public int getCount() {
        return count;
    }

    public int[] getParent() {
        return parent;
    }
}