package work.huangdu.question_bank.easy;

import java.util.Arrays;

/**
 * 1232. 缀点成线
 * 在一个 XY 坐标系中有一些点，我们用数组 coordinates 来分别记录它们的坐标，其中 coordinates[i] = [x, y] 表示横坐标为 x、纵坐标为 y 的点。
 * <p>
 * 请你来判断，这些点是否在该坐标系中属于同一条直线上，是则返回 true，否则请返回 false。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
 * 输出：true
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= coordinates.length <= 1000
 * coordinates[i].length == 2
 * -10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
 * coordinates 中不含重复的点
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/1/17
 */
public class CheckStraightLine {
    public boolean checkStraightLine(int[][] coordinates) {
        int n = coordinates.length;
        if (n == 2) return true;
        // 斜率不存在时为null，斜率存在的时候，斜率slope[0]表示斜率分子，slope[1]表示斜率分母
        int[] firstPoint = coordinates[0], slope = getSlope(firstPoint, coordinates[1]);
        for (int i = 2; i < n; i++) {
            if (!Arrays.equals(getSlope(firstPoint, coordinates[i]), slope)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 求point1和point2确定的直线的斜率
     */
    private int[] getSlope(int[] point1, int[] point2) {
        int x0 = point1[0], y0 = point1[1], x1 = point2[0], y1 = point2[1];
        if (x0 == x1) {
            return null;
        }
        if (y0 == y1) {
            return new int[]{0, 1};
        }
        int slopeNumerator = y1 - y0, slopeDenominator = x1 - x0, slopeGcd = computeGcd(slopeNumerator, slopeDenominator);
        return new int[]{slopeNumerator / slopeGcd, slopeDenominator / slopeGcd};
    }

    /**
     * 辗转相除法求最大公约数
     * greatest common divisor
     */
    private int computeGcd(int number1, int number2) {
        if (number2 == 0) return number1;
        int remainder = number1 % number2;
        return computeGcd(number2, remainder);
    }

    public static void main(String[] args) {
        CheckStraightLine scl = new CheckStraightLine();
        System.out.println(scl.checkStraightLine(new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}}));
    }
}
