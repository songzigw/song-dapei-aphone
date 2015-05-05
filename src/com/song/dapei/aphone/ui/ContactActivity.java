package com.song.dapei.aphone.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.song.account.entity.User;
import com.song.dapei.aphone.R;
import com.song.dapei.aphone.adapter.UserFriendAdapter;
import com.song.dapei.aphone.bean.XUser;
import com.song.dapei.aphone.client.ErrorCode;
import com.song.dapei.aphone.client.UserManager;
import com.song.dapei.aphone.client.db.DapeiDB;
import com.song.dapei.aphone.client.listener.ExecuteListener;
import com.song.dapei.aphone.client.listener.FindItemsListener;
import com.song.dapei.aphone.util.CharacterParser;
import com.song.dapei.aphone.util.CollectionUtils;
import com.song.dapei.aphone.util.PinyinComparator;
import com.song.dapei.aphone.view.ClearEditText;
import com.song.dapei.aphone.view.HeaderLayout.onRightImageButtonClickListener;
import com.song.dapei.aphone.view.MyLetterView;
import com.song.dapei.aphone.view.MyLetterView.OnTouchingLetterChangedListener;
import com.song.dapei.aphone.view.dailog.DialogTips;

/**
 * 联系人（好友）
 * 
 * @author songzigw
 * 
 */
