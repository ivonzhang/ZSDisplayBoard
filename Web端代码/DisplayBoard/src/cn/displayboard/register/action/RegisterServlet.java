package cn.displayboard.register.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import cn.displayboard.bean.User;
import cn.displayboard.form2beanUtils.WebUtils;
import cn.displayboard.formbean.RegisterFormbean;
import cn.displayboard.register.dao.RegisterDao;

public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("utf-8");
		RegisterFormbean formbean = WebUtils.request2Bean(request, RegisterFormbean.class);
		//PrintWriter out = response.getWriter();
		 
		//表单校验
		if(formbean.validate()==false){
			request.setAttribute("formbean", formbean);
			request.getRequestDispatcher("/WEB-INF/jsp/register1.jsp").forward(request,response);
			//out.println("failed");
			return;
		}
		
		//User user = new User();
		RegisterDao dao = new RegisterDao();
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
			request.getRequestDispatcher("/WEB-INF/jsp/register1.jsp").forward(request, response);
			//out.println("failed");
			return;
		}
		//out.println("success");
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	

}
