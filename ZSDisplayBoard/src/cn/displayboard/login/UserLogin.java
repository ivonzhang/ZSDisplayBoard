package cn.displayboard.login;

import java.util.HashMap;
import java.util.Map;

import cn.displayboard.net.constant.NetConstant;
import cn.ivonzhang.httpexcuteUtils.HttpUploadUtil;
import cn.ivonzhang.views.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class UserLogin extends Activity {

	private EditText username = null;
	private EditText password = null;
	private CheckBox mSavePasswordBox = null;
	private Button loginbtn = null;
	private Button registerbtn = null;
	private Handler handler = null;
	private String result = null;

	// private ProgressDialog progressDialog = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);
		intiView();
		loginbtn.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				doLogin();
			}
		});
		registerbtn.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
	}

	// 执行登陆操作
	private void doLogin() {

		// 封装请求头需要的参数
		Map<String, String> params = new HashMap<String, String>();
		params.put("username", username.getText().toString().trim());
		params.put("password", password.getText().toString());
		// progressDialog = new ProgressDialog(this);
		// progressDialog.show(UserLogin.this, "登陆", "正在登陆,请稍后...");

		handler = new Handler() {
			public void handleMessage(Message msg) {
				if (msg.what == 1) {
					Bundle data = msg.getData();
					result = data.getString("msg");
					if (result != null) {
						if (result.equals("MatchError")) {
							// progressDialog.dismiss();
							Toast.makeText(UserLogin.this, "账号或密码错误",
									Toast.LENGTH_LONG).show();
							password.setHint("账号或密码错误");
						} else if (result.equals("LoginSuccess")) {
							// progressDialog.dismiss();
							Toast.makeText(UserLogin.this, "恭喜你，登陆成功",
									Toast.LENGTH_LONG).show();
							finish();
						}
					}/*
					 * else{ System.out.println("asdfa"); }
					 */
				}
			}
		};
		// 执行http操作，发送登陆请求
		HttpUploadUtil.postWithoutFile(NetConstant.LOGIN_URL, params, handler);

	}

	// 初始化控件
	private void intiView() {
		username = (EditText) findViewById(R.id.login_username);
		password = (EditText) findViewById(R.id.login_password);
		loginbtn = (Button) findViewById(R.id.login_btn);
		registerbtn = (Button) findViewById(R.id.register_btn);
		mSavePasswordBox = (CheckBox) findViewById(R.id.auto_login);

	}
}
