package com.song.dapei.aphone.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.song.dapei.aphone.R;
import com.song.dapei.aphone.ui.BlackListActivity;
import com.song.dapei.aphone.ui.ContactActivity;
import com.song.dapei.aphone.ui.MySettingsActivity;
import com.song.dapei.aphone.ui.MyselfInfoActivity;
import com.song.dapei.aphone.ui.UserHomeActivity;

/**
 * 我的（信息）显示区
 * 
 * @author songzigw
 * 
 */
public class MyInfoFragment extends BaseFragment implements OnClickListener {

	private RelativeLayout layout_head, layout_my_info, layout_my_friend,
			layout_my_blacklist, layout_my_settings;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_my_info, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();
	}

	private void initView() {
		initTopBarForOnlyTitle("我的");
		layout_my_settings = (RelativeLayout) findViewById(R.id.layout_my_settings);
		layout_head = (RelativeLayout) findViewById(R.id.layout_head);
		layout_my_info = (RelativeLayout) findViewById(R.id.layout_my_info);
		layout_my_friend = (RelativeLayout) findViewById(R.id.layout_my_friend);
		layout_my_blacklist = (RelativeLayout) findViewById(R.id.layout_my_blacklist);

		layout_my_settings.setOnClickListener(this);
		layout_head.setOnClickListener(this);
		layout_my_info.setOnClickListener(this);
		layout_my_friend.setOnClickListener(this);
		layout_my_blacklist.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layout_my_settings:
			startAnimActivity(new Intent(getActivity(),
					MySettingsActivity.class));
			break;
		case R.id.layout_head:
			startActivity(new Intent(getActivity(), UserHomeActivity.class));
			break;
		case R.id.layout_my_info:
			startActivity(new Intent(getActivity(), MyselfInfoActivity.class));
			break;
		case R.id.layout_my_friend:
			startActivity(new Intent(getActivity(), ContactActivity.class));
			break;
		case R.id.layout_my_blacklist:
			startActivity(new Intent(getActivity(), BlackListActivity.class));
			break;
		default:
			break;
		}
	}
}
