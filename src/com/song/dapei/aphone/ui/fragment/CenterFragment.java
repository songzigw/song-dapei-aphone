package com.song.dapei.aphone.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.song.dapei.aphone.R;

/**
 * 发现（广场）显示区
 * 
 * @author songzigw
 * 
 */
@SuppressLint("DefaultLocale")
public class CenterFragment extends BaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater
				.inflate(R.layout.fragment_center, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();
	}

	private void initView() {
		initTopBarForOnlyTitle("发现");
	}
}
