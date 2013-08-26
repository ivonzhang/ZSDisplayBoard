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

	// ִ�е�½����
	private void doLogin() {

		// ��װ����ͷ��Ҫ�Ĳ���
		Map<String, String> params = new HashMap<String, String>();
		params.put("username", username.getText().toString().trim());
		params.put("password", password.getText().toString());
		// progressDialog = new ProgressDialog(this);
		// progressDialog.show(UserLogin.this, "��½", "���ڵ�½,���Ժ�...");

		handler = new Handler() {
			public void handleMessage(Message msg) {
				if (msg.what == 1) {
					Bundle data = msg.getData();
					result = data.getString("msg");
					if (result != null) {
						if (result.equals("MatchError")) {
							// progressDialog.dismiss();
							Toast.makeText(UserLogin.this, "�˺Ż��������",
									Toast.LENGTH_LONG).show();
							password.setHint("�˺Ż��������");
						} else if (result.equals("LoginSuccess")) {
							// progressDialog.dismiss();
							Toast.makeText(UserLogin.this, "��ϲ�㣬��½�ɹ�",
									Toast.LENGTH_LONG).show();
							finish();
						}
					}/*
					 * else{ System.out.println("asdfa"); }
					 */
				}
			}
		};
		// ִ��http���������͵�½����
		HttpUploadUtil.postWithoutFile(NetConstant.LOGIN_URL, params, handler);

	}

	// ��ʼ���ؼ�
	private void intiView() {
		username = (EditText) findViewById(R.id.login_username);
		password = (EditText) findViewById(R.id.login_password);
		loginbtn = (Button) findViewById(R.id.login_btn);
		registerbtn = (Button) findViewById(R.id.register_btn);
		mSavePasswordBox = (CheckBox) findViewById(R.id.auto_login);

	}
}
