package work.huangdu.exploration.start_from_scratch.greedy.sub_array;

/**
 * 134. 加油站
 * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 * 说明:
 * 如果题目有解，该答案即为唯一答案。
 * 输入数组均为非空数组，且长度相同。
 * 输入数组中的元素均为非负数。
 * 示例 1:
 * 输入:
 * gas  = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 * 输出: 3
 * 解释:
 * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 * 因此，3 可为起始索引。
 * 示例 2:
 * 输入:
 * gas  = [2,3,4]
 * cost = [3,4,3]
 * 输出: -1
 * 解释:
 * 你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
 * 我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
 * 开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
 * 你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
 * 因此，无论怎样，你都不可能绕环路行驶一周。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/11/17 20:15
 */
public class CanCompleteCircuit {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // 1. 如果转一圈可以加的gas小于转一圈的cost则不可能转一圈，直接返回false
        int gasSum = 0, costSum = 0;
        for (int g : gas) gasSum += g;
        for (int c : cost) costSum += c;
        if (gasSum < costSum) return -1;
        int n = gas.length;
        // 2. 枚举每一个可能的开始点
        for (int start = 0; start < n; start++) {
            // 2.1 如果第一个点gas就不够直接continue
            if (gas[start] < cost[start]) {
                continue;
            }
            // 2.2 开始模拟转圈
            int step = 0, location = start;
            gasSum = costSum = 0;
            while (step < n) {
                gasSum += gas[location];
                costSum += cost[location++];
                // 转圈
                if (location >= n) {
                    location -= n;
                }
                // gas不够break
                if (gasSum < costSum) {
                    break;
                }
                step++;
            }
            // 2.3 成功转完一圈返回start
            if (step == n) {
                return start;
            }
        }
        return -1;
    }

    public int canCompleteCircuit2(int[] gas, int[] cost) {
        // 1. 如果转一圈可以加的gas小于转一圈的cost则不可能转一圈，直接返回false
        int gasSum = 0, costSum = 0;
        for (int g : gas) gasSum += g;
        for (int c : cost) costSum += c;
        if (gasSum < costSum) return -1;
        int n = gas.length, start = 0;
        // 2. o(n)
        while (start < n) {
            // 2.1 如果第一个点gas就不够，就直接下一个
            if (gas[start] >= cost[start]) {
                // 2.2 开始模拟转圈
                int location = start;
                gasSum = costSum = 0;
                while (location < n) {
                    gasSum += gas[location];
                    costSum += cost[location++];
                    // gas不够break
                    if (gasSum < costSum) {
                        break;
                    }
                }
                if (location == n) {
                    // 2.3 成功转完一圈返回start
                    return start;
                } else {
                    start = location - 1;
                }
            }
            // 2.4 没成功转完一圈，则说明这个加油站有问题，从下一个加油站开始遍历
            start++;
        }
        return -1;
    }

    public static void main(String[] args) {
        CanCompleteCircuit ccc = new CanCompleteCircuit();
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};
        System.out.println(ccc.canCompleteCircuit2(gas, cost));
    }

    public int canCompleteCircuit3(int[] gas, int[] cost) {
        int n = gas.length, sum = 0, cur = 0, start = 0;
        for (int i = 0; i < n; i++) {
            sum += (gas[i] - cost[i]);
            cur += (gas[i] - cost[i]);
            if (cur < 0) {
                cur = 0;
                start = i + 1;
            }
        }
        return sum < 0 ? -1 : start;
    }
}
