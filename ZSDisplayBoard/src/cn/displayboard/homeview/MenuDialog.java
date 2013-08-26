package cn.displayboard.homeview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;
import cn.ivonzhang.views.R;

public class MenuDialog extends Activity implements OnClickListener{
	
	private LinearLayout llayout1,llayout2,llayout3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menudialog);
		findView();
		setListener();
	}
	
	public void findView(){
		llayout1 = (LinearLayout)super.findViewById(R.id.linearlayout1);
		llayout2 = (LinearLayout)super.findViewById(R.id.linearlayout2);
		llayout3 = (LinearLayout)super.findViewById(R.id.linearlayout3);
	}
	
	public void setListener(){
		llayout1.setOnClickListener(this);
		llayout2.setOnClickListener(this);
		llayout3.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		//Toast.makeText(MenuDialog.this, v.getId()+"", Toast.LENGTH_SHORT).show();
		
		int list_ID = v.getId();
		switch (list_ID) {
		case R.id.linearlayout1:

			Intent intent1 = new Intent();
			intent1.setClass(MenuDialog.this, cn.displayboard.login.UserLogin.class);
			startActivity(intent1);
			this.finish();
			break;
		case R.id.linearlayout2:
			Intent intent2 = new Intent();
			intent2.setClass(MenuDialog.this, cn.displayboard.register.RegisterNewUser.class);
			startActivity(intent2);
			this.finish();
			break;
		
		case R.id.linearlayout3:
			Toast.makeText(MenuDialog.this, v.getId()+"", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		finish();
		return super.onTouchEvent(event);
	}
	
	
	

}
