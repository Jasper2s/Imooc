package com.java.udp;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class URL {
	
	public static void main(String[] args) {
		try {
			//InetAddress ia=InetAddress.getLocalHost();
			InetAddress ia=InetAddress.getByName("www.baidu.com");//注意：不加协议！
			System.out.println(ia.getHostName());
			System.out.println(ia.getHostAddress());
			System.out.println(ia.getCanonicalHostName());
			System.out.println(Arrays.toString(ia.getAddress()));
			
			
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
	}

}
