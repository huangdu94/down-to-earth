package club.huangdu94.algorithm_middle.math;

/**
 * Excel表列序号
 * 给定一个Excel表格中的列名称，返回其相应的列序号。
 * 例如，
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 * 示例 1:
 * 输入: "A"
 * 输出: 1
 * 示例 2:
 * 输入: "AB"
 * 输出: 28
 * 示例 3:
 * 输入: "ZY"
 * 输出: 701
 * 致谢：
 * 特别感谢 @ts 添加此问题并创建所有测试用例。
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/22 0:16
 */
public class TitleToNumber {
    public int titleToNumber(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            sum = sum * 26 + (c - 64);
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println((char) 64);
    }
}
