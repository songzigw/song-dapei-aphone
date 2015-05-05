package com.song.dapei.aphone.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.song.dapei.aphone.R;

public class WelcomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_welcome);

		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					sleep(3000);
				} catch (InterruptedException e) {
					Log.e("WelcomeActivity", "onCreate()", e);
				} finally {
					Intent intent = new Intent();
					intent.setClass(WelcomeActivity.this, MainActivity.class);
					startActivity(intent);
				}
			}
		};
		t.start();
	}

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}

}
