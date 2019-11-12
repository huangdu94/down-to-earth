package string;

public class StringUtil {
    /**
     * 获取给定的路径中的域名
     * 例如：http://www.oracle.com
     * 方法会返回其中的域名部分：oracle
     */
    public static String getHostName(String url) {
        //查第一个点之后第一个字符的位置
        int indexa = url.indexOf(".") + 1;
        //第二个点的位置
        int indexb = url.indexOf(".", indexa);
        String str = url.substring(indexa, indexb);
        return str;
    }

    public static void main(String[] args) {
        String webside = "http://www.oracle.com";
        String result = getHostName(webside);
        System.out.println(result);
    }
}
