package com.song.dapei.aphone.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.song.dapei.aphone.CustomApplication;
import com.song.dapei.aphone.R;
import com.song.dapei.aphone.view.dailog.DialogTips;

/**
 * 我的设置
 * 
 * @author songzigw
 *
 */
public class MySettingsActivity extends OnlineActivity implements
		OnClickListener {

	private Button btn_logout;

	private RelativeLayout rl_switch_notification, rl_switch_voice,
			rl_switch_vibrate, rl_switch_speaker;

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_logout:
			DialogTips dialog = new DialogTips(this, "", "确定退出吗？", "确定", true,
					false);
			dialog.SetOnSuccessListener(new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialogInterface, int id) {
					CustomApplication.getInstance().logout();
					MySettingsActivity.this.finish();
				}
			});
			dialog.show();
			dialog = null;
			break;
		case R.id.rl_switch_notification:
			break;
		case R.id.rl_switch_voice:
			break;
		case R.id.rl_switch_vibrate:
			break;
		case R.id.rl_switch_speaker:
			break;
		default:
			break;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_settings);
		initView();
	}

	private void initView() {
		initTopBarForLeft("设置");
		btn_logout = (Button) findViewById(R.id.btn_logout);
		rl_switch_notification = (RelativeLayout) findViewById(R.id.rl_switch_notification);
		rl_switch_voice = (RelativeLayout) findViewById(R.id.rl_switch_voice);
		rl_switch_vibrate = (RelativeLayout) findViewById(R.id.rl_switch_vibrate);
		rl_switch_speaker = (RelativeLayout) findViewById(R.id.rl_switch_speaker);

		btn_logout.setOnClickListener(this);
		rl_switch_notification.setOnClickListener(this);
		rl_switch_voice.setOnClickListener(this);
		rl_switch_vibrate.setOnClickListener(this);
		rl_switch_speaker.setOnClickListener(this);
	}

}
