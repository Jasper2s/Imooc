package com.java.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
/**
 * 基于UDP协议
 * 模拟服务器端
 * @author qiuzhiwen
 *
 */
public class Server {
	
	public static void main(String[] args) {
		
		DatagramSocket socket=null;
		try {
			System.out.println("服务器已启动....");
			//1.创建DatagramSocket实例
			socket=new DatagramSocket(8800);
			//2.定义接受数据的字节 数组
			byte[] data=new byte[1024];
			//3.定义数据包
			DatagramPacket packet=new DatagramPacket(data, data.length);
			//4.通过receive方法阻塞，等待客户端发送数据，同时将数据存放到数据包
			socket.receive(packet);
			//5.打印客户端发送端数据消息
			String msg=new String(data, 0, packet.getLength());
			InetAddress ia=packet.getAddress();
			System.out.println(ia.getHostAddress()+"客户端说："+msg);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
