package com.song.dapei.aphone.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.song.dapei.aphone.R;
import com.song.dapei.aphone.client.SSOAuth;
import com.song.dapei.aphone.ui.fragment.CenterFragment;
import com.song.dapei.aphone.ui.fragment.DevelopmentFragment;
import com.song.dapei.aphone.ui.fragment.LoginNotFragment;
import com.song.dapei.aphone.ui.fragment.MessageFragment;
import com.song.dapei.aphone.ui.fragment.MyInfoFragment;

public class MainActivity extends BaseActivity implements OnClickListener {

	private Button[] mTabs;
	private Button mTabHome, mTabCenter, mTabMessage, mTabMy;
	private Button mTabTake;
	private int index;
	private int currentTabIndex;

	private Fragment[] fragments;
	private DevelopmentFragment develFragment;
	private CenterFragment centerFragment;
	private MessageFragment messageFragment;
	private MyInfoFragment myInfoFragment;

	private LoginNotFragment loginNotFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();
		initTab();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private static long firstTime;

	/**
	 * 连续按两次返回键就退出
	 */
	@Override
	public void onBackPressed() {
		if (firstTime + 2000 > System.currentTimeMillis()) {
			super.onBackPressed();
		} else {
			ShowToast("再按一次退出程序");
		}
		firstTime = System.currentTimeMillis();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		try {
			// unregisterReceiver(newReceiver);
		} catch (Exception e) {
		}
		try {
			// unregisterReceiver(userReceiver);
		} catch (Exception e) {
		}
		// 取消定时检测服务
		// BmobChat.getInstance(this).stopPollService();
	}

	private void initView() {
		mTabs = new Button[4];
		mTabHome = (Button) findViewById(R.id.btn_home);
		mTabCenter = (Button) findViewById(R.id.btn_center);
		mTabMessage = (Button) findViewById(R.id.btn_message);
		mTabMy = (Button) findViewById(R.id.btn_my);
		mTabs[0] = mTabHome;
		mTabs[1] = mTabCenter;
		mTabs[2] = mTabMessage;
		mTabs[3] = mTabMy;
		mTabTake = (Button) findViewById(R.id.btn_take);

		// 把第一个tab设置为选中状态
		index = 0;
		currentTabIndex = index;
		mTabHome.setSelected(true);
		
		mTabHome.setOnClickListener(this);
		mTabCenter.setOnClickListener(this);
		mTabMessage.setOnClickListener(this);
		mTabMy.setOnClickListener(this);
		mTabTake.setOnClickListener(this);
	}

	private void initTab() {
		fragments = new Fragment[4];
		develFragment = new DevelopmentFragment();
		centerFragment = new CenterFragment();
		messageFragment = new MessageFragment();
		myInfoFragment = new MyInfoFragment();

		loginNotFragment = new LoginNotFragment();

		fragments = new Fragment[] { develFragment, centerFragment,
				messageFragment, myInfoFragment };
		// 添加显示第一个fragment
//		getSupportFragmentManager().beginTransaction()
//				.add(R.id.fragment_container, develFragment)
//				.show(develFragment).commit();
	}

	/**
	 * button点击事件
	 * 
	 * @param view
	 */
	public void onTabSelect(View view) {
		switch (view.getId()) {
		case R.id.btn_home:
			index = 0;
			break;
		case R.id.btn_center:
			index = 1;
			break;
		case R.id.btn_take:

			break;
		case R.id.btn_message:
			index = 2;
			break;
		case R.id.btn_my:
			index = 3;
			break;
		}

		if (currentTabIndex != index) {
			changeFragment();
		}
	}

	@Override
	public void onClick(View view) {
		this.onTabSelect(view);
	}

	public void changeFragment() {
		FragmentTransaction trx = getSupportFragmentManager()
				.beginTransaction();
		trx.hide(loginNotFragment);
		trx.hide(fragments[currentTabIndex]);
		// 将之前tab设为未选中状态
		mTabs[currentTabIndex].setSelected(false);
		// 更新当前tab值
		currentTabIndex = index;
		// 把当前tab设为选中状态
		mTabs[currentTabIndex].setSelected(true);

		if (currentTabIndex != 0 && currentTabIndex != 1) {
			SSOAuth ssoAuth = SSOAuth.getInstance(this);
			if (ssoAuth.getCurrUser() == null) {
				if (!loginNotFragment.isAdded()) {
					trx.add(R.id.fragment_container, loginNotFragment);
				}
				trx.show(loginNotFragment).commit();
				return;
			}
		}

		if (!fragments[currentTabIndex].isAdded()) {
			trx.add(R.id.fragment_container, fragments[currentTabIndex]);
		}
		trx.show(fragments[currentTabIndex]).commit();
	}

	@Override
	protected void onResume() {
		super.onRestart();
		changeFragment();
	}
}
