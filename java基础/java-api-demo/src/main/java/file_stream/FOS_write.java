package file_stream;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * java io java标准IO
 * IO流根据方向分为：
 * 输入流：外界到我们编写的程序中的方向，所有输入流是用于从外界获取数据的流，读操作.
 * 输出流：将数据从我们编写的程序发送到外界的方向，写操作.
 * 
 * java将流分为两类：
 * 节点流：节点流又称为低级流，是实际连接程序与数据源的"管道"，负责传输数据.读写一定是建立在节点流上进行的.
 * 处理流：处理流又称为高级流，用于处理其他流，不能独立存在（没有意义），使用高级流处理其他流的目的是通过
 * 高级流带来的功能简化我们对数据读写时的某些复杂处理.
 * 
 * 文件流
 * FileInputStream,FileOutputStream它们是一对低级流，作用是对文件读写数据.
 * 从功能上讲它们与RandomAccessFile一致， 但是底层实现不同，RandomAccessFile是专门设计用来读写文件数据的，基于指针操作.
 * 而文件流符合JAVA标准IO操作.
 * @author Bean
 *
 */
public class FOS_write {
	public static void main(String[] args) throws IOException {
		/*
		 * FileOutputStream(String fileName)
		 * FileOutputStream(File file)
		 * 上面两种构造方法创建的文件输出流都是覆盖写操作，
		 * 即：若指定的文件已经存在会将该文件所有内容清除，
		 * 然后通过该流写出的内容会作为这个文件的新内容.
		 * 
		 * FileOutputStream(String fileName，boolean append)
		 * FileOutputStream(File file，boolean append)
		 * 追加写模式，即：若指定的文件己经存在会接着在文件末尾写入新数据.
		 */
		FileOutputStream fos=new FileOutputStream("fos.txt");
		String str="你举手，你抬头，你说选我选我。我将牌换颜色，变出你的选择，将自由的女神变不见不稀奇，101变不见才惊喜。手摊开，帽子里，总能空手出牌，不管切多少牌，总能切的回来，手上海报却不拿汉堡，反而拿出牛仔帽，你永远都猜不到。读你读你读，心想啥事，用古典迫牌方式，我手法精致埃厄姆值，雷一百分的姿势，谁说恋爱别找魔法师我不需要解释，所以不用麻烦了不用麻烦了不用麻烦了";
		fos.write(str.getBytes("utf-8"));
		fos.close();
		System.out.println("写出完毕!");
	}
}
