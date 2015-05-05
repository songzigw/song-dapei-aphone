package com.song.dapei.aphone.ui;

import android.os.Bundle;

import com.song.dapei.aphone.R;

/**
 * 搭配详情
 * 
 * @author songzigw
 * 
 */
public class CollocationDetailActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_collocation_detail);
		initView();
	}

	private void initView() {
		initTopBarForLeft("搭配详情");
	}

}
