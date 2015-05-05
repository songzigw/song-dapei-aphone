package com.song.dapei.aphone.ui.fragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.song.dapei.aphone.R;
import com.song.dapei.aphone.adapter.ConversationAdapter;
import com.song.dapei.aphone.bean.BmobMsg;
import com.song.dapei.aphone.bean.Conversation;
import com.song.dapei.aphone.ui.NewFriendActivity;
import com.song.dapei.aphone.view.dailog.DialogTips;

/**
 * 消息（信息）显示区
 * 
 * @author songzigw
 * 
 */
@SuppressLint("DefaultLocale")
public class MessageFragment extends BaseFragment implements
		OnItemClickListener, OnItemLongClickListener {

	private ListView list_message;
	// 新朋友
	private RelativeLayout layout_new;
	private RelativeLayout layout_at_my;
	private RelativeLayout layout_comment_my;
	private RelativeLayout layout_praise_my;
	private List<Conversation> messageList = new ArrayList<Conversation>();
	private ConversationAdapter converAdapter;

	private InputMethodManager inputMethodManager;

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		Conversation conve = (Conversation) converAdapter.getItem(position - 1);
		showDeleteDialog(conve);
		return true;
	}

	public void showDeleteDialog(final Conversation conve) {
		DialogTips dialog = new DialogTips(this.getActivity(),
				conve.getNickName(), "删除会话", "确定", true, true);
		// 设置成功事件
		dialog.SetOnSuccessListener(new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialogInterface, int userId) {

			}
		});
		// 显示确认对话框
		dialog.show();
		dialog = null;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// Conversation conve = (Conversation) converAdapter.getItem(position -
		// 1);
		// 进入对话页面

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_message, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		inputMethodManager = (InputMethodManager) getActivity()
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		initView();
		initDate();
	}

	private void initView() {
		initTopBarForOnlyTitle("消息");

		list_message = (ListView) findViewById(R.id.list_message);
		LinearLayout headView = (LinearLayout) mInflater.inflate(
				R.layout.include_new_message, null);
		layout_new = (RelativeLayout) headView.findViewById(R.id.layout_new);
		layout_at_my = (RelativeLayout) headView
				.findViewById(R.id.layout_at_my);
		layout_comment_my = (RelativeLayout) headView
				.findViewById(R.id.layout_comment_my);
		layout_praise_my = (RelativeLayout) headView
				.findViewById(R.id.layout_praise_my);
		layout_new.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getActivity(),
						NewFriendActivity.class);
				intent.putExtra("from", "contact");
				startAnimActivity(intent);
			}
		});
		layout_at_my.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {

			}
		});
		layout_comment_my.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {

			}
		});
		layout_praise_my.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {

			}
		});

		list_message.addHeaderView(headView);
		converAdapter = new ConversationAdapter(getActivity(), messageList);
		list_message.setAdapter(converAdapter);
		list_message.setOnItemClickListener(this);
		list_message.setOnItemLongClickListener(this);

		list_message.setOnTouchListener(new OnTouchListener() {

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

	private void initDate() {
		List<Conversation> ml = new ArrayList<Conversation>();
		Date d = new Date();
		Conversation m1 = new Conversation();
		m1.setAvatar("http://avatar.csdn.net/3/B/3/1_chenfuduo_loveit.jpg");
		m1.setTime(d.getTime());
		m1.setMessage("dss");
		m1.setNickName("zhangsong");
		m1.setType(BmobMsg.TYPE_IMAGE);
		ml.add(m1);

		Conversation m2 = new Conversation();
		m2.setAvatar("https://avatars2.githubusercontent.com/u/9962941?v=3&amp;s=460");
		m2.setTime(d.getTime());
		m2.setMessage("dss");
		m2.setNickName("zhangsong");
		m2.setType(BmobMsg.TYPE_LOCATION);
		ml.add(m2);

		Conversation m3 = new Conversation();
		m3.setAvatar("http://avatar.csdn.net/B/4/1/1_jianghuiquan.jpg");
		m3.setTime(d.getTime());
		m3.setMessage("dss");
		m3.setNickName("zhangsong");
		m3.setType(BmobMsg.TYPE_TEXT);
		ml.add(m3);

		Conversation m4 = new Conversation();
		m4.setAvatar("http://avatar.csdn.net/3/5/2/1_bboyfeiyu.jpg");
		m4.setTime(d.getTime());
		m4.setMessage("dss");
		m4.setNickName("zhangsong");
		m4.setType(BmobMsg.TYPE_VOICE);
		ml.add(m4);

		Conversation m5 = new Conversation();
		m5.setAvatar("http://avatar.csdn.net/6/5/B/1_neil3d.jpg");
		m5.setTime(d.getTime());
		m5.setMessage("dss");
		m5.setNickName("zhangsong");
		m5.setType(BmobMsg.TYPE_IMAGE);
		ml.add(m5);

		messageList.addAll(ml);
		converAdapter.notifyDataSetChanged();
	}

	private boolean hidden;

	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		this.hidden = hidden;
		if (!hidden) {
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		if (!hidden) {
		}
	}

}
