package com.song.dapei.aphone.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
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
import com.song.dapei.aphone.adapter.CollocationUserAdapter;
import com.song.dapei.entity.x.XCollocation;

/**
 * 搭配列表显示
 * 
 * @author songzigw
 * 
 */
@SuppressLint("DefaultLocale")
public class CollocationFragment extends BaseFragment implements
		OnItemClickListener, OnItemLongClickListener {

	private ListView list_collo;
	private CollocationUserAdapter mColUserAdapter;
	private List<XCollocation> xColList = new ArrayList<XCollocation>();

	private InputMethodManager inputMethodManager;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater
				.inflate(R.layout.fragment_collocation, container, false);
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
		mColUserAdapter.notifyDataSetChanged();
	}

	private void initView() {
		list_collo = (ListView) findViewById(R.id.list_collo);
		mColUserAdapter = new CollocationUserAdapter(this.getActivity(),
				xColList);
		list_collo.setAdapter(mColUserAdapter);
		list_collo.setOnItemClickListener(this);
		list_collo.setOnItemLongClickListener(this);

		list_collo.setOnTouchListener(new OnTouchListener() {
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

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

	}

}
