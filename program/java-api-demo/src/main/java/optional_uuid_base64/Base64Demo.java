package optional_uuid_base64;

import java.util.Base64;

/**
 * Base64加密
 *
 * @author duhuang@iflytek.com
 * @version 2019/11/11 17:05
 */
public class Base64Demo {
    public static void main(String[] args) {
        //加密
        Base64.Encoder encoder = Base64.getEncoder();
        //解密
        Base64.Decoder decoder = Base64.getDecoder();
        String message = "hello world!";
        byte[] message_encoder = encoder.encode(message.getBytes());
        System.out.println(new String(message_encoder));
        byte[] message_decoder = decoder.decode(message_encoder);
        System.out.println(new String(message_decoder));
    }
}
