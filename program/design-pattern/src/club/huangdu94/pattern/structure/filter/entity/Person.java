package club.huangdu94.pattern.structure.filter.entity;

/**
 * @author duhuang@iflytek.com
 * @version 2020/11/29 16:02
 */
public class Person {
    private final String name;
    private final String gender;
    private final String maritalStatus;

    public Person(String name, String gender, String maritalStatus) {
        this.name = name;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    @Override
    public String toString() {
        return "Person : [ Name : " + name
            + ", Gender : " + gender
            + ", Marital Status : " + maritalStatus
            + " ]";
    }
}
