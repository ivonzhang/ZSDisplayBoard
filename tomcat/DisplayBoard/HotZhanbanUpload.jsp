<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>热门展板上传</title>
    <script type="text/javascript">
    function dosubmit()
    {
    	var th = document.form;
    	if(th.hotdisplayboardName.value =="")
    	{
    		alert("展板名不能为空");
    		th.hotdisplayboardName.focus();
        	return ;
    	}
    	if(th.hotdisplayboardType.value =="")
    	{
    		alert("展板类型不能为空");
    		th.hotdisplayboardType.focus();
        	return ;
    	}
    	if(th.hotdisplayboardDiscription.value =="")
    	{
    		alert("展板描述不能为空");
    		th.hotdisplayboardDiscription.focus();
        	return ;
    	}
    	if(th.hotlevel.value =="")
    	{
    		alert("推荐指数不能为空");
    		th.hotlevel.focus();
        	return ;
    	}
    	th.action="<%=path%>/servlet/HotDisplayBoardUpload";
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
  <span>展板名称(必填)：<input type="text" name="hotdisplayboardName"></span><br>
  <span>展板类型(必填)：<input type="text" name="hotdisplayboardType"></span><br>
  <span>展板描述(必填)：<input type="text" name="hotdisplayboardDiscription"></span><br>
  <span>推荐指数(选填)：<input type="text" name="hotlevel"></span><br>
  <span>选择展板：<input type="file" name="hotdisplayboardUrl"></span><br>
  <input type="submit" value="提交" onclick="dosubmit()">
  </div>
  </form>
  </body>
</html>
