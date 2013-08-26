package cn.displayboard.business.dao;

import java.sql.SQLException;
import java.util.List;

import cn.displayboard.business.service.BusinessService;
import cn.displayboard.dbutil.JdbcUtils;

public class FileUploadDao implements BusinessService {

	private JdbcUtils utils = null;

	public FileUploadDao() {
		// TODO Auto-generated constructor stub
		utils = new JdbcUtils();
	}

	// 用于上传普通用户的展板
	public boolean doFileUpload(List<Object> params) {
		// TODO Auto-generated method stub
		Boolean flag = false;
		try {
			String sql = "insert into displayboardinfo(displayboardid,displayboardname,displayboardtype,displayboardimage) values(?,?,?,?)";
			utils.getConnection();
			flag = utils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			utils.releaseConn();
		}
		return flag;
	}

	public boolean doHotZhanbanUpload(List<Object> params) {
		// TODO Auto-generated method stub
		Boolean flag = false;
		try {
			String sql = "insert into hotdisplayboardInfo(hotdisplayboardName,hotdisplayboardType,hotdisplayboardDiscription, hotlevel , hotdisplayboardUrl) values(?,?,?,?,?)";
			utils.getConnection();
			flag = utils.updateByPreparedStatement(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

}
