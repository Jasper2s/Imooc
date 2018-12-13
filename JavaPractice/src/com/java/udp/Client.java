package com.java.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		try {
			System.out.println("客户端已启动......");
			InetAddress address=InetAddress.getByName("192.168.1.6");
			//1.创建DatagramSocket实例，并指定端口和IP地址
			//DatagramSocket socket=new DatagramSocket(8800, InetAddress.getByName("localhost"));
			DatagramSocket socket=new DatagramSocket();
			//2.定义消息数据
			String msg="你好服务器，我是"+InetAddress.getLocalHost();
			//3.将消息转为字节数组
			byte[] data=msg.getBytes();
			//4.定义数据包
			DatagramPacket packet=new DatagramPacket(data, data.length, address, 8800);
			//DatagramPacket packet=new DatagramPacket(data, data.length);
			//5.发送消息
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
