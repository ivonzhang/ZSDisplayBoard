package cn.displayboard.homeview;

import cn.ivonzhang.views.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends Activity {//�˴�������ʾ����չ��/ģ��
	
	private Intent intent;
	private ImageView img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detailslayout);
		findView();
		
		intent =this.getIntent();
		int number = intent.getExtras().getInt("imgnumber");
		img.setBackgroundResource(number);
		
		
	}
	
	private void findView(){
		img = (ImageView)super.findViewById(R.id.detailsimg);
	}

}
