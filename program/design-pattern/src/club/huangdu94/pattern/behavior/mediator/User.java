package club.huangdu94.pattern.behavior.mediator;

/**
 * @author duhuang@iflytek.com
 * @version 2020/11/29 21:57
 */
public class User {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String name) {
        this.name = name;
    }

    public void sendMessage(String message) {
        ChatRoom.showMessage(this, message);
    }
}
