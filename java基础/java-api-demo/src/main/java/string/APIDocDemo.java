package string;
/** 
 *文档注释
 * 文档注释只修饰三个地方：
 * 类，方法，常量
 * 
 * 在类上声明是用来说明当前类的设计目的，以及当前类的功能.
 * 
 *@author Bean
 *@version 1.0,22/09/17
 *@see java.lang.String
 *@since JDK1.5
 */
public class APIDocDemo {
	/**
	 * sayHello中使用的问候短语
	 */
	public static final String INFO="hello!";
	
	/**
	 * 为给定的用户添加一个用户语 
	 * @param name 需要添加问候语的用户的名字
	 * @return 含有问候语的字符串
	 */
	public String sayHello(String name) {
		return INFO+name;
	}
}
//UNICODE utf-8