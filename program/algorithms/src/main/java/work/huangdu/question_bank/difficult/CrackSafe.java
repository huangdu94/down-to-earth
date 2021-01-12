package work.huangdu.question_bank.difficult;

import java.util.HashSet;
import java.util.Set;

/**
 * 753. 破解保险箱
 * 有一个需要密码才能打开的保险箱。密码是 n 位数, 密码的每一位是 k 位序列 0, 1, ..., k-1 中的一个 。
 * 你可以随意输入密码，保险箱会自动记住最后 n 位输入，如果匹配，则能够打开保险箱。
 * 举个例子，假设密码是 "345"，你可以输入 "012345" 来打开它，只是你输入了 6 个字符.
 * 请返回一个能打开保险箱的最短字符串。
 * 示例1:
 * 输入: n = 1, k = 2
 * 输出: "01"
 * 说明: "10"也可以打开保险箱。
 * 示例2:
 * 输入: n = 2, k = 2
 * 输出: "00110" 0010
 * 说明: "01100", "10011", "11001" 也能打开保险箱。
 * 提示：
 * n 的范围是 [1, 4]。
 * k 的范围是 [1, 10]。
 * k^n 最大可能为 4096。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/8/27 22:25
 */
public class CrackSafe {
    private char[] res;
    private boolean[] visited;
    private int len;
    private int k;

    /*
    完全相同的数字组成的节点，出度和入度都为k-1，其它节点出度和入度都为k
     */
    public String crackSafe2(int n, int k) {
        int count = (int) Math.pow(k, n), i = 0;
        this.k = k;
        len = count + (n - 1);
        res = new char[len];
        visited = new boolean[count];
        while (i < n) {
            res[i++] = '0';
        }
        visited[0] = true;
        return helper(1, i);
    }

    private String helper(int start, int index) {
        if (index == len) {
            return new String(res);
        }
        int nodeIndex = 0, i = start;
        while (i < index) {
            nodeIndex = nodeIndex * k + (res[i++] - '0');
        }
        nodeIndex *= k;
        for (i = 0; i < k; i++) {
            if (!visited[nodeIndex + i]) {
                res[index] = (char) (i + '0');
                visited[nodeIndex + i] = true;
                String r = helper(start + 1, index + 1);
                if (r != null) return r;
                visited[nodeIndex + i] = false;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        CrackSafe crackSafe = new CrackSafe();
        int n = 2, k = 3;
        String res = crackSafe.crackSafe(n, k);
        System.out.println(res);
    }

    Set<String> seen;
    StringBuilder ans;

    public String crackSafe(int n, int k) {
        if (n == 1 && k == 1) return "0";
        seen = new HashSet<>();
        ans = new StringBuilder();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n - 1; ++i)
            sb.append("0");
        String start = sb.toString();

        dfs(start, k);
        ans.append(start);
        //ans.reverse();
        return new String(ans);
    }

    // 当一个节点的所有
    public void dfs(String node, int k) {
        for (int x = 0; x < k; ++x) {
            String nei = node + x;
            if (!seen.contains(nei)) {
                seen.add(nei);
                dfs(nei.substring(1), k);
                ans.append(x);
            }
        }
    }
}
