package cn.ivonzhang.views;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import cn.displayboard.jsonUtils.JsonTools;
import cn.ivonzhang.httpexcuteUtils.HttpUploadUtil;
import cn.ivonzhang.transcoding.MyConverter;
import android.R.string;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity{

	private String IP = "192.168.1.118";
	private String actionUrl = "http://"+IP+":8080/DisplayBoard/register.jsp";
	//private String actionUrl = "http://"+IP+":8080/DisplayBoard/servlet/RegisterServlet";
	private Map<String, String> params;
	private TextView txt;
	private Handler handler;
	private String result;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);
		txt = (TextView)findViewById(R.id.textView1);
		params = new HashMap<String, String>();
		//params.put("username", MyConverter.unescape(MyConverter.escape("黄蓉")));
		params.put("username", "");
		params.put("password", "Z123456");
		params.put("confirmpsw", "Z123456");
		params.put("nickname", "");
		params.put("email", "as1235qq.com");
		//params.put("position", MyConverter.unescape(MyConverter.escape("学生")));
		params.put("position", "学生");
		
		handler = new Handler(){
			public void handleMessage(Message msg){
				super.handleMessage(msg);
				if(msg.what == 1)
				{
					Bundle data = msg.getData();
					result = data.getString("msg");
					System.out.println(result);
					if(result!=null && result.equals("registerSuccess")){
						//Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG);
						txt.setText(result);
						
					}else if(result!=null && result.equals("UserExistError")){
						//Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG);
						//result = "error";
						txt.setText(result);
					}else{
						System.out.println(JsonTools.getRegisterJson("registerError", result));
						txt.setText(result);
					}
				}
			}
		};
		
		HttpUploadUtil.postWithoutFile(actionUrl, params, handler);
		
		
		/*
		final String url = actionUrl;
		final Map<String, String> param = params;
		new Thread(){
			public void run()
			{
				Looper.prepare();
				HttpClient httpclient = new DefaultHttpClient();
				// 你的URL
				HttpPost httppost = new HttpPost(url);
				try {
					List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
							param.size());

					for (Map.Entry<String, String> entry : param.entrySet()) {// 构建表单字段内容
						nameValuePairs.add(new BasicNameValuePair(entry.getKey(),
								entry.getValue()));
					}
					httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"utf-8"));
					HttpResponse response;
					response = httpclient.execute(httppost);
					InputStream in = response.getEntity().getContent();
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					int ch = 0;
					while ((ch = in.read()) != -1) {
						baos.write(ch);
					}
					byte[] data = baos.toByteArray();
					baos.close();
					String result = MyConverter.unescape(new String(data).trim());
					Message msg = new Message();
					Bundle dat = new Bundle();
					dat.putString("msg", result);
					msg.setData(dat);
					msg.what=1;
					handler.sendMessage(msg);
					
				} catch (Exception e) {
					e.printStackTrace();
					//result = "error";
				}
			}
		}.start();
		*/
		
		
		
		
		
		
		
		
		
		
		
		/*
		
		String result = HttpUploadUtil.postWithoutFile(actionUrl, params);
		if(result!=null && result.equals("注册成功!")){
			//Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG);
			txt.setText(result);
			
		}else{
			//Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG);
			txt.setText(result);
		}*/
	}

}
