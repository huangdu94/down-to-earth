package file_stream.randomaccessfile;
import java.io.IOException;
import java.io.RandomAccessFile;
/**
 * 读取指定格式emp.dat里的员工信息，并显示在控制台上
 * @author Bean
 *
 */
public class ReadEmp {
	public static void main(String[] args) throws IOException {
		RandomAccessFile raf=new RandomAccessFile("emp.dat","r");
		int empNumber=(int)(raf.length()/Emp.ALLLEN);//计算文件中员工数量
		Emp[] emps=new Emp[empNumber];//创建Emp型数组
		for(int i=0;i<empNumber;i++) {//为每一个Emp元素获取信息
			emps[i]=new Emp();
			emps[i].formatRead(raf);
		}
		for(int i=0;i<empNumber;i++) {//将每一个Emp元素的信息输出
			String information=emps[i].toString();
			System.out.println(information);
		}
	}
}
/**
 * 员工类，包含dat文件中，一个员工的所有数据的字节信息。
 * 读出员工信息的方法。
 * 重写了toString方法用于返回员工信息字符串。
 * @author Bean
 *
 */
class Emp{
	/**
	 *员工数据字节大小常量 
	 */
	public static final int NAME_LEN=32;
	public static final int AGE_LEN=4;
	public static final int GENDER_LEN=10;
	public static final int SALARY_LEN=4;
	public static final int HIREDATE_LEN=30;
	public static final int ALLLEN=(NAME_LEN+AGE_LEN+GENDER_LEN+SALARY_LEN+HIREDATE_LEN);
	
	private String name;
	private int age;
	private String gender;
	private int salary;
	private String hiredate;
	/**
	 * 读取一个员工信息
	 * @param raf 所读文件
	 * @throws IOException
	 */
	public void formatRead(RandomAccessFile raf) throws IOException {
		byte[] nameData=new byte[NAME_LEN];
		byte[] genderData=new byte[GENDER_LEN];
		byte[] hiredateData=new byte[HIREDATE_LEN];
		raf.read(nameData);
		this.name=new String(nameData,"UTF-8").trim();
		this.age=raf.readInt();
		raf.read(genderData);
		this.gender=new String(genderData,"UTF-8").trim();
		this.salary=raf.readInt();
		raf.read(hiredateData);
		this.hiredate=new String(hiredateData,"UTF-8").trim();
	}
	/**
	 * 返回员工信息字符串
	 */
	public String toString() {
		return "name:"+name+",age:"+age+",gender:"+gender+",salary:"+salary+",hiredate:"+hiredate;
	}
}
