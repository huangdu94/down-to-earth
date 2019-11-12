package collection.sort;

/**
 * 自定义类 实现Comparable接口
 *
 * @author duhuang@iflytek.com
 * @version 2019/11/11 16:43
 */
public class User implements Comparable<User> {
    private String username;
    private int age;

    public User() {
    }

    public User(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';
    }

    /**
     * 可用于Arrays.sort()排序
     *
     * @param o 比较同类型对象
     * @return 0 相等 正数 大于 负数 小于
     */
    @Override
    public int compareTo(User o) {
        if (this.age == o.age) {
            return this.username.compareTo(o.username);
        }
        return this.age - o.age;
    }
}
