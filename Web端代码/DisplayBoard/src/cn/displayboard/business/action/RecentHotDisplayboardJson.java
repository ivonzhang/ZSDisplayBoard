package cn.displayboard.business.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.displayboard.dbutil.JdbcUtils;

public class RecentHotDisplayboardJson extends HttpServlet {

	private List<Map<String, Object>> list;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		JdbcUtils utils = new JdbcUtils();
		String sql = "select * from hotdisplayboardInfo order by hotdisplayboardId desc limit 6";
		list = new ArrayList<Map<String, Object>>();
		try {
			utils.getConnection();
			list = utils.findMoreResult(sql, null);
			if(list!=null)
			{
				request.setAttribute("hotdisplayboardinfo", list);
				System.out.println(list);
			}else{
				request.setAttribute("hotdisplayboardinfo", "searchError");
				System.out.println("searchError");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("hotdisplayboardinfo", "searchError");
			System.out.println("searchError");
		} 
		request.getRequestDispatcher("/WEB-INF/jsp/recentHotDisplayboardJson.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
