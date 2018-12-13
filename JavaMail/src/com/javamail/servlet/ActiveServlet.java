package com.javamail.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javamail.service.UserService;
import com.javamail.service.impl.UserServiceImpl;
import com.javamail.vo.UserVO;

/**
 * ���伤��
 */
public class ActiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActiveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String code=request.getParameter("code");
		
		UserService userService=new UserServiceImpl();
		UserVO user=userService.findUserByCode(code);
		
		if(user!=null){//�û��Ѿ�����
			user.setState("1");
			user.setCode(null);
			userService.updateUser(user);
			request.setAttribute("msg", "�û�����ɹ�");
			request.getRequestDispatcher("/msg.jsp").forward(request, response);
		}else{
			//�����������û�������
			request.setAttribute("msg", "�����������û�������");
			request.getRequestDispatcher("/msg.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
