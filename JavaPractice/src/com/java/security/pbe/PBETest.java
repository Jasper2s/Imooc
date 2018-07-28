package com.java.security.pbe;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

public class PBETest {
	
	private String text="Jasper is a good student!";
	
	@Test
	public void testJDKPBE(){
		try {
			//初始化盐
			SecureRandom random=new SecureRandom();
			byte[] salt=random.generateSeed(8);
			
			//口令与密钥
			String password="Jasper";
			PBEKeySpec pbeKeySpec=new PBEKeySpec(password.toCharArray());
			SecretKeyFactory factory=SecretKeyFactory.getInstance("PBEWITHMD5andDES");
			SecretKey key=factory.generateSecret(pbeKeySpec);
			
			//加密
			PBEParameterSpec parameterSpec=new PBEParameterSpec(salt, 100);
			Cipher cipher=Cipher.getInstance("PBEWITHMD5andDES");
			cipher.init(Cipher.ENCRYPT_MODE, key, parameterSpec);
			byte[] result=cipher.doFinal(text.getBytes());
			System.out.println("加密："+Hex.encodeHexString(result));
			
			//解密
			cipher.init(Cipher.DECRYPT_MODE, key,parameterSpec);
			result=cipher.doFinal(result);
			System.out.println("解密："+new String(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
