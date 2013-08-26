<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>展板上传</title>
    <script type="text/javascript">
    function dosubmit()
    {
    	var th = document.form;
    	if(th.displayboardname.value =="")
    	{
    		alert("展板名不能为空");
    		th.displayboardname.focus();
        	return ;
    	}
    	if(th.displayboardtype.value =="")
    	{
    		alert("展板类型不能为空");
    		th.displayboardtype.focus();
        	return ;
    	}
    	th.action="<%=path%>/servlet/FileUploadOrDownloadAction?act_flag=upload";
		th.submit();
    }
    </script>
  </head>
  
  <body style=text-align: center>
  <h1>展板上传</h1>
  <hr>
  <form action="" 
method="post" enctype="multipart/form-data" name="form">
  <div>
  <span>展板名称(必填)：<input type="text" name="displayboardname"></span><br>
  <span>展板类型(必填)：<input type="text" name="displayboardtype"></span><br>
  <span>选择展板：<input type="file" name="diplayboardimage"></span><br>
  <input type="submit" value="提交" onclick="dosubmit()">
  </div>
  </form>
  </body>
</html>
