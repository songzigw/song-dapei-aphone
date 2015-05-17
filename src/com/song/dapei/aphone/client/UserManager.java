package com.song.dapei.aphone.client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.song.account.api.ClientAccount;
import com.song.account.api.result.FriendItems;
import com.song.account.api.result.UserPages;
import com.song.account.entity.Friend;
import com.song.account.entity.User;
import com.song.commons.api.ApiException;
import com.song.dapei.aphone.client.db.DapeiDB;
import com.song.dapei.aphone.client.listener.ExecuteListener;
import com.song.dapei.aphone.client.listener.FindItemsListener;
import com.song.dapei.aphone.util.CommonUtils;

/**
 * 用户业务服务
 * 
 * @author songzigw
 * 
 */
public class UserManager {

	private static UserManager mInstance;

	private Context context;

	private UserManager(Context context) {
		if (this.context == null) {
			this.context = context;
		}
	}

	public static UserManager getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new UserManager(context);
		}
		return mInstance;
	}

	private void runOnWorkThread(Runnable action) {
		new Thread(action).start();
	}

	/**
	 * 联系人（好友）列表
	 * 
	 * @param findItemsListener
	 */
	public void getContactList(final FindItemsListener<User> findItemsListener) {
		boolean isNetConnected = CommonUtils.isNetworkAvailable(context);
		if (!isNetConnected) {
			findItemsListener.onFailure(ErrorCode.GEN_004, "当前网络不可用,请检查您的网络!");
			return;
		}

		Runnable action = new Runnable() {
			@Override
			public void run() {
				List<User> uList = new ArrayList<User>();
				try {
					String sessionId = SSOAuth.getInstance(context)
							.getSessionId();
					FriendItems friendItems = ClientAccount.getInstance()
							.getFriendList(sessionId);
					List<Friend> fList = friendItems.getFriendList();
					if (fList != null) {
						for (Friend f : fList) {
							Long userId = f.getFriendUserId();
							User u = ClientAccount.getInstance().getUserById(
									userId);
							uList.add(u);
						}
					}
					// 网络查询成功
					findItemsListener.onSuccess(uList, uList.size());
					// 加载本地数据库
					DapeiDB.getInstance(context).setContactList(uList);
				} catch (MalformedURLException e) {
					findItemsListener.onFailure(ErrorCode.GEN_004, "网络异常");
				} catch (ProtocolException e) {
					findItemsListener.onFailure(ErrorCode.GEN_004, "网络异常");
				} catch (IOException e) {
					findItemsListener.onFailure(ErrorCode.GEN_004, "网络异常");
				} catch (ApiException e) {
					findItemsListener.onFailure(e.getErrCode(),
							e.getErrNotice());
				}
			}
		};
		runOnWorkThread(action);
	}

	/**
	 * 删除好友（联系人）
	 * 
	 * @param userId
	 * @param executeListener
	 */
	public void deleteContact(long userId, ExecuteListener executeListener) {

	}

	/**
	 * 分页查询用户
	 * 
	 * @param currPage
	 * @param pageSize
	 * @param searchName
	 * @param findItemsListener
	 */
	public void queryUserByPage(final int currPage, final int pageSize,
			final String searchName,
			final FindItemsListener<User> findItemsListener) {
		boolean isNetConnected = CommonUtils.isNetworkAvailable(context);
		if (!isNetConnected) {
			findItemsListener.onFailure(ErrorCode.GEN_004, "当前网络不可用,请检查您的网络!");
			return;
		}

		Runnable action = new Runnable() {
			@Override
			public void run() {
				List<User> uList = new ArrayList<User>();
				try {
					UserPages uPages = ClientAccount.getInstance().getUserList(
							searchName, currPage, pageSize);
					uList = uPages.getUserList();
					// 网络查询成功
					findItemsListener.onSuccess(uList, uPages.getTotalNum());
				} catch (MalformedURLException e) {
					findItemsListener.onFailure(ErrorCode.GEN_004, "网络异常");
				} catch (ProtocolException e) {
					findItemsListener.onFailure(ErrorCode.GEN_004, "网络异常");
				} catch (IOException e) {
					findItemsListener.onFailure(ErrorCode.GEN_004, "网络异常");
				} catch (ApiException e) {
					findItemsListener.onFailure(e.getErrCode(),
							e.getErrNotice());
				}
			}
		};
		runOnWorkThread(action);
	}

	/**
	 * 删除黑名单
	 * 
	 * @param blackUserId
	 * @param executeListener
	 */
	public void removeBlack(long blackUserId, ExecuteListener executeListener) {

	}
}