@SuppressLint("DefaultLocale")
public class ContactActivity extends OnlineActivity implements
		OnItemClickListener, OnItemLongClickListener {

	private ClearEditText mClearEditText;

	private TextView dialog;

	private ListView friend_list;
	private MyLetterView right_letter;

	/** 好友 */
	private UserFriendAdapter userAdapter;

	private List<XUser> friends = new ArrayList<XUser>();

	private InputMethodManager inputMethodManager;
	private LayoutInflater mInflater;

	/**
	 * 汉字转换成拼音的类
	 */
	private CharacterParser characterParser;
	/**
	 * 根据拼音来排列ListView里面的数据类
	 */
	private PinyinComparator pinyinComparator;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contacts);
		inputMethodManager = (InputMethodManager) this
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		mInflater = LayoutInflater.from(this);
		init();
	}

	private void init() {
		characterParser = CharacterParser.getInstance();
		pinyinComparator = new PinyinComparator();
		initTopBarForBoth("我的好友", R.drawable.base_action_bar_add_bg_selector,
				new onRightImageButtonClickListener() {
					@Override
					public void onClick() {
						startAnimActivity(AddFriendActivity.class);
					}
				});

		initListView();
		initRightLetterView();
		initEditText();
		loadData();
	}

	private ImageView iv_msg_tips;
	private LinearLayout layout_new;// 新朋友
	private LinearLayout layout_near;// 附近的人

	private void initListView() {
		RelativeLayout headView = (RelativeLayout) mInflater.inflate(
				R.layout.include_new_friend, null);

		iv_msg_tips = (ImageView) headView.findViewById(R.id.iv_msg_tips);
		layout_new = (LinearLayout) headView.findViewById(R.id.layout_new);
		layout_near = (LinearLayout) headView.findViewById(R.id.layout_near);

		layout_new.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(ContactActivity.this,
						NewFriendActivity.class);
				intent.putExtra("from", "contact");
				startAnimActivity(intent);
			}
		});
		layout_near.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(ContactActivity.this,
						NearPeopleActivity.class);
				startAnimActivity(intent);
			}
		});

		friend_list = (ListView) findViewById(R.id.friend_list);
		friend_list.addHeaderView(headView);
		userAdapter = new UserFriendAdapter(ContactActivity.this, friends);
		friend_list.setAdapter(userAdapter);
		friend_list.setOnItemClickListener(this);
		friend_list.setOnItemLongClickListener(this);
		friend_list.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// 隐藏软键盘
				if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
					if (getCurrentFocus() != null)
						inputMethodManager.hideSoftInputFromWindow(
								getCurrentFocus().getWindowToken(),
								InputMethodManager.HIDE_NOT_ALWAYS);
				}
				return false;
			}
		});

	}

	private void initRightLetterView() {
		right_letter = (MyLetterView) findViewById(R.id.right_letter);
		dialog = (TextView) findViewById(R.id.dialog);
		right_letter.setTextView(dialog);
		right_letter
				.setOnTouchingLetterChangedListener(new LetterListViewListener());
	}

	private void initEditText() {
		mClearEditText = (ClearEditText) findViewById(R.id.et_msg_search);
		// 根据输入框输入值的改变来过滤搜索
		mClearEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
				filterData(s.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
	}

	/**
	 * 根据输入框中的值来过滤数据并更新ListView
	 * 
	 * @param filterStr
	 */
	private void filterData(String filterStr) {
		List<XUser> filterDateList = new ArrayList<XUser>();
		if (TextUtils.isEmpty(filterStr)) {
			filterDateList = friends;
		} else {
			filterDateList.clear();
			for (XUser sortModel : friends) {
				String name = sortModel.getNickName();
				if (name != null) {
					if (name.indexOf(filterStr.toString()) != -1
							|| characterParser.getSelling(name).startsWith(
									filterStr.toString())) {
						filterDateList.add(sortModel);
					}
				}
			}
		}
		// 根据a-z进行排序
		Collections.sort(filterDateList, pinyinComparator);
		userAdapter.updateListView(filterDateList);
	}

	/**
	 * 为ListView填充数据
	 * 
	 * @param date
	 * @return
	 */
	private void filledData(List<User> datas) {
		friends.clear();
		int total = datas.size();
		for (int i = 0; i < total; i++) {
			User user = datas.get(i);
			XUser sortModel = new XUser();
			sortModel.setPhotoPath(user.getPhotoPath());
			sortModel.setNickName(user.getNickName());
			sortModel.setUserName(user.getUserName());
			sortModel.setUserId(user.getUserId());
			// sortModel.setContacts(user.getContacts());
			// 汉字转换成拼音
			String nickName = sortModel.getNickName();
			// 若没有nickName
			if (nickName != null) {
				String pinyin = characterParser.getSelling(sortModel
						.getNickName());
				String sortString = pinyin.substring(0, 1).toUpperCase();
				// 正则表达式，判断首字母是否是英文字母
				if (sortString.matches("[A-Z]")) {
					sortModel.setSortLetters(sortString.toUpperCase());
				} else {
					sortModel.setSortLetters("#");
				}
			} else {
				sortModel.setSortLetters("#");
			}
			friends.add(sortModel);
		}
		// 根据a-z进行排序
		Collections.sort(friends, pinyinComparator);
	}

	private class LetterListViewListener implements
			OnTouchingLetterChangedListener {

		@Override
		public void onTouchingLetterChanged(String s) {
			// 该字母首次出现的位置
			int position = userAdapter.getPositionForSection(s.charAt(0));
			if (position != -1) {
				friend_list.setSelection(position);
			}
		}
	}

	private void loadData() {
		final DapeiDB uDB = DapeiDB.getInstance(this);
		// 是否有新的好友请求
		if (DapeiDB.getInstance(this).hasNewInvite()) {
			iv_msg_tips.setVisibility(View.VISIBLE);
		} else {
			iv_msg_tips.setVisibility(View.GONE);
		}

		UserManager.getInstance(this).getContactList(
				new FindItemsListener<User>() {
					@Override
					public void onSuccess(List<User> items, int totalCount) {
						Map<String, User> users = CollectionUtils
								.list2map(items);
						// 组装新的User
						filledData(CollectionUtils.map2list(users));
						notifyDataChanged();
					}

					@Override
					public void onFailure(String errCode, String errNotic) {
						if (ErrorCode.GEN_004.equals(errCode)) {
							Map<String, User> users = CollectionUtils
									.list2map(uDB.getContactList());
							// 组装新的User
							filledData(CollectionUtils.map2list(users));
							notifyDataChanged();
						}
						ShowToast(errNotic);
					}
				});
	}

	private void notifyDataChanged() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				userAdapter.notifyDataSetChanged();
			}
		});
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		XUser user = (XUser) userAdapter.getItem(position - 1);
		// 先进入好友的详细资料页面
		Intent intent = new Intent(ContactActivity.this, UserHomeActivity.class);
		intent.putExtra("from", "other");
		intent.putExtra("nickName", user.getNickName());
		startAnimActivity(intent);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		XUser user = (XUser) userAdapter.getItem(position - 1);
		showDeleteDialog(user);
		return true;
	}

	public void showDeleteDialog(final XUser user) {
		DialogTips dialog = new DialogTips(this, user.getNickName(), "删除联系人",
				"确定", true, true);
		// 设置成功事件
		dialog.SetOnSuccessListener(new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialogInterface, int userId) {
				deleteContact(user);
			}
		});
		// 显示确认对话框
		dialog.show();
		dialog = null;
	}

	/**
	 * 删除联系人
	 * 
	 * @param user
	 */
	private void deleteContact(final XUser user) {
		final ProgressDialog progress = new ProgressDialog(this);
		progress.setMessage("正在删除...");
		progress.setCanceledOnTouchOutside(false);
		progress.show();
		UserManager userManager = UserManager.getInstance(this);
		userManager.deleteContact(user.getUserId(), new ExecuteListener() {

			@Override
			public void onSuccess(Object obj) {
				ShowToast("删除成功");
				// 更新界面
				runOnUiThread(new Runnable() {
					public void run() {
						progress.dismiss();
						userAdapter.remove(user);
					}
				});
			}

			@Override
			public void onFailure(String errCode, String errNotic) {
				ShowToast("删除失败：" + errNotic);
				progress.dismiss();
			}
		});
	}

}