package cn.displayboard.domain;

public class RegisterBean {//用于封装注册失败时服务器返回的提示信息

	private String username;
	private String password;
	private String confirmpsw;
	private String nickname;
	private String email;
	
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
	

	@Override
	public String toString() {
		return "RegisterBean [username=" + username + ", password=" + password
				+ ", confirmpsw=" + confirmpsw + ", nickname=" + nickname
				+ ", email=" + email + "]";
	}
	
}
