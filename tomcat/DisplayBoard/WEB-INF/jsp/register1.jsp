<%@ page language="java" import="java.util.*"
	contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();

request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>掌上展板用户注册</title>
		<style type="text/css">
body {
	margin: 0px;
	padding: 0px;
	front-size: 12px;
}

#div1{
margin-top: 50px;
margin-left: 300px;
}
		</style>
		
	</head>

	<body>
	<center><h1>掌上展板用户注册</h1></center>
		<div id="div1">
		<form action="${pageContext.request.contextPath }/servlet/RegisterServlet" name="form" method="post">
		用户名<span></span><br>
		<input type="text" name="username" placeholder="请填写您的用户名">${formbean.errors.username }<br>
		密     码<span></span><br>
		<input type="password" name="password" placeholder="请输入您的密码" >${formbean.errors.password }<br>
		确认密码<span></span><br>
		<input type="password" name="confirmpsw" placeholder="请确认您的密码" >${formbean.errors.confirmpsw }<br>
		昵称<span>（必填）</span><br>
		<input type="text" name="nickname" placeholder="请填写您的昵称" >${formbean.errors.email }<br>
		邮箱<span>（选填）</span><br>
		<input type="text" name="email" placeholder="请填写您的邮箱" >${formbean.errors.email }<br>
		身份<span>（选填）</span><br>
		<input type="text" name="position" placeholder="您是身份，如：学生、商家"><br>
		<input type="submit" value="提交" >
		
		</form>			
		</div>
	</body>
</html>
