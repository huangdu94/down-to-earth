package work.huangdu.exploration.advanced_algorithms.math;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 149. 直线上最多的点数
 * 给定一个二维平面，平面上有n个点，求最多有多少个点在同一条直线上。
 * 示例 1:
 * 输入: [[1,1],[2,2],[3,3]]
 * 输出: 3
 * 解释:
 * ^
 * |
 * |     o
 * |   o
 * | o
 * +------------->
 * 0 1 2 3  4
 * 示例2:
 * 输入: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * 输出: 4
 * 解释:
 * ^
 * |
 * |     o
 * |   o     o
 * |     o
 * | o     o
 * +------------------->
 * 0 1 2 3 4 5 6
 *
 * @author duhuang@iflytek.com
 * @version 2020/9/7 20:35
 */
public class MaxPoints {
    public int maxPoints(int[][] points) {
        int len = points.length;
        if (len <= 2) return len;
        // key: y=k*x+b 或 x=x0 value:该线上的点号Set
        int max = 2;
        Map<String, Set<Integer>> lineMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                String line = getLine(points[i], points[j]);
                Set<Integer> pointSet = lineMap.get(line);
                if (pointSet == null) {
                    pointSet = new HashSet<>();
                    pointSet.add(i);
                    pointSet.add(j);
                    lineMap.put(line, pointSet);
                } else {
                    pointSet.add(i);
                    pointSet.add(j);
                    if (pointSet.size() > max) max = pointSet.size();
                }
            }
        }
        return max;
    }

    private String getLine(int[] p1, int[] p2) {
        StringBuilder sb = new StringBuilder();
        if (p1[0] == p2[0]) {
            return sb.append("x=").append(p1[0]).toString();
        } else {
            sb.append("y=");
            if (p1[1] == p2[1]) {
                return sb.append(p1[1]).toString();
            }
            return appendSlopeAndIntercept(sb, p1, p2);
        }
    }

    /**
     * append 斜率 和 截距
     */
    private String appendSlopeAndIntercept(StringBuilder sb, int[] p1, int[] p2) {
        int deltaX = p1[0] - p2[0];
        int deltaY = p1[1] - p2[1];
        int slopeGcd = computeGcd(deltaX, deltaY);
        int slopeDenominator = deltaY / slopeGcd;
        int slopeNumerator = deltaX / slopeGcd;
        int interceptDenominator = p1[1] * slopeNumerator - p1[0] * slopeDenominator;
        int interceptGcd = computeGcd(interceptDenominator, slopeNumerator);
        interceptDenominator = interceptDenominator / interceptGcd;
        int interceptNumerator = slopeNumerator / interceptGcd;
        return sb.append(slopeDenominator).append('/').append(slopeNumerator).append("x+").append(interceptDenominator).append('/').append(interceptNumerator).toString();
    }

    /**
     * 辗转相除法求最大公约数
     */
    private int computeGcd(int p1, int p2) {
        if (p2 == 0) return p1;
        int r = p1 % p2;
        return computeGcd(p2, r);
    }

    public static void main(String[] args) {
        MaxPoints maxPoints = new MaxPoints();
        int[][] points = {{3, 10}, {0, 2}, {0, 2}, {3, 10}};
        System.out.println(maxPoints.maxPoints(points));
    }
}
