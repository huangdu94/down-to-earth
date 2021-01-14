package work.huangdu.exploration.primary_algorithms.string;

/**
 * 验证回文字符串
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * 示例 1:
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 示例 2:
 * 输入: "race a car"
 * 输出: false
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/7/26 17:06
 */
public class IsPalindrome {
    public boolean isPalindrome(String s) {
        if (s.length() <= 1)
            return true;
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            while (i < j && isNotValidChar(s.charAt(i)))
                i++;
            while (i < j && isNotValidChar(s.charAt(j)))
                j--;
            if (i < j) {
                if (toUpperCase(s.charAt(i)) != toUpperCase(s.charAt(j)))
                    return false;
                i++;
                j--;
            }
        }
        return true;
    }

    /**
     * 判断是否是有效地字符，数字和字母
     *
     * @param c 输入字符
     * @return 不是有效字符 true
     */
    private boolean isNotValidChar(char c) {
        return (c < '0' || c > '9') && (c < 'a' || c > 'z') && (c < 'A' || c > 'Z');
    }

    /**
     * 小写字母转大写字母
     */
    private int toUpperCase(char c) {
        if (c >= 'a' && c <= 'z')
            return c - 32;
        return c;
    }

    public boolean isPalindrome2(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            while (i < j && !isLetterOrNumber(s.charAt(i))) {
                i++;
            }
            while (i < j && !isLetterOrNumber(s.charAt(j))) {
                j--;
            }
            if (i < j) {
                if (toLowerCase(s.charAt(i)) != toLowerCase(s.charAt(j))) {
                    return false;
                }
                i++;
                j--;
            }
        }
        return true;
    }

    private char toLowerCase(char c) {
        if (c <= 'Z' && c >= 'A') {
            c += 32;
        }
        return c;
    }

    private boolean isLetterOrNumber(char c) {
        return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c >= '0' && c <= '9';
    }

    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        IsPalindrome isPalindrome = new IsPalindrome();
        System.out.println(isPalindrome.isPalindrome2(s));
    }
}
