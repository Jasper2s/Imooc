package com.java.tcp;

import java.net.ServerSocket;
import java.net.Socket;

public class ThreadServerMain {

	public static void main(String[] args) {
		
		ServerSocket serverSocket=null;
		Socket socket=null;
		int count=0;//记录客户端连接数
		try {
			System.out.println("服务器已启动，等待客户端连接......");
			serverSocket=new ServerSocket(8888);
			while(true) {
				socket=serverSocket.accept();
				new Thread(new ThreadServer(socket)).start();
				count++;
				System.out.println("客户端连接数："+count);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
