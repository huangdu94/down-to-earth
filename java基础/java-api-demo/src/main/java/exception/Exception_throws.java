package exception;

import java.awt.AWTException;
import java.io.IOException;

/**
 * 子类重写父类含有throws异常抛出声明的方法时
 * 对throws的重写原则
 * @author Bean
 *
 */
public class Exception_throws {
	public void dosome() throws IOException,AWTException{}
}
class Son extends Exception_throws{
	//重写 子类抛出的异常小于等于父类. 1.子类不抛出异常 2.子类抛出父类异常的子类 3.子类抛出父类异常的一部分 4.子类抛出和父类一样的异常
	
	//允许不抛出任何异常
//	public void dosome(){}

	//允许抛出父类方法异常的子类
//	public void dosome() throws FileNotFoundException,AWTException{}
	
	//允许抛出部分异常
//	public void dosome() throws IOException{}
	
	//允许抛出父类型的异常
//	public void dosome() throws IOException,AWTException{}
	
	//不允许抛出额外异常
//	public void dosome() throws IOException,AWTException,SQLException{}
	
	//不允许抛出父类方法异常的父类
//	public void dosome() throws Exception{}
}