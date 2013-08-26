package cn.displayboard.register.dao;

import java.util.ArrayList;
import java.util.List;

import cn.displayboard.dbutil.JdbcUtils;
import cn.displayboard.exception.UserExistException;
import cn.displayboard.register.service.RegisterService;

public class RegisterDao implements RegisterService {

	private JdbcUtils utils = null;
	
	public RegisterDao() {
		// TODO Auto-generated constructor stub
		utils = new JdbcUtils(); 
	}

	public boolean registerUser(List<Object> params) {
		// TODO Auto-generated method stub
		
		boolean flag = false;
		
		List<Object> param = new ArrayList<Object>();
		param.add(params.get(0));
		
		String sql1 = "select * from userinfo where username=?";	
		String sql = "insert into userinfo(username,password,nickname,email,position) values(?,?,?,?,?)";
		try {
			// 先获得链接
			utils.getConnection();
			if(!utils.findSimpleResult(sql1, param).isEmpty())
			{
				//throw new UserExistException();
				//System.out.println("failed!!!");
				return false;
			}
			flag = utils.updateByPreparedStatement(sql, params);
			System.out.println("-flag-->>" + flag);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			// 关闭数据库的链接
			utils.releaseConn();
		}
		return flag;
	}

}
