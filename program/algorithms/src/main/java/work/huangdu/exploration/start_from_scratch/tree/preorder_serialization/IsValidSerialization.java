package work.huangdu.exploration.start_from_scratch.tree.preorder_serialization;

import java.lang.management.BufferPoolMXBean;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 331. 验证二叉树的前序序列化
 * 序列化二叉树的一种方法是使用前序遍历。当我们遇到一个非空节点时，我们可以记录下这个节点的值。如果它是一个空节点，我们可以使用一个标记值记录，例如 #。
 * *      _9_
 * *     /   \
 * *    3     2
 * *   / \   / \
 * *  4   1  #  6
 * * / \ / \   / \
 * * # # # #   # #
 * 例如，上面的二叉树可以被序列化为字符串 "9,3,4,#,#,1,#,#,2,#,6,#,#"，其中 # 代表一个空节点。
 * 给定一串以逗号分隔的序列，验证它是否是正确的二叉树的前序序列化。编写一个在不重构树的条件下的可行算法。
 * 每个以逗号分隔的字符或为一个整数或为一个表示 null 指针的 '#' 。
 * 你可以认为输入格式总是有效的，例如它永远不会包含两个连续的逗号，比如 "1,,3" 。
 * 示例 1:
 * 输入: "9,3,4,#,#,1,#,#,2,#,6,#,#"
 * 输出: true
 * 示例 2:
 * 输入: "1,#"
 * 输出: false
 * 示例 3:
 * 输入: "9,#,#,1"
 * 输出: false
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/2/1
 */
public class IsValidSerialization {
    // 槽位slot概念
    public boolean isValidSerialization(String preorder) {
        if ("#".equals(preorder)) return true;
        if (preorder.startsWith("#") || !preorder.endsWith("#,#")) return false;
        int n = preorder.length(), slots = 1;
        for (int i = 1; i < n - 1; i++) {
            if (preorder.charAt(i) == ',') {
                if (--slots < 0) {
                    return false;
                }
                if (preorder.charAt(i - 1) != '#') {
                    slots += 2;
                }
            }
        }
        return slots == 1;
    }

    public boolean isValidSerialization2(String preorder) {
        // if (preorder == null || preorder.length() == 0) return false;
        if ("#".equals(preorder)) return true;
        if (preorder.startsWith("#") || !preorder.endsWith("#,#")) return false;
        String[] nodes = preorder.split(",");
        int n = nodes.length, i = 0;
        Deque<Boolean> stack = new ArrayDeque<>();
        for (; i < n; i++) {
            String node = nodes[i];
            if (!"#".equals(node)) {
                stack.push(Boolean.FALSE);
            } else {
                boolean flag = Boolean.TRUE;
                while (!stack.isEmpty() && flag) {
                    flag = stack.pop();
                }
                if (stack.isEmpty() && flag) {
                    break;
                }
                stack.push(Boolean.TRUE);
            }
        }
        return i == n - 1;
    }

    public static void main(String[] args) {
        String preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#";
        IsValidSerialization ivs = new IsValidSerialization();
        System.out.println(ivs.isValidSerialization(preorder));
    }
}
