package file_stream.randomaccessfile;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 读取指定格式emp.dat里的员工信息，并显示在控制台上
 *
 * @author Bean
 */
public class ReadEmp {
    public static void main(String[] args) throws IOException {
        String path = ReadEmp.class.getResource("/emp.dat").getPath();
        RandomAccessFile raf = new RandomAccessFile(path, "r");
        int empNumber = (int) (raf.length() / Emp.DataLen.ALL_LEN);//计算文件中员工数量
        Emp[] emps = new Emp[empNumber];//创建Emp型数组
        for (int i = 0; i < empNumber; i++) {//为每一个Emp元素获取信息
            emps[i] = new Emp();
            emps[i].formatRead(raf);
        }
        for (int i = 0; i < empNumber; i++) {//将每一个Emp元素的信息输出
            String information = emps[i].toString();
            System.out.println(information);
        }
    }
}