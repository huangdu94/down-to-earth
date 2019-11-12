package xml;

/**
 * 用来表示一个员工信息
 * VO value object值对象
 * 所谓值对象，就是当前类的每个实例用来保存一组数据使用.
 *
 * @author Bean
 */
public class Emp {
    private int id;
    private String name;
    private int age;
    private String gender;
    private double salary;

    public Emp() {
    }

    public Emp(int id, String name, int age, String gender, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String toString() {
        return id + "," + name + "," + age + "," + gender + "," + salary;
    }

}
