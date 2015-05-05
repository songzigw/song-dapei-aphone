package com.song.dapei.aphone.ui;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

import com.song.dapei.aphone.R;

/**
 * （我的）个人信息
 * 
 * @author songzigw
 *
 */
public class MyselfInfoActivity extends OnlineActivity implements
		OnClickListener {

	private RelativeLayout layout_head, layout_account, layout_nick,
			layout_name, layout_gender, layout_birthday, layout_summary,
			layout_email, layout_qq, layout_ww;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myself_info);
		initView();
	}

	private void initView() {
		initTopBarForLeft("我的资料");
		layout_head = (RelativeLayout) findViewById(R.id.layout_head);
		layout_account = (RelativeLayout) findViewById(R.id.layout_account);
		layout_nick = (RelativeLayout) findViewById(R.id.layout_nick);
		layout_name = (RelativeLayout) findViewById(R.id.layout_name);
		layout_gender = (RelativeLayout) findViewById(R.id.layout_gender);
		layout_birthday = (RelativeLayout) findViewById(R.id.layout_birthday);
		layout_summary = (RelativeLayout) findViewById(R.id.layout_summary);
		layout_email = (RelativeLayout) findViewById(R.id.layout_email);
		layout_qq = (RelativeLayout) findViewById(R.id.layout_qq);
		layout_ww = (RelativeLayout) findViewById(R.id.layout_ww);

		layout_head.setOnClickListener(this);
		layout_account.setOnClickListener(this);
		layout_nick.setOnClickListener(this);
		layout_name.setOnClickListener(this);
		layout_gender.setOnClickListener(this);
		layout_birthday.setOnClickListener(this);
		layout_summary.setOnClickListener(this);
		layout_email.setOnClickListener(this);
		layout_qq.setOnClickListener(this);
		layout_ww.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

	}

}
