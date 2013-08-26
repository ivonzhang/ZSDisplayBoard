package cn.displayboard.login.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.displayboard.login.dao.LoginDao;
import cn.displayboard.login.service.LoginService;

public class LoginAction extends HttpServlet {
	/*
	 * private LoginDao dao = null;
	 * 
	 * @Override public void init() throws ServletException { // TODO
	 * Auto-generated method stub super.init(); dao = new LoginDao(); }
	 */

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");

		LoginDao dao = new LoginDao();
		List<Object> params = new ArrayList<Object>();
		
		// ��װռλ��
		params.add(request.getParameter("username"));
		params.add(request.getParameter("password"));

		System.out.println(params);
		// �ж��û������Ƿ�ƥ��
		if (!dao.doLogin(params)) {
			request.setAttribute("logintips", "�û��������벻ƥ��");
			request.getRequestDispatcher("/index.jsp").forward(request,
					response);
			return;
		}
		request.getSession().setAttribute("loginusername", request.getParameter("username"));
		request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
