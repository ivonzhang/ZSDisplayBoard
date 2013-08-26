package cn.displayboard.register;

import java.util.HashMap;
import java.util.Map;

import cn.displayboard.domain.RegisterBean;
import cn.displayboard.jsonUtils.JsonTools;
import cn.displayboard.net.constant.NetConstant;
import cn.ivonzhang.httpexcuteUtils.HttpUploadUtil;
import cn.ivonzhang.views.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//用于注册新用户
public class RegisterNewUser extends Activity {

	private EditText username = null;
	private EditText password = null;
	private EditText confirmpsw = null;
	private EditText nickname = null;
	private EditText email = null;
	private EditText position = null;
	private Button registerbtn = null;
	private Handler handler = null;
	private String result = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_layout);
		initView();
		//用于发送注册请求
		registerbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				doRegister();
			}
		});
	}

	// 初始化注册表单中需要用到的控件
	public void initView() {

		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		confirmpsw = (EditText) findViewById(R.id.confirmpsw);
		nickname = (EditText) findViewById(R.id.nickname);
		email = (EditText) findViewById(R.id.email);
		position = (EditText) findViewById(R.id.position);
		registerbtn = (Button)findViewById(R.id.registerbtn);

	}

	// 执行注册新用户的操作
	public void doRegister() {

		// 封装请求头需要的参数
		Map<String, String> params = new HashMap<String, String>();
		params.put("username", username.getText().toString().trim());
		params.put("password", password.getText().toString().trim());
		params.put("confirmpsw", confirmpsw.getText().toString().trim());
		params.put("nickname", nickname.getText().toString().trim());
		params.put("email", email.getText().toString().trim());
		params.put("position", position.getText().toString().trim());

		// 在线程中判断注册是否成功，更新UI，提示用户
		handler = new Handler() {
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				if (msg.what == 1) {
					Bundle data = msg.getData();
					result = data.getString("msg");
					System.out.println(result);
					if(result !=null){

						if (result.equals("registerSuccess")) {
							Toast.makeText(RegisterNewUser.this, "恭喜您，注册成功",
									Toast.LENGTH_LONG);
							Intent intent1 = new Intent();
							intent1.setClass(RegisterNewUser.this,
									cn.displayboard.homeview.MainActivity.class);
							startActivity(intent1);
							finish();

						} else if (result.equals("UserExistError")) {
							username.setText("该用户名已被注册了哦");

						} else {
							System.out.println(JsonTools.getRegisterJson(
									"registerError", result));
							RegisterBean registerbean = JsonTools.getRegisterJson(
									"registerError", result);
							if(!registerbean.getUsername().equals("")){
								username.setHint(registerbean.getUsername());
							}
							if(!registerbean.getPassword().equals("")){
								//password.setText(registerbean.getPassword());
								password.setText("");
								password.setHint(registerbean.getPassword());
								confirmpsw.setText("");
							}
							if(!registerbean.getConfirmpsw().equals("")){
								confirmpsw.setText("");
								confirmpsw.setHint(registerbean.getConfirmpsw());
							}
							if(!registerbean.getNickname().equals("")){
								nickname.setHint(registerbean.getNickname());
							}
							if(!registerbean.getEmail().equals("")){
								email.setHint(registerbean.getEmail());
							}
						}
					}
					else{
						Toast.makeText(RegisterNewUser.this, "发送请求有误，请检查网络连接",
								Toast.LENGTH_LONG);
					}
				}
			}

		};
		// 执行http操作，发送注册请求
		HttpUploadUtil.postWithoutFile(NetConstant.REGISTER_URL, params,
				handler);

	}
	

}
