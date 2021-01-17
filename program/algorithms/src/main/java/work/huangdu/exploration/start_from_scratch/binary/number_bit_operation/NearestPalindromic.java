package work.huangdu.exploration.start_from_scratch.binary.number_bit_operation;

/**
 * 564. 寻找最近的回文数
 * 给定一个整数 n ，你需要找到与它最近的回文数（不包括自身）。
 * “最近的”定义为两个整数差的绝对值最小。
 * 示例 1:
 * 输入: "123"
 * 输出: "121"
 * 注意:
 * n 是由字符串表示的正整数，其长度不超过18。
 * 如果有多个结果，返回最小的那个。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/10/5 13:44
 */
public class NearestPalindromic {
    public String nearestPalindromic(String n) {
        char[] chars = n.toCharArray();
        for (int i = 0, j = chars.length - 1; i < j; i++, j--) {
            chars[j] = chars[i];
        }
        String cur = String.valueOf(chars);
        // 核心
        String pre = nearest(cur, false);
        String next = nearest(cur, true);
        long numL = Long.parseLong(n);
        long curL = Long.parseLong(cur);
        long preL = Long.parseLong(pre);
        long nextL = Long.parseLong(next);
        long d1 = Math.abs(numL - preL);
        long d2 = Math.abs(numL - curL);
        long d3 = Math.abs(numL - nextL);
        if (numL == curL) {
            return d1 <= d3 ? pre : next;
        } else if (numL < curL) {
            return d2 < d1 ? cur : pre;
        } else {
            return d2 <= d3 ? cur : next;
        }
    }

    String nearest(String cur, boolean isRight) {
        int right = cur.length() >> 1;
        int left = cur.length() - right;
        long l = Long.parseLong(cur.substring(0, left));
        // 如果是求左边l--，求右边l++
        if (!isRight) l--;
        else l++;
        // 如果左边减完是0，则看看右边有没有，如果右边有则说明原来的数为1..，结果应该为9
        // 如果右边没有则说明原来的数就为1，结果为0
        if (l == 0) return right == 0 ? "0" : "9";
        StringBuilder ll = new StringBuilder(String.valueOf(l));
        StringBuilder rr = new StringBuilder(ll).reverse();
        // 当且仅当l减1后降位了，并且cur.length()原本为偶数，right才大于ll的长度
        if (right > ll.length()) rr.append(9);
        return ll.append(rr.substring(rr.length() - right)).toString();
    }

    public static void main(String[] args) {
        NearestPalindromic mic = new NearestPalindromic();
        String n = "11";
        System.out.println(mic.nearestPalindromic(n));
    }
}
