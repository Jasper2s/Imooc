package com.java.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 基于TCP协议
 * 模拟服务器
 * @author qiuzhiwen
 *
 */
public class Server {
	public static void main(String[] args) {
		System.out.println("服务器正在启动中，等待客户端连接......");
		ServerSocket serverSocket=null;
		Socket socket=null;
		InputStream is=null;
		InputStreamReader isr=null;
		BufferedReader br=null;
		try {
			//1.创建ServerSocket实例并指定端口
			serverSocket=new ServerSocket(8888);
			//2.通过accept（）方法阻塞等待客户端连接，同时产生一个Socket实例
			socket=serverSocket.accept();
			//3.通过Socket实例获取字节输入流
			is=socket.getInputStream();
			//4.将字节输入流转为字符输入流，提高读取效率
			isr=new InputStreamReader(is);
			//5.将字符流放到缓冲区
			br=new BufferedReader(isr);
			String msg=null;
			//6.循环读取客户端发送的信息并处理
			while((msg=br.readLine())!=null) {
				System.out.println("我是服务器，客户端说："+msg);
			}
			//7.关闭输入流
			socket.shutdownInput();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {//8.关闭流资源
				try {
					if(br!=null)
						br.close();
					if(isr!=null)
						isr.close();
					if(is!=null) 
						is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}
