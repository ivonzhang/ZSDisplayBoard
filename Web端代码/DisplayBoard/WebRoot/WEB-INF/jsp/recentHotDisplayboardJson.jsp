<%@ page language="java" import="java.util.*,cn.displayboard.dbutil.*"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");

	if (request.getAttribute("hotdisplayboardinfo").toString().equals(
			"searchError")) {

		out.println("searchError");

	} else {
		String json = JsonTools.createJsonString("recenthotdisplayboard",
				request.getAttribute("hotdisplayboardinfo"));
		out.println(json);

	}
%>
