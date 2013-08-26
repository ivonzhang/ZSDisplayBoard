package cn.displayboard.net.constant;

public interface NetConstant {

	String IP = "http://"+"192.168.1.121"+":8080/DisplayBoard";
	String REGISTER_URL = IP+"/register.jsp";
	String LOGIN_URL = IP+"/login.jsp";
	String HOTZHANBANLOAD_URL = IP+"/servlet/RecentHotDisplayboardJson";
}
