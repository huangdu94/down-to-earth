package club.huangdu94.algorithm_easy.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 帕斯卡三角形
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/1 21:55
 */
public class Pasika {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>(numRows);
        if (numRows == 0)
            return result;
        List<Integer> oneRows = Collections.singletonList(1);
        result.add(oneRows);
        if (numRows == 1)
            return result;
        List<Integer> twoRows = Arrays.asList(1, 1);
        result.add(twoRows);
        if (numRows == 2)
            return result;
        List<Integer> preRow = twoRows;
        for (int i = 3; i <= numRows; i++) {
            List<Integer> currentRow = new ArrayList<>(i);
            currentRow.add(1);
            for (int k = 1; k < i - 1; k++) {
                currentRow.add(preRow.get(k - 1) + preRow.get(k));
            }
            currentRow.add(1);
            preRow = currentRow;
            result.add(currentRow);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Pasika().generate(5));
    }
}
