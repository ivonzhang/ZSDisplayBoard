<%@ page language="java" import="java.util.*"
	contentType="text/html;charset=UTF-8"%>
<%@page
	import="cn.displayboard.register.dao.RegisterDao,cn.displayboard.transcoding.MyConverter"%>
<%@page
	import="cn.displayboard.formbean.* , cn.displayboard.form2beanUtils.*" %>
<%@page import="cn.displayboard.dbutil.JsonTools"%>
<%
	String path = request.getContextPath();
	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("UTF-8");
	RegisterFormbean formbean = WebUtils.request2Bean(request,
			RegisterFormbean.class);
%>

<%!private RegisterDao dao = new RegisterDao();%>
<%
	//表单校验
		if(formbean.validate()==false){
			request.setAttribute("formbean", formbean);
			String json = JsonTools.createJsonString("registerError",formbean.getErrors());
			out.println(json);
			
			return;
		}
 %>

<%
	String param1 = request.getParameter("username").trim();
	String param2 = request.getParameter("password").trim();
	String param3 = request.getParameter("nickname").trim();
	String param4 = request.getParameter("email").trim();
	String param5 = request.getParameter("position").trim();
%>

<%
	List<Object> params = new ArrayList<Object>();
	//封装占位符
		params.add(formbean.getUsername());
		params.add(formbean.getPassword());
		params.add(formbean.getNickname());
		params.add(formbean.getEmail());
		params.add(formbean.getPosition());
		
		if(!dao.registerUser(params)){
			formbean.getErrors().put("username", "该用户名已被注册");
			request.setAttribute("formbean", formbean);
			out.println("UserExistError");
			return;
		}
		//注册成功
		out.println("registerSuccess");
%>