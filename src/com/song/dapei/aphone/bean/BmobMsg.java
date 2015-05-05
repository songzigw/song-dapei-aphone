package com.song.dapei.aphone.bean;

import android.content.Context;

public class BmobMsg {

	public static final int TYPE_TEXT = 0;

	public static final int TYPE_VOICE = 1;

	public static final int TYPE_LOCATION = 1;

	public static final int TYPE_IMAGE = 3;

	public static final int STATUS_SEND_START = 0;

	public static final int STATUS_SEND_SUCCESS = 0;

	public static final int STATUS_SEND_FAIL = 0;

	public static final int STATUS_SEND_RECEIVERED = 0;

	public static final String BROADCAST_NEW_MESSAGE = "cn.bmob.new_msg";

	public String getContent() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getStatus() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getBelongId() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getConversationId() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getMsgTime() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setStatus(int statusSendReceivered) {
		// TODO Auto-generated method stub
		
	}

	public int getMsgType() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static BmobMsg createTextSendMsg(Context context,
			Long targetId, String msg) {
		// TODO Auto-generated method stub
		return null;
	}

	public static BmobMsg createLocationSendMsg(Context context,
			Long targetId, String address, double latitude, double longtitude) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getBelongAvatar() {
		// TODO Auto-generated method stub
		return null;
	}

}
