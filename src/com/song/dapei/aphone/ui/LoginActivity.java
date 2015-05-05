package com.song.dapei.aphone.ui;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.song.dapei.aphone.R;
import com.song.dapei.aphone.client.SSOAuth;
import com.song.dapei.aphone.client.listener.ExecuteListener;
import com.song.dapei.aphone.config.DapeiConstants;
import com.song.dapei.aphone.util.CommonUtils;

/**
 * 登入
 * @author songzigw
 */
public class LoginActivity extends BaseActivity implements OnClickListener {

	EditText et_account, et_password;
	Button btn_login;
	TextView btn_register;

	private MyBroadcastReceiver receiver = new MyBroadcastReceiver();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initTopBarForLeft("登入");
		init();
		// 注册退出广播
		IntentFilter filter = new IntentFilter();
		filter.addAction(DapeiConstants.ACTION_REGISTER_SUCCESS_FINISH);
		registerReceiver(receiver, filter);
	}

	private void init() {
		et_account = (EditText) findViewById(R.id.et_account);
		et_password = (EditText) findViewById(R.id.et_password);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_register = (TextView) findViewById(R.id.btn_register);
		btn_login.setOnClickListener(this);
		btn_register.setOnClickListener(this);
	}

	public class MyBroadcastReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent != null
					&& DapeiConstants.ACTION_REGISTER_SUCCESS_FINISH
							.equals(intent.getAction())) {
				finish();
			}
		}

	}

	@Override
	public void onClick(View v) {
		if (v == btn_register) {
			Intent intent = new Intent(LoginActivity.this,
					RegisterActivity.class);
			startActivity(intent);
		} else if (v == btn_login) {
			boolean isNetConnected = CommonUtils.isNetworkAvailable(this);
			if (!isNetConnected) {
				ShowToast(R.string.network_tips);
				return;
			}
			login();
		}
	}

	private void login() {
		String name = et_account.getText().toString();
		String password = et_password.getText().toString();

		if (TextUtils.isEmpty(name)) {
			ShowToast(R.string.toast_error_account_null);
			return;
		}

		if (TextUtils.isEmpty(password)) {
			ShowToast(R.string.toast_error_password_null);
			return;
		}

		final ProgressDialog progress = new ProgressDialog(this);
		progress.setMessage("正在登陆...");
		progress.setCanceledOnTouchOutside(false);
		progress.show();
		SSOAuth.getInstance(this).login(name, password, new ExecuteListener() {
			@Override
			public void onSuccess(Object obj) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						progress.setMessage("正在获取好友列表...");
					}
				});
				// 更新用户的地理位置以及好友的资料
				updateUserInfos();
				progress.dismiss();
				finish();
			}

			@Override
			public void onFailure(String errCode, String errNotic) {
				progress.dismiss();
				ShowToast(errNotic);
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(receiver);
	}

}
