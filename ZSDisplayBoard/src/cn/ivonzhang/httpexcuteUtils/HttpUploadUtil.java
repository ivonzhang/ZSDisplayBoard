package cn.ivonzhang.httpexcuteUtils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.*;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import cn.ivonzhang.transcoding.MyConverter;

//ͨ��HttpЭ�鷢�ʹ��ļ��򲻴��ļ�������Ĺ�����
public class HttpUploadUtil {

	// �����ļ��������ͷ���
	/**
	 * @param actionUrl
	 * @param params
	 * @return
	 */
	//private String result=null;
	
	
	public static void postWithoutFile(String actionUrl, // �����URL
			Map<String, String> params ,// ����Ĳ�������
			Handler handler     //���������̷߳�������Ľ��
	) {
		
		final String url = actionUrl;
		final Map<String, String> param = params;
		final Handler handler2 = handler;
		new Thread(){
			public void run()
			{
				Looper.prepare();
				HttpClient httpclient = new DefaultHttpClient();
				//����ʱ
		        httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
		        //��ȡ��ʱ
		        httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 10000);
				// ���URL
				HttpPost httppost = new HttpPost(url);
				try {
					List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
							param.size());

					for (Map.Entry<String, String> entry : param.entrySet()) {// �������ֶ�����
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
					//result = MyConverter.unescape(new String(data).trim());
					String result = new String(data,"UTF-8").trim();
					Message msg = new Message();
					Bundle dat = new Bundle();
					dat.putString("msg", result);
					msg.setData(dat);
					msg.what=1;
					handler2.sendMessage(msg);
					
				} catch (Exception e) {
					e.printStackTrace();
					//result = "error";
				}finally{
					httppost.abort();
				}
			}
		}.start();
		
		//return null;
		/*
		
		HttpClient httpclient = new DefaultHttpClient();
		// ���URL
		HttpPost httppost = new HttpPost(actionUrl);
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
					params.size());

			for (Map.Entry<String, String> entry : params.entrySet()) {// �������ֶ�����
				nameValuePairs.add(new BasicNameValuePair(entry.getKey(),
						MyConverter.escape(entry.getValue())));
			}
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
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
			return MyConverter.unescape(new String(data).trim());
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}*/
	}
}
