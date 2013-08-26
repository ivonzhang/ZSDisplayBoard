<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
	<head>
		<meta charset="utf-8" />
		<script language="javascript" type="text/javascript"
			src="js/jquery-1.10.2.min.js"></script>
		<script language="javascript" type="text/javascript" src="js/index.js"></script>
		<script language="javascript" type="text/javascript"
			src="js/jquery.smallslider.js"></script>

		<link rel="stylesheet" type="text/css" href="css/navigation.css">
		<link rel="stylesheet" type="text/css" href="css/index.css">
		<link rel="stylesheet" type="text/css" href="css/smallslider.css">

		<title>掌上展板</title>
		<style type="text/css">
body {
	margin: 0px;
	padding: 0px;
	front-size: 12px;
	background-color: #D15FEE;
	text-align: center;
}

.pageTitle {
	background-color: #9ACD32;
	text-align: center;
}

#form1div {
	margin-top: 50px;
	margin-right: -1000px;
}

#btn input {
	cursor: hand;
}
</style>
		<script type="text/javascript">
	function dologin()
	{
	    var th = document.form1;
		if(th.username.value=="")
		{
        alert("用户名不能为空");
        th.username.focus();
        return ;
        }
       if(th.password.value=="")
       {
        alert("密码不能为空");
        th.password.focus();
        return ;
        }
       th.action="<%=path%>/servlet/LoginAction";
       th.submit();
       }
		
		function doregister()
		{
			var th = document.form1;
			th.action="<%=path%>/servlet/RegisterAction";
       		th.submit();
		}
		</script>
	</head>

	<body>
		<div class="pageTitle">
			<h1>
				掌上展板
			</h1>
		</div>
		<div id="flashbox" class="smallslider">
			<ul>
				<li>
					<a href=""><img src="images/001.jpg" width="320" alt="" /> </a>
				</li>
				<li>
					<a href=""><img src="images/002.jpg" width="320" alt="" /> </a>
				</li>
				<li>
					<a href=""><img src="images/003.jpg" width="320" alt="" /> </a>
				</li>
				<li>
					<a href=""><img src="images/004.jpg" width="320" alt="" /> </a>
				</li>
				<li>
					<a href=""><img src="images/005.jpg" width="320" alt="" /> </a>
				</li>
			</ul>
		</div>
		<div id="form1div">
			<form class="loginForm" name="form1" action="" method="post"
				id="login">
				<div class="input-prepend">
					<span class="add-on">账号</span>
					<input class="span2" id="prependedInput" type="text"
						name="username" />
					<span>${logintips }</span>
				</div>

				<div class="input-prepend">
					<span class="add-on">密码</span>
					<input class="span2" id="prependedInput" type="password"
						name="password" />
				</div>

				<div id="btn">
					<input type="submit" value="登陆" class="registerBtn"
						onclick="dologin()" />

					<input type="submit" value="注册" class="loginBtn"
						onclick="doregister()" />

				</div>


			</form>
		</div>


	</body>
</html>