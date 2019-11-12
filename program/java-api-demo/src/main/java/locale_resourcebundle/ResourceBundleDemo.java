package locale_resourcebundle;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * ResourceBundle
 * 资源束包含区域特定的对象
 * 当您的程序需要特定于区域设置的资源时，例如String，您的程序可以从适合当前用户的区域设置的资源包中加载它
 * 需要与资源文件配合使用
 *
 * @author duhuang@iflytek.com
 * @version 2019/11/11 16:11
 */
public class ResourceBundleDemo {
    public static void main(String[] args) throws UnsupportedEncodingException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("MyResources",Locale.ENGLISH);
        System.out.println(new String(resourceBundle.getString("name").getBytes("ISO-8859-1"),"UTF-8"));
    }
}
