package com.java.security.des;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

public class DESTest {
	
	private String text="Jasper is a good student!";
	
	@Test
	public void testJDKDES(){
		try {
			//生成Key
			KeyGenerator keyGenerator=KeyGenerator.getInstance("DES");
			keyGenerator.init(56);//JDKDES实现--密钥长度默认是56位
			SecretKey secretKey=keyGenerator.generateKey();
			byte[] bytesKey=secretKey.getEncoded();
			
			//Key转换
			DESKeySpec desKeySpec=new DESKeySpec(bytesKey);
			SecretKeyFactory factory=SecretKeyFactory.getInstance("DES");
			SecretKey convertSecretKey=factory.generateSecret(desKeySpec);
			
			//加密
			Cipher cipher=Cipher.getInstance("DES/ECB/PKCS5Padding");
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
