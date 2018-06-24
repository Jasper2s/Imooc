package com.javamail.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.javamail.dao.UserDAO;
import com.javamail.utils.JDBCUtil;
import com.javamail.vo.UserVO;

public class UserDAOImpl implements UserDAO{

	@Override
	public void regist(UserVO user) {
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		JDBCUtil jdbcUtil=JDBCUtil.getInstance();
		try {
			conn=jdbcUtil.getConnection();
			String sql="insert into regist values(?,?,?,?,?,?,?)";
			st=conn.prepareStatement(sql);
			st.setString(1, user.getUid());
			st.setString(2, user.getUsername());
			st.setString(3, user.getPassword());
			st.setString(4, user.getNickname());
			st.setString(5, user.getEmail());
			st.setString(6, user.getState());
			st.setString(7, user.getCode());
			st.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			jdbcUtil.close(rs, st, conn);
		}
		
	}

	@SuppressWarnings("null")
	@Override
	public UserVO findUserByCode(String code) {
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		UserVO user=new UserVO();
		JDBCUtil jdbcUtil=JDBCUtil.getInstance();
		try {
			conn=jdbcUtil.getConnection();
			String sql="select * from regist where code=?";
			st=conn.prepareStatement(sql);
			st.setString(1, code);
			rs=st.executeQuery();
			while(rs!=null&&rs.next()){
				String uid=rs.getString("uid");
				String username=rs.getString("username");
				String nickname=rs.getString("nickname");
				String email=rs.getString("email");
				String state=rs.getString("state");
				
				user.setUid(uid);
				user.setUsername(username);
				user.setNickname(nickname);
				user.setEmail(email);
				user.setState(state);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			jdbcUtil.close(rs, st, conn);
		}
		return user;
	}

	@Override
	public void updateUser(UserVO user) {
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		JDBCUtil jdbcUtil=JDBCUtil.getInstance();
		try {
			conn=jdbcUtil.getConnection();
			String sql="update regist set username=?,password=?,nickname=?,email=?,state=?,code=? where uid=?";
			st=conn.prepareStatement(sql);
			st.setString(1, user.getUsername());
			st.setString(2, user.getPassword());
			st.setString(3, user.getNickname());
			st.setString(4, user.getEmail());
			st.setString(5, user.getState());
			st.setString(6, user.getCode());
			st.setString(7, user.getUid());
			st.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			jdbcUtil.close(rs, st, conn);
		}
	}

}
