package com.song.dapei.aphone.ui;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

import com.song.dapei.aphone.R;

/**
 * 用户详细资料
 * 
 * @author songzigw
 *
 */
public class UserInfoActivity extends BaseActivity implements OnClickListener {

	private RelativeLayout layout_head, layout_nick, layout_gender,
			layout_birthday, layout_summary;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info);

		initView();
	}

	private void initView() {
		initTopBarForLeft("详细资料");
		layout_head = (RelativeLayout) findViewById(R.id.layout_head);
		layout_nick = (RelativeLayout) findViewById(R.id.layout_nick);
		layout_gender = (RelativeLayout) findViewById(R.id.layout_gender);
		layout_birthday = (RelativeLayout) findViewById(R.id.layout_birthday);
		layout_summary = (RelativeLayout) findViewById(R.id.layout_summary);

		layout_head.setOnClickListener(this);
		layout_nick.setOnClickListener(this);
		layout_gender.setOnClickListener(this);
		layout_birthday.setOnClickListener(this);
		layout_summary.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

	}

}
