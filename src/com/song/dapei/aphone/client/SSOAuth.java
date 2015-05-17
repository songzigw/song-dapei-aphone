package com.song.dapei.aphone.client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;

import android.content.Context;
import android.util.Log;

import com.song.account.api.ClientAccount;
import com.song.account.entity.User;
import com.song.commons.api.ApiException;
import com.song.commons.api.StringResult;
import com.song.commons.service.General;
import com.song.dapei.aphone.client.db.DapeiDB;
import com.song.dapei.aphone.client.listener.ExecuteListener;
import com.song.dapei.aphone.config.ConfigManager;
import com.song.dapei.aphone.util.SharePreferenceUtil;

/**
 * 统一身份认证服务
 * 
 * @author songzigw
 * 
 */
public class SSOAuth {

	private static final String TAG = SSOAuth.class.getSimpleName();
	private static SSOAuth ssoAuth;

	private Context context;
	private User currUser;

	private SSOAuth(Context context) {
		if (this.context == null) {
			this.context = context;
		}
	}

	public static SSOAuth getInstance(Context context) {
		if (ssoAuth == null) {
			ssoAuth = new SSOAuth(context);
		}
		return ssoAuth;
	}

	/**
	 * 获取客户端SessionId
	 * 
	 * @return
	 */
	public String getSessionId() {
		SharePreferenceUtil spu = new SharePreferenceUtil(context,
				SharePreferenceUtil.SHARED_FILE_NAME);
		return spu.getSessionId();
	}

	/**
	 * 获取当前在线用户身份信息
	 * 
	 * @return
	 * @throws ApiException
	 * @throws IOException
	 * @throws ProtocolException
	 * @throws MalformedURLException
	 */
	public User getCurrUser() {
		final String sessionId = this.getSessionId();
		if (sessionId == null) {
			return null;
		}
		currUser = DapeiDB.getInstance(context).getUser(sessionId);

		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					currUser = ClientAccount.getInstance().getUserBySessionId(
							sessionId);
					DapeiDB.getInstance(context)
							.updateUser(sessionId, currUser);
				} catch (MalformedURLException e) {
					Log.e(TAG, "getCurrUser", e);
				} catch (ProtocolException e) {
					Log.e(TAG, "getCurrUser", e);
				} catch (IOException e) {
					Log.e(TAG, "getCurrUser", e);
				} catch (ApiException e) {
					Log.e(TAG, "getCurrUser", e);
					if (e.getErrCode().equals(ErrorCode.ACC_103_001)) {
						DapeiDB.getInstance(context).deleteUser(sessionId);
					}
				}
			}
		};
		t.start();

		return currUser;
	}

	/**
	 * 创建客户端标示
	 * 
	 * @throws ApiException
	 * @throws IOException
	 * @throws ProtocolException
	 * @throws MalformedURLException
	 */
	// public void createSession(boolean isCreate) throws MalformedURLException,
	// ProtocolException, IOException, ApiException {
	// String sessionId = this.getSessionId();
	// if (StringUtil.isEmptyOrNull(sessionId) && isCreate) {
	// StringResult sr =
	// ClientAccount.getInstance(config).createClientSession(APP_KEY,
	// APP_SECRET);
	// sessionId = sr.getValue();
	// SharePreferenceUtil spu = new SharePreferenceUtil(context,
	// SharePreferenceUtil.SHARED_FILE_NAME);
	// spu.setSessionId(sessionId);
	// } else {
	// ClientAccount.getInstance(config).getClientSessionId(APP_KEY, APP_SECRET,
	// sessionId);
	// }
	// }

	/**
	 * 登入
	 * 
	 * @param account
	 * @param password
	 * @param executeListener
	 */
	public void login(final String account, final String password,
			final ExecuteListener executeListener) {
		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					String sessionId = SSOAuth.getInstance(context)
							.getSessionId();
					StringResult sr = ClientAccount.getInstance().login(
							account, password, sessionId);
					// 登入成功
					sessionId = sr.getValue();
					// 保存sessionId到本地
					SharePreferenceUtil spu = new SharePreferenceUtil(context,
							SharePreferenceUtil.SHARED_FILE_NAME);
					spu.setSessionId(sessionId);

					currUser = ClientAccount.getInstance().getUserBySessionId(
							sessionId);
					if (currUser != null) {
						// 每次登入成功獲取新的TOKEN
						StringResult sResult = ClientAccount.getInstance()
								.getRongToken(
										sessionId,
										ConfigManager.getInstance()
												.getResAccountUri());
						currUser.setRongToken(sResult.getValue());
						executeListener.onSuccess(null);
						// 保存到本地数据库
						DapeiDB.getInstance(context)
								.insert(sessionId, currUser);
					}
				} catch (MalformedURLException e) {
					executeListener.onFailure(General.GEN_004.getErrCode(),
							"网络异常");
				} catch (ProtocolException e) {
					executeListener.onFailure(General.GEN_004.getErrCode(),
							"网络异常");
				} catch (IOException e) {
					executeListener.onFailure(General.GEN_004.getErrCode(),
							"网络异常");
				} catch (ApiException e) {
					executeListener.onFailure(e.getErrCode(), e.getErrNotice());
				}
			}
		};
		t.start();
	}

	/**
	 * 退出
	 * 
	 * @throws ApiException
	 * @throws IOException
	 * @throws ProtocolException
	 * @throws MalformedURLException
	 */
	public void logout() {
		final String sessionId = this.getSessionId();
		DapeiDB.getInstance(context).deleteUser(sessionId);
		SharePreferenceUtil spu = new SharePreferenceUtil(context,
				SharePreferenceUtil.SHARED_FILE_NAME);
		spu.setSessionId(null);

		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					ClientAccount.getInstance().logout(sessionId);
				} catch (MalformedURLException e) {
					Log.e(TAG, "logout", e);
				} catch (ProtocolException e) {
					Log.e(TAG, "logout", e);
				} catch (IOException e) {
					Log.e(TAG, "logout", e);
				} catch (ApiException e) {
					Log.e(TAG, "logout", e);
				}
			}
		};
		t.start();
	}

	public void getRongToken(final String resAccountUri,
			final ExecuteListener executeListener) {
		final String sessionId = this.getSessionId();
		if (sessionId == null) {
			executeListener.onFailure(ErrorCode.APH_001, "您还没有登入");
			return;
		}

		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					StringResult stringResult = ClientAccount.getInstance()
							.getRongToken(sessionId, resAccountUri);
					// 修改本地数据库
					DapeiDB.getInstance(context).updateUserRongToken(sessionId,
							stringResult.getValue());
					executeListener.onSuccess(stringResult.getValue());
				} catch (MalformedURLException e) {
					Log.e(TAG, "getRongToken", e);
					executeListener.onFailure(General.GEN_004.getErrCode(),
							"网络异常");
				} catch (ProtocolException e) {
					Log.e(TAG, "getRongToken", e);
					executeListener.onFailure(General.GEN_004.getErrCode(),
							"网络异常");
				} catch (IOException e) {
					Log.e(TAG, "getRongToken", e);
					executeListener.onFailure(General.GEN_004.getErrCode(),
							"网络异常");
				} catch (ApiException e) {
					executeListener.onFailure(e.getErrCode(), e.getErrNotice());
				}
			}
		};
		t.start();
	}

}
