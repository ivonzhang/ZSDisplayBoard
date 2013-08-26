<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="cn.displayboard.login.dao.*"%>
<%
	String path = request.getContextPath();
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>

<%!LoginDao dao = new LoginDao();%>
<%
	List<Object> params = new ArrayList<Object>();
	// 封装占位符
	params.add(request.getParameter("username"));
	params.add(request.getParameter("password"));
	// 判断用户密码是否匹配
	if (!dao.doLogin(params)) {
		out.println("MatchError");//匹配不成功
		//return;
	}else{
	request.getSession().setAttribute("loginusername",
			request.getParameter("username"));
	out.println("LoginSuccess");
	}
%>
