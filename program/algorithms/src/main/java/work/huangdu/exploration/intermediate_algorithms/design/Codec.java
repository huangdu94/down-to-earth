package work.huangdu.exploration.intermediate_algorithms.design;

import work.huangdu.data_structure.TreeNode;

import java.util.*;

/**
 * 二叉树的序列化与反序列化
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * 示例:
 * 你可以将以下二叉树：
 * *  1
 * * / \
 * 2   3
 * *  / \
 * * 4   5
 * 序列化为 "[1,2,3,null,null,4,5]"
 * 提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 * 说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/7/20 22:53
 */
public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        /*List<Integer> treeList = new ArrayList<Integer>() {
            @Override
            public String toString() {
                Iterator<Integer> it = iterator();
                if (!it.hasNext())
                    return "[]";
                StringBuilder sb = new StringBuilder();
                sb.append('[');
                for (; ; ) {
                    Integer e = it.next();
                    sb.append(e);
                    if (!it.hasNext())
                        return sb.append(']').toString();
                    sb.append(',');
                }
            }
        };*/
        List<Integer> treeList = new ArrayList<>();
        if (root != null) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode cur = queue.poll();
                    if (cur != null) {
                        treeList.add(cur.val);
                        queue.offer(cur.left);
                        queue.offer(cur.right);
                    } else {
                        treeList.add(null);
                    }
                }
            }
            // 遍历清除末尾的null
            for (int i = treeList.size() - 1; ; i--) {
                if (treeList.get(i) != null)
                    break;
                treeList.remove(i);
            }
        }
        return treeList.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        // 本方法不进行验证，如果输入数据有问题则会报错
        data = data.substring(1, data.length() - 1);
        String[] arr = data.split(", ");
        if ("".equals(arr[0]))
            return null;
        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int index = 1;
        while (index < arr.length) {
            int size = queue.size();
            for (int i = 0; i < size && index < arr.length; i++) {
                TreeNode cur = queue.remove();
                String nodeStr = arr[index++];
                if (!"null".equals(nodeStr)) {
                    TreeNode left = new TreeNode(Integer.parseInt(nodeStr));
                    cur.left = left;
                    queue.offer(left);
                }
                if (index < arr.length) {
                    nodeStr = arr[index++];
                    if (!"null".equals(nodeStr)) {
                        TreeNode right = new TreeNode(Integer.parseInt(nodeStr));
                        queue.offer(right);
                        cur.right = right;
                    }
                }
            }
        }
        return root;
    }

    public static void main(String[] args) {
        Codec codec = new Codec();
        String treeStr = "[]";
        TreeNode root = codec.deserialize(treeStr);
        System.out.println(codec.serialize(root));
    }
}
// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

class Codec2 {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root != null) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode cur = queue.poll();
                    if (cur != null) {
                        sb.append(cur.val).append(",");
                        queue.offer(cur.left);
                        queue.offer(cur.right);
                    } else {
                        sb.append("null,");
                    }
                }
            }
            // 删除最后一个逗号
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if ("".equals(data))
            return null;
        String[] arr = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int index = 1;
        while (index < arr.length) {
            int size = queue.size();
            for (int i = 0; i < size && index < arr.length; i++) {
                TreeNode cur = queue.remove();
                String nodeStr = arr[index++];
                if (!"null".equals(nodeStr)) {
                    TreeNode left = new TreeNode(Integer.parseInt(nodeStr));
                    cur.left = left;
                    queue.offer(left);
                }
                if (index < arr.length) {
                    nodeStr = arr[index++];
                    if (!"null".equals(nodeStr)) {
                        TreeNode right = new TreeNode(Integer.parseInt(nodeStr));
                        queue.offer(right);
                        cur.right = right;
                    }
                }
            }
        }
        return root;
    }
}