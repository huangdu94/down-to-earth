package mychat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * 聊天室客户端
 * @author Bean
 */
public class Client {
	/*
	 * java.net.Socket
	 * 封装了TCP协议，使用它可以基于TCP协议与远端计算机的程序交互
	 */
	private Socket socket;
	private Scanner scan=new Scanner(System.in);

	/**
	 * 构造方法，用来初始化客户端
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public Client() throws UnknownHostException, IOException {
		/*
		 * 实例化Socket的使用通常传入两个参数：
		 * 1：远端计算机的地址信息(IP)
		 * 2：端口号 通过端口号可以找到运行在远端计算机中需要通讯的应用程序.
		 * 
		 * 实例化Socket的过程就是发起连接的过程.若服务端没有响应，这里会抛出异常
		 */
//		System.out.println("请输入服务端IP：（114.214.186.154.XX）");
//		String ip="114.214.186.154."+scan.nextInt();
		String ip="114.214.186.154";
		//System.out.println("请输入服务端端口号：");
		int port=8088;
		System.out.println("客户端准备连接IP为："+ip+"，端口号为："+port+"的服务端.");
		socket=new Socket(ip,port);
		System.out.println("客户端连接成功.");
	}
	/**
	 * 客户端开始工作的方法
	 */
	public void start() throws IOException {
		/*
		 * 若希望向服务端发送数据，需要通过Socket获取输出流，通过该流写出的数据就被发送到了服务端.
		 * 
		 * Socket提供的方法：
		 * OutputStream getOutputStream()
		 */
		System.out.println("为客户端创建流");
		OutputStream out=socket.getOutputStream();
		OutputStreamWriter osw=new OutputStreamWriter(out,"utf-8");
		PrintWriter pw=new PrintWriter(osw,true);
		System.out.println("客户端流创建完毕，等待用户输入：");
		ServerHandler server=new ServerHandler();
		new Thread(server).start();
		while(true) {
			String input=scan.nextLine();
			pw.println(input);
		}
	}

	private class ServerHandler implements Runnable{
		/*
		 * 若希望可以随时收到服务端转发过来的消息，那么就不能让读取服务端消息的代码与给服务端发送消息的代码同步操作.否则互相会受影响.
		 * 解决方法：将循环读取服务端发送过来的消息并输出到控制台的代码放到另一个线程中运行.
		 */
		public void run() {
			try {
				//建立好输入流
				InputStream in=socket.getInputStream();
				InputStreamReader isr=new InputStreamReader(in,"utf-8");
				final BufferedReader br=new BufferedReader(isr);
				//读取服务端发送过来的数据，并输出到控制台
				String message;
				while((message=br.readLine())!=null) {
					System.out.println(message);
				}
				System.out.println("服务端已经关闭.");
			} catch (IOException e) {
			}
		}
	}

	public static void main(String[] args) {

		try {
			Client client=new Client();
			client.start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
