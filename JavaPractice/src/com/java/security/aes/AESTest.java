package com.java.security.aes;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

public class AESTest {
	
	private String text="Jasper is a good student!";
	
	@Test
	public void testJDKAES(){
		try {
			//生成Key
			KeyGenerator keyGenerator=KeyGenerator.getInstance("AES");
			keyGenerator.init(new SecureRandom());
			SecretKey secretKey=keyGenerator.generateKey();
			byte[] bytesKey=secretKey.getEncoded();
			
			//转换Key
			Key key=new SecretKeySpec(bytesKey, "AES");
			
			//加密
			Cipher cipher=Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] result=cipher.doFinal(text.getBytes());
			System.out.println("加密1："+Base64.encodeBase64String(result));
			System.out.println("加密2："+Hex.encodeHexString(result));
			
			//解密
			cipher.init(Cipher.DECRYPT_MODE, key);
			result=cipher.doFinal(result);
			System.out.println("解密："+new String(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
