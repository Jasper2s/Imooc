package com.javamail.service;

import com.javamail.vo.UserVO;

public interface UserService {
	
	public void regist(UserVO user);

	public UserVO findUserByCode(String code);

	public void updateUser(UserVO user);

}
