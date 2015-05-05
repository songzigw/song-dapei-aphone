package com.song.dapei.aphone.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.song.dapei.aphone.R;
import com.song.dapei.aphone.adapter.base.BaseListAdapter;
import com.song.dapei.entity.x.XCollocation;

/**
 * 被关注用户的动态变化
 * 
 * @author songzigw
 *
 */
public class DevelopmentAdapter extends BaseListAdapter<XCollocation> {

	public DevelopmentAdapter(Context context, List<XCollocation> list) {
		super(context, list);
	}

	@Override
	public View bindView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_collocation_home,
					null);
		}
		final XCollocation contract = getList().get(position);
		
//		TextView name = ViewHolder.get(convertView, R.id.name);
//		ImageView iv_avatar = ViewHolder.get(convertView, R.id.avatar);
//		Button btn_add = ViewHolder.get(convertView, R.id.btn_add);
//		
//		String avatar = ResPathUtil.getUserAvatar(contract.getPhotoPath());
//		if (avatar != null && !avatar.equals("")) {
//			ImageLoader.getInstance().displayImage(avatar, iv_avatar,
//					ImageLoadOptions.getOptions());
//		} else {
//			iv_avatar.setImageResource(R.drawable.default_head);
//		}
//		name.setText(contract.getNickName());
//		btn_add.setText("添加");
//		btn_add.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//			}
//		});
		
		return convertView;
	}

}
