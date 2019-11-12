package file_stream.randomaccessfile;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

/**
 * 员工类，包含dat文件中，一个员工的所有数据的字节信息。
 * 读出员工信息的方法。
 * 重写了toString方法用于返回员工信息字符串。
 *
 * @author Bean
 */
class Emp {
    private String name;
    private int age;
    private String gender;
    private int salary;
    private String hiredate;

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

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getHiredate() {
        return hiredate;
    }

    public void setHiredate(String hiredate) {
        this.hiredate = hiredate;
    }

    /**
     * 员工数据字节大小常量
     */
    public interface DataLen {
        int NAME_LEN = 32;
        int AGE_LEN = 4;
        int GENDER_LEN = 10;
        int SALARY_LEN = 4;
        int HIREDATE_LEN = 30;
        int ALL_LEN = NAME_LEN + AGE_LEN + GENDER_LEN + SALARY_LEN + HIREDATE_LEN;
    }

    /**
     * 读取一个员工信息
     *
     * @param raf 所读文件
     * @throws IOException
     */
    public void formatRead(RandomAccessFile raf) throws IOException {
        byte[] nameData = new byte[DataLen.NAME_LEN];
        byte[] genderData = new byte[DataLen.GENDER_LEN];
        byte[] hiredateData = new byte[DataLen.HIREDATE_LEN];
        raf.read(nameData);
        setName(new String(nameData, StandardCharsets.UTF_8).trim());
        setAge(raf.readInt());
        raf.read(genderData);
        setGender(new String(genderData, StandardCharsets.UTF_8).trim());
        setSalary(raf.readInt());
        raf.read(hiredateData);
        setHiredate(new String(hiredateData, StandardCharsets.UTF_8).trim());
    }

    /**
     * 返回员工信息字符串
     */
    @Override
    public String toString() {
        return "Emp{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", salary=" + salary +
                ", hiredate='" + hiredate + '\'' +
                '}';
    }
}