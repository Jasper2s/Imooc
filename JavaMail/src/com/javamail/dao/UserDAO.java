package com.javamail.dao;

import com.javamail.vo.UserVO;

public interface UserDAO {
	
	public void regist(UserVO user);

	public UserVO findUserByCode(String code);

	public void updateUser(UserVO user);

}
