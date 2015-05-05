package com.song.dapei.aphone.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.song.dapei.aphone.R;
import com.song.dapei.aphone.ui.LoginActivity;

/**
 * 未登入显示区
 * 
 * @author songzigw
 * 
 */
@SuppressLint("DefaultLocale")
public class LoginNotFragment extends BaseFragment implements OnClickListener {

	private String title = "请先登录";
	private Button btn_login;

	public void setTitle(String title) {
		initTopBarForOnlyTitle(title);
		this.title = title;

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_login_not, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();
	}

	private void initView() {
		initTopBarForOnlyTitle(title);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_login.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == btn_login) {
			Intent intent = new Intent(getActivity(), LoginActivity.class);
			startActivity(intent);
		}
	}
}
