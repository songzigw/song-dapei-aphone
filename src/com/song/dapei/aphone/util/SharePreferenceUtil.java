package com.song.dapei.aphone.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * 首选项管理
 * 
 * @author songzigw
 */
@SuppressLint("CommitPrefEdits")
public class SharePreferenceUtil {
	private SharedPreferences mSharedPreferences;
	private SharedPreferences.Editor editor;

	public SharePreferenceUtil(Context context, String name) {
		mSharedPreferences = context.getSharedPreferences(name,
				Context.MODE_PRIVATE);
		if (mSharedPreferences != null) {
			editor = mSharedPreferences.edit();
		}
	}

	public static final String SHARED_FILE_NAME = "SHARED_DAPEI";
	private static final String SHARED_KEY_NOTIFY = "shared_key_notify";
	private static final String SHARED_KEY_VOICE = "shared_key_sound";
	private static final String SHARED_KEY_VIBRATE = "shared_key_vibrate";
	private static final String SHARED_KEY_CLIENTID = "shared_key_clientid";

	// 是否允许推送通知
	public boolean isAllowPushNotify() {
		return mSharedPreferences.getBoolean(SHARED_KEY_NOTIFY, true);
	}

	public void setPushNotifyEnable(boolean isChecked) {
		editor.putBoolean(SHARED_KEY_NOTIFY, isChecked);
		editor.commit();
	}

	// 允许声音
	public boolean isAllowVoice() {
		return mSharedPreferences.getBoolean(SHARED_KEY_VOICE, true);
	}

	public void setAllowVoiceEnable(boolean isChecked) {
		editor.putBoolean(SHARED_KEY_VOICE, isChecked);
		editor.commit();
	}

	// 允许震动
	public boolean isAllowVibrate() {
		return mSharedPreferences.getBoolean(SHARED_KEY_VIBRATE, true);
	}

	public void setAllowVibrateEnable(boolean isChecked) {
		editor.putBoolean(SHARED_KEY_VIBRATE, isChecked);
		editor.commit();
	}

	public String getSessionId() {
		return mSharedPreferences.getString(SHARED_KEY_CLIENTID, null);
	}

	public void setSessionId(String sessionId) {
		editor.putString(SHARED_KEY_CLIENTID, sessionId);
		editor.commit();
	}

	public long getPullTime(String id) {
		if (mSharedPreferences == null)
			return -1;
		return mSharedPreferences.getLong(id, -1);
	}

	public void setPullTime(String id, long time) {
		editor.putLong(id, time);
		editor.commit();
	}
}