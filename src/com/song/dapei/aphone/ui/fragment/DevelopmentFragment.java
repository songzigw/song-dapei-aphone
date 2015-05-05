package com.song.dapei.aphone.ui.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import com.song.dapei.aphone.R;
import com.song.dapei.aphone.adapter.DevelopmentAdapter;
import com.song.dapei.aphone.ui.CollocationDetailActivity;
import com.song.dapei.aphone.view.pullrefresh.PullToRefreshBase;
import com.song.dapei.aphone.view.pullrefresh.PullToRefreshBase.OnRefreshListener;
import com.song.dapei.aphone.view.pullrefresh.PullToRefreshListView;
import com.song.dapei.entity.x.XCollocation;

/**
 * 动态显示区
 * 
 * @author songzigw
 * 
 */
@SuppressLint({ "DefaultLocale", "SimpleDateFormat" })
public class DevelopmentFragment extends BaseFragment implements
		OnItemClickListener, OnItemLongClickListener {

	private ListView list_development;
	private PullToRefreshListView mPullListView;
	private DevelopmentAdapter mDevAdapter;
	private List<XCollocation> xColList = new ArrayList<XCollocation>();

	private InputMethodManager inputMethodManager;

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent(getActivity(),
				CollocationDetailActivity.class);
		intent.putExtra("from", "contact");
		startAnimActivity(intent);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater
				.inflate(R.layout.fragment_development, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		inputMethodManager = (InputMethodManager) getActivity()
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		initView();
		loadDate();
	}

	private void loadDate() {
		List<XCollocation> ml = new ArrayList<XCollocation>();
		XCollocation m1 = new XCollocation();
		ml.add(m1);

		XCollocation m2 = new XCollocation();
		ml.add(m2);

		XCollocation m3 = new XCollocation();
		ml.add(m3);

		XCollocation m4 = new XCollocation();
		ml.add(m4);

		XCollocation m5 = new XCollocation();
		ml.add(m5);

		xColList.addAll(ml);
		mDevAdapter.notifyDataSetChanged();
	}

	private void initView() {
		initTopBarForOnlyTitle("主页");

		mPullListView = (PullToRefreshListView) findViewById(R.id.list_development);
		mPullListView.setPullLoadEnabled(false);
		mPullListView.setScrollLoadEnabled(true);
		mPullListView.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				loadDate();
				setLastUpdateTime();
			}

		});
		mPullListView.doPullRefreshing(true, 500);

		list_development = mPullListView.getRefreshableView();
		mDevAdapter = new DevelopmentAdapter(this.getActivity(), xColList);
		list_development.setAdapter(mDevAdapter);
		list_development.setOnItemClickListener(this);
		list_development.setOnItemLongClickListener(this);

		list_development.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// 隐藏软键盘
				if (getActivity().getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
					if (getActivity().getCurrentFocus() != null)
						inputMethodManager.hideSoftInputFromWindow(
								getActivity().getCurrentFocus()
										.getWindowToken(),
								InputMethodManager.HIDE_NOT_ALWAYS);
				}
				return false;
			}
		});
	}

	private SimpleDateFormat mDateFormat = new SimpleDateFormat("MM-dd HH:mm");

	private void setLastUpdateTime() {
		String text = formatDateTime(System.currentTimeMillis());
		mPullListView.setLastUpdatedLabel(text);
	}

	private String formatDateTime(long time) {
		if (0 == time) {
			return "";
		}
		return mDateFormat.format(new Date(time));
	}

}
