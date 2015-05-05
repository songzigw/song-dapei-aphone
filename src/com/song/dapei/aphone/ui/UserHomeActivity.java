package com.song.dapei.aphone.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.song.dapei.aphone.R;
import com.song.dapei.aphone.ui.fragment.CollocationFragment;
import com.song.dapei.aphone.ui.fragment.FocusFragment;
import com.song.dapei.aphone.ui.fragment.FollowFragment;

/**
 * 个人主页
 * 
 * @author songzigw
 * 
 */
public class UserHomeActivity extends BaseActivity implements OnClickListener {

	private RelativeLayout layout_head;
	private LinearLayout layout_dpcount, layout_gzcount, layout_fscount;

	private TextView tv_set_nickname, tv_set_summary, tv_set_dpcount,
			tv_set_gzcount, tv_set_fscount;

	private Button btn_guanzhu, btn_sendmsg;

	private CollocationFragment mColloFragment;
	private FollowFragment mFollowFragment;
	private FocusFragment mFocusFragment;

	private ScrollView sv_layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_home);
		initView();
		initTab();
		loadDate();
	}

	private void loadDate() {
		// TODO Auto-generated method stub

	}

	private void initTab() {
		mColloFragment = new CollocationFragment();
		mFollowFragment = new FollowFragment();
		mFocusFragment = new FocusFragment();

		// 加载用户的搭配列表
		getSupportFragmentManager().beginTransaction()
				.add(R.id.fragment_container, mColloFragment)
				.show(mColloFragment).commit();
		sv_layout.smoothScrollTo(0, 0);
	}

	private void initView() {
		initTopBarForLeft("个人主页");
		layout_head = (RelativeLayout) findViewById(R.id.layout_head);
		layout_dpcount = (LinearLayout) findViewById(R.id.layout_dpcount);
		layout_gzcount = (LinearLayout) findViewById(R.id.layout_gzcount);
		layout_fscount = (LinearLayout) findViewById(R.id.layout_fscount);

		layout_head.setOnClickListener(this);
		layout_dpcount.setOnClickListener(this);
		layout_gzcount.setOnClickListener(this);
		layout_fscount.setOnClickListener(this);

		tv_set_nickname = (TextView) findViewById(R.id.tv_set_nickname);
		tv_set_summary = (TextView) findViewById(R.id.tv_set_summary);
		tv_set_dpcount = (TextView) findViewById(R.id.tv_set_dpcount);
		tv_set_gzcount = (TextView) findViewById(R.id.tv_set_gzcount);
		tv_set_fscount = (TextView) findViewById(R.id.tv_set_fscount);

		btn_guanzhu = (Button) findViewById(R.id.btn_guanzhu);
		btn_sendmsg = (Button) findViewById(R.id.btn_sendmsg);

		sv_layout = (ScrollView) findViewById(R.id.sv_layout);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layout_head:
			startActivity(new Intent(this, UserInfoActivity.class));
			break;
		case R.id.layout_dpcount:
			showDpView();
			break;
		case R.id.layout_gzcount:
			showGzView();
			break;
		case R.id.layout_fscount:
			showFsView();
			break;
		default:
			break;
		}
	}

	private void showDpView() {
		FragmentTransaction ftn = this.getSupportFragmentManager()
				.beginTransaction();
		ftn.hide(mColloFragment);
		ftn.hide(mFollowFragment);
		ftn.hide(mFocusFragment);

		if (!mColloFragment.isAdded()) {
			ftn.add(R.id.fragment_container, mColloFragment);
		}

		ftn.show(mColloFragment).commit();
		sv_layout.smoothScrollTo(0, 0);
	}

	private void showGzView() {
		FragmentTransaction ftn = this.getSupportFragmentManager()
				.beginTransaction();
		ftn.hide(mColloFragment);
		ftn.hide(mFollowFragment);
		ftn.hide(mFocusFragment);

		if (!mFocusFragment.isAdded()) {
			ftn.add(R.id.fragment_container, mFocusFragment);
		}

		ftn.show(mFocusFragment).commit();
	}

	private void showFsView() {
		FragmentTransaction ftn = this.getSupportFragmentManager()
				.beginTransaction();
		ftn.hide(mColloFragment);
		ftn.hide(mFollowFragment);
		ftn.hide(mFocusFragment);

		if (!mFollowFragment.isAdded()) {
			ftn.add(R.id.fragment_container, mFollowFragment);
		}

		ftn.show(mFollowFragment).commit();
	}
}
