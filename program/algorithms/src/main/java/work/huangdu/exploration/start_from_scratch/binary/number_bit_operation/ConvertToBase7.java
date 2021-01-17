package work.huangdu.exploration.start_from_scratch.binary.number_bit_operation;

/**
 * 504. 七进制数
 * 给定一个整数，将其转化为7进制，并以字符串形式输出。
 * 示例 1:
 * 输入: 100
 * 输出: "202"
 * 示例 2:
 * 输入: -7
 * 输出: "-10"
 * 注意: 输入范围是 [-1e7, 1e7] 。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/10/10 16:05
 */
public class ConvertToBase7 {
    public String convertToBase7(int num) {
        return Integer.toString(num, 7);
    }
}
