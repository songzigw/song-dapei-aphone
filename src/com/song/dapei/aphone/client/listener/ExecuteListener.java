package com.song.dapei.aphone.client.listener;

/**
 * 任务执行监听
 * @author songzigw
 *
 */
public interface ExecuteListener {
	void onSuccess(Object obj);
	void onFailure(String errCode, String errNotic);
}
