package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Pattern
 * Matcher
 */
public class PatternDemo {
    public static void main(String[] args) {
        //邮箱匹配正则表达式
        //String regex = "\\w+@(qq.com|gmail.com|mail.ustc.edu.cn|iflytek.com)";
        //String str = "445951954@qq.com";
        String regex = "([a-z]+)(\\d+)";
        String str = "a123b213c234d123d";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        //System.out.println(matcher.matches());
        while (matcher.find()) {
            //当regex分成几部分时 0表示匹配全部 1表示匹配第一部分 2表示匹配第二部分
            System.out.println(matcher.group(0));
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
        }
    }
}
