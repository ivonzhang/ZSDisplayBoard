package cn.displayboard.login.dao;

import java.sql.SQLException;
import java.util.List;

import cn.displayboard.dbutil.JdbcUtils;
import cn.displayboard.login.service.LoginService;

public class LoginDao implements LoginService {

	private JdbcUtils utils = null;

	public LoginDao() {
		// TODO Auto-generated constructor stub
		utils = new JdbcUtils();
	}

	public boolean doLogin(List<Object> params) {
		// TODO Auto-generated method stub
		String sql = "select * from userinfo where username=? and password=?";
		try {
			utils.getConnection();
			if (utils.findSimpleResult(sql, params).isEmpty()) {
				System.out.println(utils.findSimpleResult(sql, params));
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

}
