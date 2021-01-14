package work.huangdu.exploration.start_from_scratch.string.character_statistics;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 535. TinyURL 的加密与解密
 * TinyURL是一种URL简化服务， 比如：当你输入一个URL https://leetcode.com/problems/design-tinyurl 时，它将返回一个简化的URL http://tinyurl.com/4e9iAk.
 * 要求：设计一个 TinyURL 的加密 encode 和解密 decode 的方法。你的加密和解密算法如何设计和运作是没有限制的，你只需要保证一个URL可以被加密成一个TinyURL，并且这个TinyURL可以用解密方法恢复成原本的URL。
 * Your Codec object will be instantiated and called as such:
 * Codec codec = new Codec();
 * codec.decode(codec.encode(url));
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/9/25 11:00
 */
public class Codec {
    private static final String prefix = "http://tinyurl.com/";
    private static final int ENCODE_LEN = 6;
    private static final char[] base62 = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private final Random random = new Random();
    private final Map<String, String> map = new HashMap<>();

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        String key;
        do {
            StringBuilder sb = new StringBuilder(ENCODE_LEN);
            for (int i = 0; i < ENCODE_LEN; i++) {
                sb.append(base62[random.nextInt(62)]);
            }
            key = sb.toString();
        } while (map.containsKey(key));
        map.put(key, longUrl);
        return prefix.concat(key);
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return map.get(shortUrl.substring(prefix.length()));
    }

    public static void main(String[] args) {
        Codec codec = new Codec();
        System.out.println(codec.decode(codec.encode("https://leetcode.com/problems/design-tinyurl")));
    }
}
