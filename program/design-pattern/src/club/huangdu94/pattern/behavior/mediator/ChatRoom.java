package club.huangdu94.pattern.behavior.mediator;

import java.util.Date;

/**
 * 中介者
 * 聊天室
 * 如果需要修改聊天的格式，直接在中介者类统一修改即可
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/29 21:55
 */
public class ChatRoom {
    public static void showMessage(User user, String message) {
        System.out.println(new Date().toString()
            + " [" + user.getName() + "] : " + message);
    }
}
