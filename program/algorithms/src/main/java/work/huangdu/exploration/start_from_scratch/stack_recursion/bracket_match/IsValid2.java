package work.huangdu.exploration.start_from_scratch.stack_recursion.bracket_match;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 591. 标签验证器
 * 给定一个表示代码片段的字符串，你需要实现一个验证器来解析这段代码，并返回它是否合法。合法的代码片段需要遵守以下的所有规则：
 * 代码必须被合法的闭合标签包围。否则，代码是无效的。
 * 闭合标签（不一定合法）要严格符合格式：<TAG_NAME>TAG_CONTENT</TAG_NAME>。其中，<TAG_NAME>是起始标签，</TAG_NAME>是结束标签。起始和结束标签中的 TAG_NAME 应当相同。当且仅当 TAG_NAME 和 TAG_CONTENT 都是合法的，闭合标签才是合法的。
 * 合法的 TAG_NAME 仅含有大写字母，长度在范围 [1,9] 之间。否则，该 TAG_NAME 是不合法的。
 * 合法的 TAG_CONTENT 可以包含其他合法的闭合标签，cdata （请参考规则7）和任意字符（注意参考规则1）除了不匹配的<、不匹配的起始和结束标签、不匹配的或带有不合法 TAG_NAME 的闭合标签。否则，TAG_CONTENT 是不合法的。
 * 一个起始标签，如果没有具有相同 TAG_NAME 的结束标签与之匹配，是不合法的。反之亦然。不过，你也需要考虑标签嵌套的问题。
 * 一个<，如果你找不到一个后续的>与之匹配，是不合法的。并且当你找到一个<或</时，所有直到下一个>的前的字符，都应当被解析为 TAG_NAME（不一定合法）。
 * cdata 有如下格式：<![CDATA[CDATA_CONTENT]]>。CDATA_CONTENT 的范围被定义成 <![CDATA[ 和后续的第一个 ]]>之间的字符。
 * CDATA_CONTENT 可以包含任意字符。cdata 的功能是阻止验证器解析CDATA_CONTENT，所以即使其中有一些字符可以被解析为标签（无论合法还是不合法），也应该将它们视为常规字符。
 * 合法代码的例子:
 * 输入: "<DIV>This is the first line <![CDATA[<div>]]></DIV>"
 * 输出: True
 * 解释:
 * 代码被包含在了闭合的标签内： <DIV> 和 </DIV> 。
 * TAG_NAME 是合法的，TAG_CONTENT 包含了一些字符和 cdata 。
 * 即使 CDATA_CONTENT 含有不匹配的起始标签和不合法的 TAG_NAME，它应该被视为普通的文本，而不是标签。
 * 所以 TAG_CONTENT 是合法的，因此代码是合法的。最终返回True。
 * 输入: "<DIV>>>  ![cdata[]] <![CDATA[<div>]>]]>]]>>]</DIV>"
 * 输出: True
 * 解释:
 * 我们首先将代码分割为： start_tag|tag_content|end_tag 。
 * start_tag -> "<DIV>"
 * end_tag -> "</DIV>"
 * tag_content 也可被分割为： text1|cdata|text2 。
 * text1 -> ">>  ![cdata[]] "
 * cdata -> "<![CDATA[<div>]>]]>" ，其中 CDATA_CONTENT 为 "<div>]>"
 * text2 -> "]]>>]"
 * start_tag 不是 "<DIV>>>" 的原因参照规则 6 。
 * cdata 不是 "<![CDATA[<div>]>]]>]]>" 的原因参照规则 7 。
 * 不合法代码的例子:
 * 输入: "<A>  <B> </A>   </B>"
 * 输出: False
 * 解释: 不合法。如果 "<A>" 是闭合的，那么 "<B>" 一定是不匹配的，反之亦然。
 * 输入: "<DIV>  div tag is not closed  <DIV>"
 * 输出: False
 * 输入: "<DIV>  unmatched <  </DIV>"
 * 输出: False
 * 输入: "<DIV> closed tags with invalid tag name  <b>123</b> </DIV>"
 * 输出: False
 * 输入: "<DIV> unmatched tags with invalid tag name  </1234567890> and <CDATA[[]]>  </DIV>"
 * 输出: False
 * 输入: "<DIV>  unmatched start tag <B>  and unmatched end tag </C>  </DIV>"
 * 输出: False
 * 注意:
 * 为简明起见，你可以假设输入的代码（包括提到的任意字符）只包含数字, 字母, '<','>','/','!','[',']'和' '。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/10/26 10:14
 */
