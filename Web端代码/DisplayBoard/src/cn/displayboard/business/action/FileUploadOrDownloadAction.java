package cn.displayboard.business.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.displayboard.business.dao.FileUploadDao;

public class FileUploadOrDownloadAction extends HttpServlet {

	private FileUploadDao dao = null;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String act_flag = request.getParameter("act_flag");
		dao = new FileUploadDao();
		if (act_flag.equals("upload")) {
			System.out.println(act_flag);
			doUpload(request, response);
		}
		out.write(act_flag);
	}

	private void doUpload(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 表单含有文件要提交
		String path = request.getContextPath();
		// 构建一个文件上传类
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		ServletFileUpload servletFileUpload = new ServletFileUpload(
				diskFileItemFactory);
		servletFileUpload.setFileSizeMax(3 * 1024 * 1024);// 单个文件上传最大值
		servletFileUpload.setSizeMax(6 * 1024 * 1024);// 上传文件总大小
		List<FileItem> list = null;
		List<Object> params = new ArrayList<Object>();
		params.add(cn.displayboard.dbutil.UUIDTools.getUUID());
		try {
			// 解析request的请求
			list = servletFileUpload.parseRequest(request);
			// 取出所有表单的值:判断非文本字段和文本字段
			for (FileItem fileItem : list) {
				if (fileItem.isFormField()) {
					if (fileItem.getFieldName().equals("displayboardname")) {
						params.add(fileItem.getString("UTF-8"));
					}
					if (fileItem.getFieldName().equals("displayboardtype")) {
						params.add(fileItem.getString("UTF-8"));
					}
				} else {
					try {
						String image = fileItem.getName();
						params.add(image);
						String upload_path = request
								.getRealPath("/userUploadImages");
						System.out.println("--->>" + upload_path);
						//
						File real_path = new File(upload_path + "/" + image);
						fileItem.write(real_path);
						// 把数据插入到数据库中
						boolean flag = dao.doFileUpload(params);
						if (flag) {
							response.sendRedirect(path + "/index.jsp");
						}
						else{
							request.getRequestDispatcher("/zhanbanUpload.jsp").forward(request, response);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);

	}

}
