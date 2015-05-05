package com.song.dapei.aphone.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.song.dapei.aphone.R;

/**
 * 粉丝（跟随者）显示
 * 
 * @author songzigw
 * 
 */
@SuppressLint("DefaultLocale")
public class FollowFragment extends BaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater
				.inflate(R.layout.fragment_collocation, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();
	}

	private void initView() {
		// initTopBarForOnlyTitle("发现");
	}
}