public class IsValid2 {
    public boolean isValid(String code) {
        // code最短合法长度为7，例"<A></A>"，且code必须以<开头 >结尾
        if (code.length() < 7 || code.charAt(0) != '<'||code.charAt(code.length() - 1) != '>') return false;
        Deque<String> stack = new ArrayDeque<>();
        int n = code.length();
        // 读取第一个标签后，读取接下来的内容
        return readTagContent(code, n, readTagName(code, n, 0, stack), stack);
    }

    /**
     * 读取一个标签，如果读取失败返回-1;
     *
     * @param code  代码
     * @param n     代码长度
     * @param start 当前读取起始位置
     * @param stack 标签栈
     * @return 是否读取成功：
     * 读取成功返回下一个需要读取的下标
     * 读取失败返回-1
     */
    private int readTagName(String code, int n, int start, Deque<String> stack) {
        // 一个标签最短长度为3，所以start <= n-3
        if (start > n - 3) return -1;
        // left,right为标签字符串的左边界和右边界，含头不含尾
        int left = start + 1, right = left;
        for (int i = start + 1; i < n; i++) {
            char c = code.charAt(i);
            if (c <= 'Z' && c >= 'A') {
                // tag name长度最大9
                if (right - left >= 9) {
                    return -1;
                }
                right++;
            } else if (c == '>') {
                // tag name长度必须大于0
                if (left == right) {
                    return -1;
                }
                // 如果当前left等于start+1，说明第一个字符不是/，为开始标签
                if (left == start + 1) {
                    stack.push(code.substring(left, right));
                } else if (stack.isEmpty() || !stack.pop().equals(code.substring(left, right))) {
                    return -1;
                }
                return i + 1;
            } else if (c == '/' && i == start + 1) {
                left++;
                right++;
            } else {
                return -1;
            }
        }
        return -1;
    }

    /**
     * 读取一个标签内容;
     *
     * @param code  代码
     * @param n     代码长度
     * @param start 当前读取起始位置
     * @param stack 标签栈
     * @return 是否读取成功
     */
    private boolean readTagContent(String code, int n, int start, Deque<String> stack) {
        // 如果读取tagName失败则返回false
        if (start == -1) return false;
        // 如果已经读到末尾，但是stack不为空，表示有标签没有被正确匹配，返回false
        if (start == n) return stack.isEmpty();
        // 如果还没读到末尾，进入此解析的时候，stack却为空，必不合法
        if (stack.isEmpty()) return false;
        int i = start;
        // 略过一切不是<的字符
        while (i < n && code.charAt(i) != '<') {
            i++;
        }
        // 从<后的字符开始判断
        if (i + 1 < n) {
            // 若<后的字符不为!则交给readTagName方法判断
            if (code.charAt(i + 1) != '!') {
                return readTagContent(code, n, readTagName(code, n, i, stack), stack);
            } else {
                // <![CDATA[    ]]>
                if (!code.startsWith("[CDATA[", i + 2)) {
                    return false;
                }
                i += 7;
                while (i < n - 2 && !code.startsWith("]]>", i)) {
                    i++;
                }
                // 略过CDATA数据，继续递归读接下来的数据
                if (i >= n - 2) {
                    return false;
                }
                return readTagContent(code, n, i + 3, stack);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        IsValid2 valid = new IsValid2();
        System.out.println(valid.isValid("<DIV>This is the first line <![CDATA[<div>]]></DIV>"));
    }
}
