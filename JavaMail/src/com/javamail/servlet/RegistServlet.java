package com.javamail.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javamail.service.UserService;
import com.javamail.service.impl.UserServiceImpl;
import com.javamail.utils.UUIDUtil;
import com.javamail.vo.UserVO;

/**
 * Servlet implementation class UserServlet
 */
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		
		//获取数据
		String username=request.getParameter("yourname");
		String password=request.getParameter("yourpass");
		String nickname=request.getParameter("nickname");
		String email=request.getParameter("youremail");
		
		//封装数据
		UserVO user=new UserVO();
		user.setUid(UUIDUtil.getUUID());
		user.setUsername(username);
		user.setNickname(nickname);
		user.setPassword(password);
		user.setEmail(email);
		user.setState("0");//0-未激活  1-已激活
		user.setCode(UUIDUtil.getUUID()+UUIDUtil.getUUID());
		
		//保存数据
		UserService userService=new UserServiceImpl();
		userService.regist(user);
		
		//页面跳转
		request.setAttribute("msg", "您已注册成功，请去邮箱激活！");
		request.getRequestDispatcher("/msg.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
