package cn.displayboard.homeview;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import cn.displayboard.asynctaskLoadingImageView.NetImageViewCache;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.widget.ImageView;

public class MyImageView extends ImageView {
	
	private Paint paint;
	private String text="正在加载信息...";
	private String mPicUrl;
	private MyImageView myImageView;

	public MyImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
	}
	public MyImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * 开启新线程加载图片
	 */
	/*public void setImageUrl(String url) {
		mPicUrl = url;
		loadImage(url);//利用url加载网络图片
	}*/
	public void setImageUrl(String url , MyImageView myImageView) {
		mPicUrl = url;
		this.myImageView = myImageView ;
		loadImage(url);//利用url加载网络图片
	}

	

	/*
	 * 用于在imageview上写入文字信息
	 */
	public void setText(String str){
		this.text = str;
	}

	//在imageview中画一个写入文字的区域
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		
		float startpoint=(float)(getHeight()/5*4);
		super.onDraw(canvas);
		paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setAlpha(0);
		paint.setAntiAlias(true);
		paint.setStyle(Style.STROKE);
		canvas.drawRect(0, startpoint, getWidth(), getHeight(), paint);
		paint.setColor(Color.BLUE);
		canvas.drawText(text, getWidth()/3, startpoint+25, paint);
		
	}
	

	private void loadImage(String url) {
		// TODO Auto-generated method stub
		if (NetImageViewCache.getInstance().isBitmapExit(url)) {
			Bitmap bitmap = NetImageViewCache.getInstance().get(url);
			myImageView.setImageBitmap(bitmap);
		}
		else
		{
			new NetImageDownLoad().execute(mPicUrl);
		}
	}
	private byte[] getBytesFromStream(InputStream inputStream) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] b = new byte[1024];
		int len = 0;
		while (len != -1) {
			try {
				len = inputStream.read(b);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (len != -1) {
				baos.write(b, 0, len);
			}
		}

		if (inputStream != null) {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return baos.toByteArray();
	}
	
	/*
	 * 加载图片的异步任务
	 * 
	 */
	private class NetImageDownLoad extends AsyncTask<String, Void, Bitmap> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Bitmap doInBackground(String... params) {
			URL url = null;
			InputStream inputStream = null;
			HttpURLConnection urlConnection = null;
			Bitmap bmp = null;
			try {
				url = new URL(params[0]);
				urlConnection = (HttpURLConnection) url.openConnection();
				urlConnection.setRequestMethod("GET");//采用Post方式能够加大数据量
				urlConnection.setConnectTimeout(10000);
				inputStream = urlConnection.getInputStream();
				byte[] bt = getBytesFromStream(inputStream);
				bmp = BitmapFactory.decodeByteArray(bt, 0, bt.length);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (null != inputStream) {
					try {
						inputStream.close();
						inputStream = null;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (null != urlConnection) {
					urlConnection.disconnect();
					urlConnection = null;
				}
			}
			return bmp;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			if (null != result) {
//				MyImageView.this.setImageBitmap(result);
				myImageView.setImageBitmap(result);
				//加载完毕后，将图片写入缓存
				NetImageViewCache.getInstance().put(mPicUrl, result, true);				
			}
			super.onPostExecute(result);
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			super.onProgressUpdate(values);
		}
	}
}
