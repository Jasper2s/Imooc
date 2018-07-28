package com.java.security.base64;

import java.util.Base64;

import org.junit.Test;

public class Security {
	
	private String text="Jsaper";
	
	@Test
	public void testJDKBase64(){
		String msg=Base64.getEncoder().encodeToString(text.getBytes());
		System.out.println("加密："+msg);
		
		byte[] byteArr=Base64.getDecoder().decode(msg);
		String msg2=new String(byteArr);
		System.out.println("解密:"+msg2);
	}
	
	@Test
	public void testCommonsCodec(){
		byte[] byteArr=org.apache.commons.codec.binary.Base64.encodeBase64(text.getBytes());
		String msg=new String(byteArr);
		System.out.println("加密："+msg);
		
		byte[] byteArr2=org.apache.commons.codec.binary.Base64.decodeBase64(msg);
		String msg2=new String(byteArr2);
		System.out.println("解密："+msg2);
	}
	
}
