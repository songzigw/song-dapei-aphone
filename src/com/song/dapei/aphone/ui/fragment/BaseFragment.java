package com.song.dapei.aphone.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.song.dapei.aphone.CustomApplication;
import com.song.dapei.aphone.R;
import com.song.dapei.aphone.view.HeaderLayout;
import com.song.dapei.aphone.view.HeaderLayout.HeaderStyle;
import com.song.dapei.aphone.view.HeaderLayout.onLeftImageButtonClickListener;
import com.song.dapei.aphone.view.HeaderLayout.onRightImageButtonClickListener;

/**
 * Fragmenet 基类
 * 
 * @author songzigw
 *
 */
public abstract class BaseFragment extends Fragment {

	/**
	 * 公用的Header布局
	 */
	public HeaderLayout mHeaderLayout;

	protected View contentView;

	public LayoutInflater mInflater;

	private Handler handler = new Handler();

	public void runOnWorkThread(Runnable action) {
		new Thread(action).start();
	}

	public void runOnUiThread(Runnable action) {
		handler.post(action);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		mApplication = CustomApplication.getInstance();
		mInflater = LayoutInflater.from(getActivity());
	}

	Toast mToast;

	public void ShowToast(String text) {
		if (mToast == null) {
			mToast = Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT);
		} else {
			mToast.setText(text);
		}
		mToast.show();
	}

	public void ShowToast(int text) {
		if (mToast == null) {
			mToast = Toast.makeText(getActivity(), text, Toast.LENGTH_LONG);
		} else {
			mToast.setText(text);
		}
		mToast.show();
	}

	public View findViewById(int paramInt) {
		return getView().findViewById(paramInt);
	}

	public CustomApplication mApplication;

	/**
	 * 只有title initTopBarLayoutByTitle
	 * 
	 * @Title: initTopBarLayoutByTitle
	 * @throws
	 */
	public void initTopBarForOnlyTitle(String titleName) {
		mHeaderLayout = (HeaderLayout) findViewById(R.id.common_actionbar);
		mHeaderLayout.init(HeaderStyle.DEFAULT_TITLE);
		mHeaderLayout.setDefaultTitle(titleName);
	}

	/**
	 * 初始化标题栏-带左右按钮
	 * 
	 * @return void
	 * @throws
	 */
	public void initTopBarForBoth(String titleName, int rightDrawableId,
			onRightImageButtonClickListener listener) {
		mHeaderLayout = (HeaderLayout) findViewById(R.id.common_actionbar);
		mHeaderLayout.init(HeaderStyle.TITLE_DOUBLE_IMAGEBUTTON);
		mHeaderLayout.setTitleAndLeftImageButton(titleName,
				R.drawable.base_action_bar_back_bg_selector,
				new OnLeftButtonClickListener());
		mHeaderLayout.setTitleAndRightImageButton(titleName, rightDrawableId,
				listener);
	}

	/**
	 * 只有左边按钮和Title initTopBarLayout
	 * 
	 * @throws
	 */
	public void initTopBarForLeft(String titleName) {
		mHeaderLayout = (HeaderLayout) findViewById(R.id.common_actionbar);
		mHeaderLayout.init(HeaderStyle.TITLE_LIFT_IMAGEBUTTON);
		mHeaderLayout.setTitleAndLeftImageButton(titleName,
				R.drawable.base_action_bar_back_bg_selector,
				new OnLeftButtonClickListener());
	}

	/**
	 * 右边+title initTopBarForRight
	 * 
	 * @return void
	 * @throws
	 */
	public void initTopBarForRight(String titleName, int rightDrawableId,
			onRightImageButtonClickListener listener) {
		mHeaderLayout = (HeaderLayout) findViewById(R.id.common_actionbar);
		mHeaderLayout.init(HeaderStyle.TITLE_RIGHT_IMAGEBUTTON);
		mHeaderLayout.setTitleAndRightImageButton(titleName, rightDrawableId,
				listener);
	}

	// 左边按钮的点击事件
	public class OnLeftButtonClickListener implements
			onLeftImageButtonClickListener {

		@Override
		public void onClick() {
			getActivity().finish();
		}
	}

	/**
	 * 动画启动页面 startAnimActivity
	 * 
	 * @throws
	 */
	public void startAnimActivity(Intent intent) {
		this.startActivity(intent);
	}

	public void startAnimActivity(Class<?> cla) {
		getActivity().startActivity(new Intent(getActivity(), cla));
	}

}
