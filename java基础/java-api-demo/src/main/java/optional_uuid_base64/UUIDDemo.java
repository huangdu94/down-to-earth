package optional_uuid_base64;

import java.util.UUID;

/**
 * 可以根据时间戳、硬件编号自动生成一个无重复的内容，该内容可以作为唯一编号使用
 *
 * @author duhuang@iflytek.com
 * @version 2019/11/11 16:58
 */
public class UUIDDemo {
    public static void main(String[] args) {
        UUID uuid = new UUID(16,16);
        System.out.println(uuid);
        uuid = UUID.randomUUID();
        System.out.println(uuid);
    }
}
