package work.huangdu.exploration.start_from_scratch.binary.number_bit_operation;

import java.util.Arrays;

/**
 * 319. 灯泡开关
 * 初始时有 n 个灯泡关闭。 第 1 轮，你打开所有的灯泡。 第 2 轮，每两个灯泡你关闭一次。 第 3 轮，每三个灯泡切换一次开关（如果关闭则开启，如果开启则关闭）。第 i 轮，每 i 个灯泡切换一次开关。 对于第 n 轮，你只切换最后一个灯泡的开关。 找出 n 轮后有多少个亮着的灯泡。
 * 示例:
 * 输入: 3
 * 输出: 1
 * 解释:
 * 初始时, 灯泡状态 [关闭, 关闭, 关闭].
 * 第一轮后, 灯泡状态 [开启, 开启, 开启].
 * 第二轮后, 灯泡状态 [开启, 关闭, 开启].
 * 第三轮后, 灯泡状态 [开启, 关闭, 关闭].
 * 你应该返回 1，因为只有一个灯泡还亮着。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/10/18 10:18
 */
public class BulbSwitch {
    // 暴力模拟 时间复杂度o(n^2) 空间复杂度o(n)
    // 测试样例 99999999 超时
    public int bulbSwitch2(int n) {
        boolean[] switchs = new boolean[n];
        Arrays.fill(switchs, true);
        for (int round = 2; round <= n; round++) {
            for (int i = round - 1; i < n; i += round) {
                switchs[i] = !switchs[i];
            }
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (switchs[i]) {
                count++;
            }
        }
        return count;
    }

    // 数学推导
    public int bulbSwitch(int n) {
        int i = 1;
        while (i * i <= n) {
            i++;
        }
        return i - 1;
    }
}
