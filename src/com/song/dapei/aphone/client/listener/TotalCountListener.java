package com.song.dapei.aphone.client.listener;

/**
 * 获取总数任务监听
 * @author songzigw
 *
 */
public interface TotalCountListener {
	void onSuccess(int count);
	void onFailure(String errCode, String errNotic);
}
