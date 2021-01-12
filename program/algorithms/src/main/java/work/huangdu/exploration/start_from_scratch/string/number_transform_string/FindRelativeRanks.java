package work.huangdu.exploration.start_from_scratch.string.number_transform_string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 506. 相对名次
 * 给出 N 名运动员的成绩，找出他们的相对名次并授予前三名对应的奖牌。前三名运动员将会被分别授予 “金牌”，“银牌” 和“ 铜牌”（"Gold Medal", "Silver Medal", "Bronze Medal"）。
 * (注：分数越高的选手，排名越靠前。)
 * 示例 1:
 * 输入: [5, 4, 3, 2, 1]
 * 输出: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]
 * 解释: 前三名运动员的成绩为前三高的，因此将会分别被授予 “金牌”，“银牌”和“铜牌” ("Gold Medal", "Silver Medal" and "Bronze Medal").
 * 余下的两名运动员，我们只需要通过他们的成绩计算将其相对名次即可。
 * 提示:
 * N 是一个正整数并且不会超过 10000。
 * 所有运动员的成绩都不相同。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/9/26 17:44
 */
public class FindRelativeRanks {
    public String[] findRelativeRanks(int[] nums) {
        int len = nums.length, rank = len;
        // 记录各分数对应的下标
        // Map<Integer, Integer> scoreIndexMap = new TreeMap<>((o1, o2) -> o2 - o1);
        Map<Integer, Integer> scoreIndexMap = new HashMap<>(len);
        for (int i = 0; i < len; i++) {
            scoreIndexMap.put(nums[i], i);
        }
        Arrays.sort(nums);
        String[] res = new String[len];
        for (int n : nums) {
            res[scoreIndexMap.get(n)] = getRankString(rank--);
        }
        /*for (int index : scoreIndexMap.values()) {
            res[index] = getRankString(rank++);
        }*/
        return res;
    }

    private String getRankString(int rank) {
        if (rank > 3) return Integer.toString(rank);
        if (rank == 1) return "Gold Medal";
        if (rank == 2) return "Silver Medal";
        return "Bronze Medal";
    }
}
