package cn.displayboard.test;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.displayboard.dbutil.JdbcUtils;

public class DbTest {

	/**
	 * @param args
	 */
	@Test
	public void test1()
	{
		new JdbcUtils();
	}
	@Test
	public void testDaoshuliutiaojilu(){
		JdbcUtils utils = new JdbcUtils();
		//String sql = "select * from userinfo order by userid desc limit 6";
		String sql = "select * from hotdisplayboardInfo order by hotdisplayboardId desc limit 6";
		
		try {
			utils.getConnection();
			System.out.println(utils.findMoreResult(sql, null));
			System.out.println(utils.findMoreResult(sql, null).size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//中文乱码？？？？
	@Test
	public void testSelect() throws Exception
	{
		JdbcUtils utils = new JdbcUtils();
		List<Object> param = new ArrayList<Object>();
		param.add("abcd34");
		param.add(new String("风2009".getBytes("utf-8"),"UTF-8"));
		//param.add("abcd34");
		param.add("风花雪月");
		param.add("abcd34@qq.com");
		//param.add("学生");
		//String sql = "insert into userinfo(username,password,nickname,email,position) values(?,?,?,?,?)";
		String sql = "insert into displayboardinfo(displayboardid,displayboardname,displayboardtype,displayboardimage) values(?,?,?,?)";
		try {
			utils.getConnection();
			System.out.println(new String("风2009".getBytes("utf-8"),"UTF-8"));
			System.out.println(utils.updateByPreparedStatement(sql, param));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
