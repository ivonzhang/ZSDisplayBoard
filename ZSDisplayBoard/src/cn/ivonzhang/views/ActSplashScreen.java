package cn.ivonzhang.views;


import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class ActSplashScreen extends Activity {

	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				intent = new Intent(ActSplashScreen.this, cn.displayboard.homeview.MainActivity.class);
				//intent = new Intent(ActSplashScreen.this, MainActivity.class);
				startActivity(intent);
				ActSplashScreen.this.finish();
			}
		}, 3000);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.act_splash_screen, menu);
		return true;
	}

}
