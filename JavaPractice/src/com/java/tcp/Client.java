package com.java.tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
/**
 * 模拟客户端
 * @author qiuzhiwen
 *
 */
public class Client {
	public static void main(String[] args) {
		System.out.println("客户端正在启动.......");
		Socket socket=null;
		OutputStream os=null;
		PrintWriter pw=null;
		try {
			//1.创建Socket实例并指定IP和端口
			socket=new Socket("127.0.0.1", 8888);
			//2.通过Socket实例获取输出流
			os=socket.getOutputStream();
			//3.创建打印流
			pw=new PrintWriter(os);
			//4.将消息写入打印流
			pw.write("你好，服务器！");
			//5.刷新打印流缓冲
			pw.flush();
			//6.关闭输出流
			socket.shutdownOutput();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {//7.关闭资源
				try {
					if(pw!=null)
						pw.close();
					if(os!=null)
						os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}
