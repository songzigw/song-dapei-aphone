package com.song.dapei.aphone.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.song.account.entity.User;
import com.song.dapei.aphone.R;
import com.song.dapei.aphone.adapter.AddFriendAdapter;
import com.song.dapei.aphone.client.UserManager;
import com.song.dapei.aphone.client.listener.FindItemsListener;
import com.song.dapei.aphone.util.CollectionUtils;

/**
 * 添加好友
 * 
 * @author songzigw
 * 
 */
public class AddFriendActivity extends OnlineActivity implements
		OnClickListener, OnItemClickListener, OnRefreshListener2<ListView>,
		OnLastItemVisibleListener {

	UserManager userManager;
	EditText et_find_name;
	Button btn_search;

	List<User> users = new ArrayList<User>();
	PullToRefreshListView mListView;
	AddFriendAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_contact);
		userManager = UserManager.getInstance(this);
		initView();
	}

	private void initView() {
		initTopBarForLeft("查找好友");
		et_find_name = (EditText) findViewById(R.id.et_find_name);
		btn_search = (Button) findViewById(R.id.btn_search);
		btn_search.setOnClickListener(this);
		initListView();
	}

	private void initListView() {
		mListView = (PullToRefreshListView) findViewById(R.id.list_search);

		adapter = new AddFriendAdapter(this, users);
		mListView.setAdapter(adapter);

		mListView.setOnItemClickListener(this);
		mListView.setOnRefreshListener(this);
		mListView.setOnLastItemVisibleListener(this);
		mListView.setMode(Mode.BOTH);
	}

	private int currPage = 1;
	private int pageSize = 10;
	private int totalNum = -1;
	private ProgressDialog progress;

	private void initSearchList(final boolean isUpdate) {
		if (isUpdate) {
			currPage = 1;
			totalNum = -1;
			users.clear();
			progress = new ProgressDialog(this);
			progress.setMessage("正在搜索...");
			progress.setCanceledOnTouchOutside(true);
			progress.show();
		} else {
			if (totalNum != -1 && adapter.getCount() >= totalNum) {
				stopPullToRefresh();
				ShowToast("用户加载完毕!");
				return;
			}
		}

		userManager.queryUserByPage(currPage, pageSize, searchName,
				new FindItemsListener<User>() {
					@Override
					public void onSuccess(List<User> items, int totalCount) {
						if (CollectionUtils.isNotNull(items)) {
							currPage++;
							adapter.addAll(items);
							notifyDataChanged();
							if (adapter.getCount() >= totalCount) {
								totalNum = totalCount;
							}
						} else {
							if (isUpdate) {
								notifyDataChanged();
							}
							ShowToast("用户不存在");
						}
						if (isUpdate) {
							progress.dismiss();
						}
						stopPullToRefresh();
					}

					@Override
					public void onFailure(String errCode, String errNotic) {
						ShowToast(errNotic);
						if (isUpdate) {
							progress.dismiss();
						}
						stopPullToRefresh();
					}
				});
	}
	
	private void stopPullToRefresh() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				mListView.onRefreshComplete();
			}
		});
	}
	
	private void notifyDataChanged() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				adapter.notifyDataSetChanged();
			}
		});
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		User user = (User) adapter.getItem(position - 1);
		Intent intent = new Intent(this, UserHomeActivity.class);
		intent.putExtra("from", "add");
		intent.putExtra("username", user.getNickName());
		startAnimActivity(intent);
	}

	String searchName = "";

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_search:
			searchName = et_find_name.getText().toString();
			if (!TextUtils.isEmpty(searchName)) {
				initSearchList(true);
			} else {
				ShowToast("请输入用户名");
			}
			break;

		default:
			break;
		}
	}

	@Override
	public void onLastItemVisible() {
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		initSearchList(true);
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		initSearchList(false);
	}
}
