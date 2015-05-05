package com.song.dapei.aphone.adapter;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.song.account.entity.User;
import com.song.dapei.aphone.R;
import com.song.dapei.aphone.adapter.base.BaseListAdapter;
import com.song.dapei.aphone.adapter.base.ViewHolder;
import com.song.dapei.aphone.util.ImageLoadOptions;
import com.song.dapei.aphone.util.ResPathUtil;

/**
 * 查找好友
 * 
 * @author songzigw
 *
 */
public class AddFriendAdapter extends BaseListAdapter<User> {

	public AddFriendAdapter(Context context, List<User> list) {
		super(context, list);
	}

	@Override
	public View bindView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_add_friend, null);
		}
		final User contract = getList().get(position);
		TextView name = ViewHolder.get(convertView, R.id.name);
		ImageView iv_avatar = ViewHolder.get(convertView, R.id.avatar);

		Button btn_add = ViewHolder.get(convertView, R.id.btn_add);

		String avatar = ResPathUtil.getUserAvatar(contract.getPhotoPath());

		if (avatar != null && !avatar.equals("")) {
			ImageLoader.getInstance().displayImage(avatar, iv_avatar,
					ImageLoadOptions.getOptions());
		} else {
			iv_avatar.setImageResource(R.drawable.default_head);
		}

		name.setText(contract.getNickName());
		btn_add.setText("添加");
		btn_add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final ProgressDialog progress = new ProgressDialog(mContext);
				progress.setMessage("正在添加...");
				progress.setCanceledOnTouchOutside(false);
				progress.show();
				// 发送tag请求
/*				BmobChatManager.getInstance(mContext).sendTagMessage(
						BmobConfig.TAG_ADD_CONTACT, contract.getObjectId(),
						new PushListener() {

							@Override
							public void onSuccess() {
								progress.dismiss();
								ShowToast("发送请求成功，等待对方验证!");
							}

							@Override
							public void onFailure(int arg0, final String arg1) {
								progress.dismiss();
								ShowToast("发送请求失败，请重新添加!");
								ShowLog("发送请求失败:" + arg1);
							}
						});
*/			}
		});
		return convertView;
	}

}
