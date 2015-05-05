package com.song.dapei.aphone.adapter;

import java.util.List;

import android.content.Context;
import android.text.SpannableString;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.song.dapei.aphone.R;
import com.song.dapei.aphone.adapter.base.BaseListAdapter;
import com.song.dapei.aphone.adapter.base.ViewHolder;
import com.song.dapei.aphone.bean.BmobMsg;
import com.song.dapei.aphone.bean.Conversation;
import com.song.dapei.aphone.util.FaceTextUtils;
import com.song.dapei.aphone.util.ImageLoadOptions;
import com.song.dapei.aphone.util.TimeUtil;

/**
 * 会话适配器
 * 
 * @author songzigw
 * 
 */
public class ConversationAdapter extends BaseListAdapter<Conversation> {

	public ConversationAdapter(Context context, List<Conversation> list) {
		super(context, list);
	}

	@Override
	public View bindView(int position, View convertView, ViewGroup parent) {
		Conversation item = this.getList().get(position);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_conversation, null);
		}
		ImageView iv_recent_avatar = ViewHolder.get(convertView,
				R.id.iv_recent_avatar);
		TextView tv_recent_name = ViewHolder.get(convertView,
				R.id.tv_recent_name);
		TextView tv_recent_msg = ViewHolder
				.get(convertView, R.id.tv_recent_msg);
		TextView tv_recent_time = ViewHolder.get(convertView,
				R.id.tv_recent_time);
		TextView tv_recent_unread = ViewHolder.get(convertView,
				R.id.tv_recent_unread);

		// 填充数据
		String avatar = item.getAvatar();
		if (avatar != null && !avatar.equals("")) {
			ImageLoader.getInstance().displayImage(avatar, iv_recent_avatar,
					ImageLoadOptions.getOptions());
		} else {
			iv_recent_avatar.setImageResource(R.drawable.head);
		}

		tv_recent_name.setText(item.getNickName());
		tv_recent_time.setText(TimeUtil.getChatTime(item.getTime()));
		// 显示内容
		if (item.getType() == BmobMsg.TYPE_TEXT) {
			SpannableString spannableString = FaceTextUtils.toSpannableString(
					mContext, item.getMessage());
			tv_recent_msg.setText(spannableString);
		} else if (item.getType() == BmobMsg.TYPE_IMAGE) {
			tv_recent_msg.setText("[图片]");
		} else if (item.getType() == BmobMsg.TYPE_LOCATION) {
			String all = item.getMessage();
			if (all != null && !all.equals("")) {// 位置类型的信息组装格式：地理位置&维度&经度
				String address = all.split("&")[0];
				tv_recent_msg.setText("[位置]" + address);
			}
		} else if (item.getType() == BmobMsg.TYPE_VOICE) {
			tv_recent_msg.setText("[语音]");
		}

		int num = 3;
		if (num > 0) {
			tv_recent_unread.setVisibility(View.VISIBLE);
			tv_recent_unread.setText(num + "");
		} else {
			tv_recent_unread.setVisibility(View.GONE);
		}
		return convertView;
	}

}
