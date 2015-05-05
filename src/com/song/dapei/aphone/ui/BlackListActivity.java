package com.song.dapei.aphone.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.song.account.entity.User;
import com.song.dapei.aphone.R;
import com.song.dapei.aphone.adapter.BlackListAdapter;
import com.song.dapei.aphone.client.UserManager;
import com.song.dapei.aphone.client.db.DapeiDB;
import com.song.dapei.aphone.client.listener.ExecuteListener;
import com.song.dapei.aphone.view.HeaderLayout;
import com.song.dapei.aphone.view.dailog.DialogTips;

/**
 * 黑名单列表
 * 
 * @author songzigw
 * 
 */
public class BlackListActivity extends OnlineActivity implements
		OnItemClickListener {

	ListView listview;
	BlackListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_blacklist);
		initView();
	}

	private void initView() {
		mHeaderLayout = (HeaderLayout) findViewById(R.id.common_actionbar);
		initTopBarForLeft("黑名单");
		adapter = new BlackListAdapter(this, DapeiDB.getInstance(this)
				.getBlackList());
		listview = (ListView) findViewById(R.id.list_blacklist);
		listview.setOnItemClickListener(this);
		listview.setAdapter(adapter);
	}

	/**
	 * 显示移除黑名单对话框
	 * 
	 * @Title: showRemoveBlackDialog
	 * @Description: TODO
	 * @param @param position
	 * @param @param invite
	 * @return void
	 * @throws
	 */
	public void showRemoveBlackDialog(final int position, final User user) {
		DialogTips dialog = new DialogTips(this, "移出黑名单", "你确定将"
				+ user.getNickName() + "移出黑名单吗?", "确定", true, true);
		// 设置成功事件
		dialog.SetOnSuccessListener(new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialogInterface, int userId) {
				adapter.remove(position);
				UserManager userManager = UserManager
						.getInstance(getApplicationContext());
				userManager.removeBlack(user.getUserId(),
						new ExecuteListener() {
							@Override
							public void onSuccess(Object obj) {
								ShowToast("移出黑名单成功");
								// 重新设置下内存中保存的好友列表
								// CustomApplication.getInstance().setContactList(
								// CollectionUtils.list2map(BmobDB.create(
								// getApplicationContext())
								// .getContactList()));
							}

							@Override
							public void onFailure(String errCode,
									String errNotic) {
								ShowToast("移出黑名单失败:" + errNotic);
							}
						});
			}
		});
		// 显示确认对话框
		dialog.show();
		dialog = null;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		User invite = (User) adapter.getItem(position);
		showRemoveBlackDialog(position, invite);
	}

}
