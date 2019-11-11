package xml;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;


/**
 * 使用DOM生成XML文档
 * @author Bean
 */

public class writeXmlDemo {
	public static void main(String[] args) {
		List<Emp> list=new ArrayList<Emp>();
		list.add(new Emp(1,"盲僧",45,"男",10000));
		list.add(new Emp(2,"风女",22,"女",12000));
		list.add(new Emp(3,"大树",100,"男",3500));
		list.add(new Emp(4,"狐狸",18,"女",90000));
		list.add(new Emp(5,"兰博",30,"男",15000));

		/*
		 * 使用DOM生成一个XML文档的大致步骤：
		 * 1：创建一个Document实例，表示一个空白文档
		 * 2：向Document中添加根元素
		 * 3：按照XML文档结构要求逐级添加子元素和数据以形成该结构
		 * 4：创建XMLWriter
		 * 5：使用XMLWriter将Document实例写出以形成xml文档
		 */

		//1
		Document doc=DocumentHelper.createDocument();

		/*
		 * 2:添加根元素
		 * Document提供方法：
		 * Element addElement(String name)
		 * 向当前文档中添加根元素，并将该元素以Element实例返回，以便继续操作根元素.
		 * 需要注意，该方法只能调用一次，因为一个XML文档中只允许有一个根元素.
		 */

		Element root=doc.addElement("list");

		/*
		 * Element也提供了相关操作方法：
		 * Element addElement(String name)
		 * 向当前元素中添加给定名字的子元素并返回
		 * Element addText(String text)
		 * 向当前元素中添加文本，添加到该开始标签与结束标签之间
		 */

		for(Emp emp:list) {
			//向根目录中添加<emp>
			Element empEle=root.addElement("emp");
			//向<emp>中添加<name>
			Element nameEle=empEle.addElement("name");
			nameEle.addText(emp.getName());
			//向<emp>中添加<age>
			Element ageEle=empEle.addElement("age");
			ageEle.addText(String.valueOf(emp.getAge()));
			//向<emp>中添加<gender>
			Element genderEle=empEle.addElement("gender");
			genderEle.addText(emp.getGender());
			//向<emp>中添加<salary>
			Element salaryEle=empEle.addElement("salary");
			salaryEle.addText(String.valueOf(emp.getSalary()));
			//向<emp>中添加属性id
			empEle.addAttribute("id",String.valueOf(emp.getId()));
		}
		XMLWriter writer=null;
		try {
			writer=new XMLWriter(new FileOutputStream("myemp.xml"),OutputFormat.createPrettyPrint());
			writer.write(doc);
			System.out.println("写出完毕.");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(writer!=null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}