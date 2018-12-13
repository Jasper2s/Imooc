package com.java.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 *  多线程下的客户端
 * @author qiuzhiwen
 *
 */
public class ThreadClient implements Runnable{

	Socket socket=null;
	
	public ThreadClient(Socket socket) {
		this.socket=socket;
	}
	
	@Override
	public void run() {
		OutputStream os=null;
		PrintWriter pw=null;
		
		InputStream is=null;
		InputStreamReader isr=null;
		BufferedReader br=null;
		try {
			os=socket.getOutputStream();
			pw=new PrintWriter(os);
			InetAddress ia=socket.getLocalAddress();
			pw.write("我是"+ia.getHostName()+",你好，服务器！");
			pw.flush();
			socket.shutdownOutput();
			
			is=socket.getInputStream();
			isr=new InputStreamReader(is);
			br=new BufferedReader(isr);
			String msg=null;
			while((msg=br.readLine())!=null) {
				System.out.println("服务器说："+msg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
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
