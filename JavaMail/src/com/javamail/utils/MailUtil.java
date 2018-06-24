package com.javamail.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

/**
 * 邮件发送工具类
 * @author qiuzhiwen
 *
 */
public class MailUtil {
	
	public static void sendMail(String to,String code){
		Properties props=new Properties();
		//props.setProperty("host", "");
		//1.创建连接对象，连接到邮箱服务器
		Session session=Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("service@qzw.com", "123");
			}
		});
		//2.创建邮件对象
		Message message=new MimeMessage(session);
		try {
			//设置发件人
			message.setFrom(new InternetAddress("service@qzw.com"));
			//设置收件人
			message.setRecipient(RecipientType.TO, new InternetAddress(to));
			//设置邮件的主题
			message.setSubject("来自XXX网站的激活邮件");
			//设置邮件的正文
			message.setContent("<h1>来自XXX网站的激活邮件,激活请点击以下链接：</h1><br/><h3><a href='http://localhost:8080/JavaMail/ActiveServlet?code="+code+"'>http://localhost:8080/JavaMail/ActiveServlet?code="+code+"</a></h3>", "text/html;charset=UTF-8");
			//3.发送激活邮件
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
