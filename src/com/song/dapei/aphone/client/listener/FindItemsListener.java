package com.song.dapei.aphone.client.listener;

import java.util.List;

/**
 * 查询列表监听
 * @author songzigw
 *
 * @param <T>
 */
public interface FindItemsListener<T> {
	static final int QUERY_LIMIT_COUNT = 18;
	void onSuccess(List<T> items, int totalCount);
	void onFailure(String errCode, String errNotic);
}
