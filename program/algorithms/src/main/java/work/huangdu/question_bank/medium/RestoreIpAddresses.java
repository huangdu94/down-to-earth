package work.huangdu.question_bank.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 93. 复原IP地址
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * 有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。
 * 示例:
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 *
 * @author duhuang@iflytek.com
 * @version 2020/8/9 0:43
 */
public class RestoreIpAddresses {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        //if (s == null) return res;
        int len = s.length();
        if (len > 12 || len < 4) return res;
        StringBuilder sb;
        for (int i = 1; i <= 3; i++) {
            if (i > 1 && s.charAt(0) == '0' || i == 3 && s.substring(0, i).compareTo("255") > 0) break;
            for (int j = i + 1; j <= i + 3; j++) {
                if (j > len) break;
                if (j > i + 1 && s.charAt(i) == '0' || j == i + 3 && s.substring(i, i + 3).compareTo("255") > 0) break;
                for (int k = j + 1; k <= j + 3; k++) {
                    if (k > len) break;
                    if (k > j + 1 && s.charAt(j) == '0' || k == j + 3 && s.substring(j, j + 3).compareTo("255") > 0)
                        break;
                    for (int l = k + 1; l <= k + 3; l++) {
                        if (l > len) break;
                        if (l < len) continue;
                        if (l > k + 1 && s.charAt(k) == '0' || l == k + 3 && s.substring(k, k + 3).compareTo("255") > 0)
                            break;
                        sb = new StringBuilder(s);
                        sb.insert(i, '.');
                        sb.insert(j + 1, '.');
                        sb.insert(k + 2, '.');
                        res.add(sb.toString());
                    }
                }
            }
        }
        return res;
    }
}
