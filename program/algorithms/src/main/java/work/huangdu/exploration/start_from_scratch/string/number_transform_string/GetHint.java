package work.huangdu.exploration.start_from_scratch.string.number_transform_string;

/**
 * 299. 猜数字游戏
 * 你在和朋友一起玩 猜数字（Bulls and Cows）游戏，该游戏规则如下：
 * 你写出一个秘密数字，并请朋友猜这个数字是多少。
 * 朋友每猜测一次，你就会给他一个提示，告诉他的猜测数字中有多少位属于数字和确切位置都猜对了（称为“Bulls”, 公牛），有多少位属于数字猜对了但是位置不对（称为“Cows”, 奶牛）。
 * 朋友根据提示继续猜，直到猜出秘密数字。
 * 请写出一个根据秘密数字和朋友的猜测数返回提示的函数，返回字符串的格式为 xAyB ，x 和 y 都是数字，A 表示公牛，用 B 表示奶牛。
 * xA 表示有 x 位数字出现在秘密数字中，且位置都与秘密数字一致。
 * yB 表示有 y 位数字出现在秘密数字中，但位置与秘密数字不一致。
 * 请注意秘密数字和朋友的猜测数都可能含有重复数字，每位数字只能统计一次。
 * 示例 1:
 * 输入: secret = "1807", guess = "7810"
 * 输出: "1A3B"
 * 解释: 1 公牛和 3 奶牛。公牛是 8，奶牛是 0, 1 和 7。
 * 示例 2:
 * 输入: secret = "1123", guess = "0111"
 * 输出: "1A1B"
 * 解释: 朋友猜测数中的第一个 1 是公牛，第二个或第三个 1 可被视为奶牛。
 * 说明: 你可以假设秘密数字和朋友的猜测数都只包含数字，并且它们的长度永远相等。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/8/27 19:05
 */
public class GetHint {
    public String getHint(String secret, String guess) {
        int len = secret.length(), bulls = 0, cows = 0;
        int[] count_s = new int[10], count_g = new int[10];
        for (int i = 0; i < len; i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                bulls++;
            } else {
                count_s[secret.charAt(i) - '0']++;
                count_g[guess.charAt(i) - '0']++;
            }
        }
        for (int i = 0; i < 10; i++) {
            cows += Math.min(count_s[i], count_g[i]);
        }
        return new StringBuilder().append(bulls).append('A').append(cows).append('B').toString();
    }


    public String getHint2(String secret, String guess) {
        int a = 0, b = 0;
        int[] counts = new int[10];
        for (int i = 0, len = secret.length(); i < len; i++) {
            int s = secret.charAt(i) - '0';
            int g = guess.charAt(i) - '0';
            if (s == g) {
                a++;
            }
            if (counts[s]++ < 0) b++;
            if (counts[g]-- > 0) b++;
        }
        return a + "A" + (b - a) + "B";
    }
}
