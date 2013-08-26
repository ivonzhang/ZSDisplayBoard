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
	private String username;  �û�������Ϊ�գ�����Ҫ��3-8���ַ� abcdABcd
	private String password;  ���벻��Ϊ�գ�����Ҫ��3-8������
	private String password2; ��������Ҫһ��
	private String email;     ����Ϊ�գ���Ϊ��Ҫ��һ���Ϸ�������
	private String birthday;  ����Ϊ�գ���Ϊ��ʱ��Ҫ��һ���Ϸ�������
	 * 
	 */
	public boolean validate(){
		
		boolean isOk = true;
		
		if(this.username==null || this.username.trim().equals("") ){
			isOk = false;
			errors.put("username", "�û�������Ϊ��");
		}else{
			if(!this.username.matches("^[\\w\\-����[��-��]\u4e00-\u9fa5\uFF21-\uFF3A\uFF41-\uFF5A]+$")){//!this.username.matches("[a-zA-Z]{3,8}")
				isOk = false;
				errors.put("username", "�û���������4-20λ����Ӣ�ġ��»��߼��������");
			}
		}
		
		
		if(this.password==null || this.password.trim().equals("")){
			isOk = false;
			errors.put("password", "���벻��Ϊ��");
		}else{
			if(!this.password.matches("^[a-zA-Z]\\w{5,17}$")){//\\d{6,14}
				isOk = false;
				errors.put("password", "�����������ĸ��ͷ��������6~18֮�䣬ֻ�ܰ����ַ������ֺ��»���");
			}
		}
		
		//private String password2; ��������Ҫһ��
		if(this.confirmpsw!=null){
			if(!this.confirmpsw.equals(this.password)){
				isOk = false;
				errors.put("confirmpsw", "�������벻һ��");
			}
		}
		//�ǳƲ���Ϊ��
		if(this.nickname == null || this.nickname.trim().equals("")){
			isOk = false ;
			errors.put("nickname", "�ǳƲ���Ϊ��");
		}
		
		//private String email;     ����Ϊ�գ���Ϊ��Ҫ��һ���Ϸ�������
		// flx_itcast@sina.com.cn
		if(this.email!=null){
			if(!this.email.matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")){//  \\w+@\\w+(\\.\\w+)+
				isOk = false;
				errors.put("email", "���䲻��һ���Ϸ�����");
			}
		}
		
		return isOk;
	}
	
}
