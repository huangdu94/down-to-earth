package binary;

import java.nio.charset.StandardCharsets;

public class Homework {
    public static void main(String[] args) {
        byte[] bytes = utf8('中');
//		String ch=new String(bytes);
//		System.out.println(ch);
        char ch = decodeChar(bytes);
        System.out.println(ch);
        byte[] bytes2 = utf8("编码解码真好玩");
        String str = decodeString(bytes2);
        String str2 = new String(bytes2, StandardCharsets.UTF_8);
		System.out.println(str);
		System.out.println(str2);
    }

    public static byte[] utf8(char ch) {
        byte[] bytes = new byte[3];
        bytes[2] = (byte) (ch & 0x3f | 0x80);
        bytes[1] = (byte) ((ch >>> 6) & 0x3f | 0x80);
        bytes[0] = (byte) ((ch >>> 12) & 0xf | 0xe0);
        return bytes;
    }

    public static byte[] utf8(String str) {
        byte[] bytes = new byte[3 * str.length()];
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            bytes[2 + i * 3] = (byte) (chars[i] & 0x3f | 0x80);
            bytes[1 + i * 3] = (byte) ((chars[i] >>> 6) & 0x3f | 0x80);
            bytes[i * 3] = (byte) ((chars[i] >>> 12) & 0xf | 0xe0);
        }
        return bytes;
    }

    public static char decodeChar(byte[] bytes) {
        char ch = (char) (bytes[2] & 0x3f | (bytes[1] & 0x3f) << 6 | (bytes[0] & 0xf) << 12);
        return ch;
    }

    public static String decodeString(byte[] bytes) {
        char[] chars = new char[bytes.length / 3];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (bytes[2 + i * 3] & 0x3f | (bytes[1 + i * 3] & 0x3f) << 6 | (bytes[i * 3] & 0xf) << 12);
        }
        String str = String.valueOf(chars);
        return str;
    }
}
