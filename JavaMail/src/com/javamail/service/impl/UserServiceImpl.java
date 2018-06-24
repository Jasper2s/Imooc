package com.javamail.service.impl;

import com.javamail.dao.UserDAO;
import com.javamail.dao.impl.UserDAOImpl;
import com.javamail.service.UserService;
import com.javamail.utils.MailUtil;
import com.javamail.vo.UserVO;

public class UserServiceImpl implements UserService{

	@Override
	public void regist(UserVO user) {
		
		//将数据存入数据库
		UserDAO userDAO=new UserDAOImpl();
		userDAO.regist(user);
		
		//发送激活邮件
		MailUtil.sendMail(user.getEmail(), user.getCode());
	}

	@Override
	public UserVO findUserByCode(String code) {
		UserDAO userDAO=new UserDAOImpl();
		return userDAO.findUserByCode(code);
	}

	@Override
	public void updateUser(UserVO user) {
		UserDAO userDAO=new UserDAOImpl();
		userDAO.updateUser(user);
		
	}

}
