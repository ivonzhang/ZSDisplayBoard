package cn.displayboard.homeview;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.DefaultClientConnection;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.displayboard.homeview.RTPullListView.OnRefreshListener;
import cn.displayboard.net.constant.NetConstant;
import cn.ivonzhang.views.R;

public class MainActivity extends Activity {

	private static final int INTERNET_FAILURE = -1;
	private static final int LOAD_SUCCESS = 1;
	private static final int LOAD_MORE_SUCCESS = 3;
	private static final int NO_MORE_INFO = 4;
	private static final int LOAD_NEW_INFO = 5;

	
	private boolean isExit;
	private ProgressBar moreProgressBar;
	private TextView menuText, findText, panelText, templateText;
	private RTPullListView pullListView;
	private List<ItemInfo> list;
	private MyAdapter adapter;
	private LayoutInflater inflater;
	private RelativeLayout footerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		findView();

		list = new ArrayList<ItemInfo>();//用于存放json数据
		LoadImageAsycTask loadImageAsycTask = new LoadImageAsycTask();
		loadImageAsycTask.execute(NetConstant.HOTZHANBANLOAD_URL);
		/*for (int i = 0; i < 3; i++) {
			ItemInfo info = new ItemInfo("", "");//用于显示左右对称的两幅图片
			list.add(info);
		}
		adapter = new MyAdapter(MainActivity.this, list);
		pullListView.setAdapter(adapter);
		//pullListView.addFooterView(footerView);//用于添加底部的刷新部件"获取更多"
		setListener();*/

		

	}
	
	public class LoadImageAsycTask extends AsyncTask <String , Void , List<Map<String, Object>>>{

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}
		@Override
		protected List<Map<String, Object>> doInBackground(String... params) {
			// TODO Auto-generated method stub
			List<Map<String, Object>> list2 = new ArrayList<Map<String,Object>>();
			try {
				HttpClient client = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(params[0]);
				HttpResponse httpResponse = client.execute(httpPost);
				if(httpResponse.getStatusLine().getStatusCode() == 200)
				{
					String jsonString = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
					
					if(jsonString.equals("searchError"))//如果服务器没有更新数据,返回的错误提示
					{
						Map<String,Object> map = new HashMap<String, Object>();
						map.put("Error", "searchError");
						list2.add(map);
						return list2;
					}
					else{
						JSONObject jsonObject = new JSONObject(jsonString);
						JSONArray jsonArray = jsonObject.getJSONArray("recenthotdisplayboard");
						for(int i = 0 ;i<jsonArray.length() ; i++)
						{
							JSONObject jsonObject2 = jsonArray.getJSONObject(i);
							Map<String,Object> map = new HashMap<String, Object>();
							Iterator<String> iterator = jsonObject2.keys();
							System.out.println(iterator);
							while(iterator.hasNext())
							{
								String key = iterator.next();
								Object value = jsonObject2.get(key);
								map.put(key, value);
							}
							list2.add(0 , map);//list倒序插入
							//list2.add(map);
						}
						
					}
				}
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return list2;
		}
		
		//更新UI
		@Override
		protected void onPostExecute(List<Map<String, Object>> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(result.size()==1&&result.get(0).get("Error")!=null)
			{
				Toast.makeText(MainActivity.this, "还没有更新哦", Toast.LENGTH_LONG).show();
			}else
			{
				for (int i = 0; i < result.size(); i+=2) {
					ItemInfo info = new ItemInfo(result.get(i+1), result.get(i));//用于显示左右对称的两幅图片
					list.add(0,info);
					//list.add(info);
				}
				System.out.println(list);
				adapter = new MyAdapter(MainActivity.this, list);
				pullListView.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				setListener();
			}
		}
		
	}
	
	
	//初始化控件
	public void findView() {
		menuText = (TextView) super.findViewById(R.id.textView1);
		findText = (TextView) super.findViewById(R.id.textView2);
		panelText = (TextView) super.findViewById(R.id.textView3);
		templateText = (TextView) super.findViewById(R.id.textView4);
		pullListView = (RTPullListView) super.findViewById(R.id.pullListView);
		
		/*inflater = LayoutInflater.from(this);
		View view = inflater.inflate(R.layout.list_footview, null);
		footerView = (RelativeLayout) view.findViewById(R.id.list_footview);
		moreProgressBar = (ProgressBar) view.findViewById(R.id.footer_progress);*/
	}
	
	//顶部的刷新操作监听事件
	public void setListener(){
		pullListView.setonRefreshListener(new OnRefreshListener() {

			public void onRefresh() {
				new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							Thread.sleep(2000);
							list.clear();/*
							for (int i = 0; i < 3; i++) {
//								ItemInfo info = new ItemInfo(R.drawable.hammer,
//										R.drawable.hammer);
//								list.add(info);
							}*/
							myHandler.sendEmptyMessage(LOAD_NEW_INFO);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}).start();
				//adapter.notifyDataSetChanged();
			}
		});
/*
		//底部"获取更多"的更新操作监听器
		footerView.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				moreProgressBar.setVisibility(View.VISIBLE);

				new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							Thread.sleep(2000);
							list.clear();
							for (int i = 0; i < 3; i++) {
								ItemInfo info = new ItemInfo(R.drawable.hammer,
										R.drawable.hammer);
								list.add(info);
							}
							myHandler.sendEmptyMessage(LOAD_MORE_SUCCESS);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}).start();
			}
		});*/
		menuText.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, MenuDialog.class));
				
			}
		});
	}

	//更新UI
	private Handler myHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {/*
			case LOAD_MORE_SUCCESS:
				moreProgressBar.setVisibility(View.GONE);
				adapter.notifyDataSetChanged();
				pullListView.setSelectionfoot();
				break;*/

			case LOAD_NEW_INFO:
				new LoadImageAsycTask().execute(NetConstant.HOTZHANBANLOAD_URL);
				//adapter.notifyDataSetChanged();
				pullListView.onRefreshComplete();
				break;
			default:
				break;
			}
		}

	};

	/*
	 * 实现按两次back键就退出
	 */

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exitActivity();
			return false;
		} else {
			return super.onKeyDown(keyCode, event);
		}

	}

	private void exitActivity() {
		Handler exitHandler;
		exitHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				isExit = false;
			}
		};
		if (!isExit) {
			isExit = true;
			Toast.makeText(MainActivity.this, "再按一次退出", Toast.LENGTH_SHORT)
					.show();
			exitHandler.sendEmptyMessageDelayed(1, 2000);

		} else {
			finish();
		}
	}


}
