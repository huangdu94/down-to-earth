package locale_resourcebundle;

import java.util.Locale;

/**
 * Locale对象代表具体的地理，政治或文化地区
 *
 * @author duhuang@iflytek.com
 * @version 2019/11/11 15:56
 */
public class LocaleDemo {
    public static void main(String[] args) {
        Locale locale = Locale.CHINA;
        System.out.println(locale);
        System.out.println(locale.getCountry());
        System.out.println(locale.getDisplayCountry());
        System.out.println(locale.getDisplayLanguage());
        System.out.println(locale.getDisplayName());
        System.out.println(locale.getDisplayScript());
        System.out.println(locale.getDisplayVariant());
        for (Character c : locale.getExtensionKeys()) {
            System.out.println(c);
        }

    }
}
