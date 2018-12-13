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
 * 多线程下的服务器端
 * @author qiuzhiwen
 *
 */
public class ThreadServer implements Runnable{

	Socket socket=null;
	
	public ThreadServer(Socket socket) {
		this.socket=socket;
	}
	
	@Override
	public void run() {
		InputStream is=null;
		InputStreamReader isr=null;
		BufferedReader br=null;
		OutputStream os=null;
		PrintWriter pw=null;
		try {
			is=socket.getInputStream();
			isr=new InputStreamReader(is);
			br=new BufferedReader(isr);
			InetAddress ia=socket.getInetAddress();
			String msg=null;
			//循环读取客户端发送的信息
			while((msg=br.readLine())!=null) {
				System.out.println(ia.getHostName()+"说："+msg);
			}
			socket.shutdownInput();//关闭输入流
			
			os=socket.getOutputStream();
			pw=new PrintWriter(os);
			pw.write("Hello "+ia.getHostName());
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
				try {
					if(br!=null)
						br.close();
					if(isr!=null)
						isr.close();
					if(is!=null)
						is.close();
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
