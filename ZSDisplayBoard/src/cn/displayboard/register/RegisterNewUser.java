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

//����ע�����û�
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
		//���ڷ���ע������
		registerbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				doRegister();
			}
		});
	}

	// ��ʼ��ע�������Ҫ�õ��Ŀؼ�
	public void initView() {

		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		confirmpsw = (EditText) findViewById(R.id.confirmpsw);
		nickname = (EditText) findViewById(R.id.nickname);
		email = (EditText) findViewById(R.id.email);
		position = (EditText) findViewById(R.id.position);
		registerbtn = (Button)findViewById(R.id.registerbtn);

	}

	// ִ��ע�����û��Ĳ���
	public void doRegister() {

		// ��װ����ͷ��Ҫ�Ĳ���
		Map<String, String> params = new HashMap<String, String>();
		params.put("username", username.getText().toString().trim());
		params.put("password", password.getText().toString().trim());
		params.put("confirmpsw", confirmpsw.getText().toString().trim());
		params.put("nickname", nickname.getText().toString().trim());
		params.put("email", email.getText().toString().trim());
		params.put("position", position.getText().toString().trim());

		// ���߳����ж�ע���Ƿ�ɹ�������UI����ʾ�û�
		handler = new Handler() {
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				if (msg.what == 1) {
					Bundle data = msg.getData();
					result = data.getString("msg");
					System.out.println(result);
					if(result !=null){

						if (result.equals("registerSuccess")) {
							Toast.makeText(RegisterNewUser.this, "��ϲ����ע��ɹ�",
									Toast.LENGTH_LONG);
							Intent intent1 = new Intent();
							intent1.setClass(RegisterNewUser.this,
									cn.displayboard.homeview.MainActivity.class);
							startActivity(intent1);
							finish();

						} else if (result.equals("UserExistError")) {
							username.setText("���û����ѱ�ע����Ŷ");

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
						Toast.makeText(RegisterNewUser.this, "������������������������",
								Toast.LENGTH_LONG);
					}
				}
			}

		};
		// ִ��http����������ע������
		HttpUploadUtil.postWithoutFile(NetConstant.REGISTER_URL, params,
				handler);

	}
	

}
