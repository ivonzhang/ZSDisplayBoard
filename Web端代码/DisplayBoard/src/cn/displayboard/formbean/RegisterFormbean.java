package cn.displayboard.formbean;

import java.util.HashMap;
import java.util.Map;

public class RegisterFormbean {

	private String username;
	private String password;
	private String confirmpsw;
	private String nickname;
	private String email;
	private String position;
	
	public Map<String, String> getErrors() {
		return errors;
	}
	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

	private Map<String,String> errors = new HashMap();
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmpsw() {
		return confirmpsw;
	}
	public void setConfirmpsw(String confirmpsw) {
		this.confirmpsw = confirmpsw;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}

	/*
	private String username;  用户名不能为空，并且要是3-8的字符 abcdABcd
	private String password;  密码不能为空，并且要是3-8的数字
	private String password2; 两次密码要一致
	private String email;     可以为空，不为空要是一个合法的邮箱
	private String birthday;  可以为空，不为空时，要是一个合法的日期
	 * 
	 */
	public boolean validate(){
		
		boolean isOk = true;
		
		if(this.username==null || this.username.trim().equals("") ){
			isOk = false;
			errors.put("username", "用户名不能为空");
		}else{
			if(!this.username.matches("^[\\w\\-－＿[０-９]\u4e00-\u9fa5\uFF21-\uFF3A\uFF41-\uFF5A]+$")){//!this.username.matches("[a-zA-Z]{3,8}")
				isOk = false;
				errors.put("username", "用户名必须是4-20位的中英文、下划线及数字组合");
			}
		}
		
		
		if(this.password==null || this.password.trim().equals("")){
			isOk = false;
			errors.put("password", "密码不能为空");
		}else{
			if(!this.password.matches("^[a-zA-Z]\\w{5,17}$")){//\\d{6,14}
				isOk = false;
				errors.put("password", "密码必须以字母开头，长度在6~18之间，只能包含字符、数字和下划线");
			}
		}
		
		//private String password2; 两次密码要一致
		if(this.confirmpsw!=null){
			if(!this.confirmpsw.equals(this.password)){
				isOk = false;
				errors.put("confirmpsw", "两次密码不一致");
			}
		}
		//昵称不能为空
		if(this.nickname == null || this.nickname.trim().equals("")){
			isOk = false ;
			errors.put("nickname", "昵称不能为空");
		}
		
		//private String email;     可以为空，不为空要是一个合法的邮箱
		// flx_itcast@sina.com.cn
		if(this.email!=null){
			if(!this.email.matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")){//  \\w+@\\w+(\\.\\w+)+
				isOk = false;
				errors.put("email", "邮箱不是一个合法邮箱");
			}
		}
		
		return isOk;
	}
	
}
