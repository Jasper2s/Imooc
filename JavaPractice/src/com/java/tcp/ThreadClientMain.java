package com.java.tcp;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ThreadClientMain {
	
	public static void main(String[] args) {
		
		Socket socket=null;
		try {
			System.out.println("客户端已启动......");
			socket = new Socket("127.0.0.1", 8888);
			new Thread(new ThreadClient(socket)).start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

}
