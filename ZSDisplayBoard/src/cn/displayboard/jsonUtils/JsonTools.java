package cn.displayboard.jsonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import cn.displayboard.domain.RegisterBean;

public class JsonTools {

	public JsonTools() {
		// TODO Auto-generated constructor stub
	}

	//����ע��ʧ�ܵ���Ϣ �������ǲ���optXXX()��������getXXX()������ǰ�ߵ����ҵļ���������ʱ�ط���null�����߻ᱨ��
	public static RegisterBean getRegisterJson(String key , String jsonString )
	{
		RegisterBean registerBean = new RegisterBean();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONObject registerBeanObject = jsonObject.getJSONObject(key);
			String username = registerBeanObject.optString("username")!=null ? registerBeanObject.optString("username") : "";
			String password = registerBeanObject.optString("password")!=null ? registerBeanObject.optString("password") : "";
			String confirmpsw = registerBeanObject.optString("confirmpsw")!=null ? registerBeanObject.optString("confirmpsw") : "";
			String nickname = registerBeanObject.optString("nickname")!=null ? registerBeanObject.optString("nickname") : "";
			String email = registerBeanObject.optString("email")!=null ? registerBeanObject.optString("email") : "";
			registerBean.setUsername(username);
			registerBean.setPassword(password);
			registerBean.setConfirmpsw(confirmpsw);
			registerBean.setNickname(nickname);
			registerBean.setEmail(email);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return registerBean;
	}
	
	
}
