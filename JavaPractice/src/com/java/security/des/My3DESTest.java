package com.java.security.des;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

public class My3DESTest {
	
private String text="Jasper is a good student!";
	
	@Test
	public void testJDK3DES(){
		try {
			//生成Key
			KeyGenerator keyGenerator=KeyGenerator.getInstance("DESede");
			//keyGenerator.init(56);//JDKDES实现--密钥长度默认是56位
			keyGenerator.init(new SecureRandom());//根据加密方式随机生成一个长度
			SecretKey secretKey=keyGenerator.generateKey();
			byte[] bytesKey=secretKey.getEncoded();
			
			//Key转换
			DESedeKeySpec desKeySpec=new DESedeKeySpec(bytesKey);
			SecretKeyFactory factory=SecretKeyFactory.getInstance("DESede");
			SecretKey convertSecretKey=factory.generateSecret(desKeySpec);
			
			//加密
			Cipher cipher=Cipher.getInstance("DESede/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
			byte[] result=cipher.doFinal(text.getBytes());
			System.out.println("加密："+Hex.encodeHexString(result));
			
			//解密
			cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
			result=cipher.doFinal(result);
			System.out.println("解密："+new String(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
