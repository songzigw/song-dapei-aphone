package com.song.dapei.aphone.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.song.account.entity.User;
import com.song.dapei.aphone.R;
import com.song.dapei.aphone.adapter.base.BaseListAdapter;
import com.song.dapei.aphone.adapter.base.ViewHolder;
import com.song.dapei.aphone.util.ImageLoadOptions;

/**
 * 黑名单
 * 
 * @author songzigw
 */
public class BlackListAdapter extends BaseListAdapter<User> {

	public BlackListAdapter(Context context, List<User> list) {
		super(context, list);
	}

	@Override
	public View bindView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_blacklist, null);
		}
		final User contract = getList().get(position);
		TextView tv_friend_name = ViewHolder.get(convertView,
				R.id.tv_friend_name);
		ImageView iv_avatar = ViewHolder.get(convertView,
				R.id.img_friend_avatar);
		String avatar = contract.getAvatar();
		if (avatar != null && !avatar.equals("")) {
			ImageLoader.getInstance().displayImage(avatar, iv_avatar,
					ImageLoadOptions.getOptions());
		} else {
			iv_avatar.setImageResource(R.drawable.default_head);
		}
		tv_friend_name.setText(contract.getNickName());
		return convertView;
	}

}
