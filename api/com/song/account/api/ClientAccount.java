package com.song.account.api;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URLEncoder;

import com.song.account.api.result.FriendApplyItems;
import com.song.account.api.result.FriendItems;
import com.song.account.api.result.FriendMessageItems;
import com.song.account.api.result.UserPages;
import com.song.account.entity.FriendApply;
import com.song.account.entity.FriendMessage;
import com.song.account.entity.User;
import com.song.commons.api.ApiException;
import com.song.commons.api.FormatType;
import com.song.commons.api.Result;
import com.song.commons.api.SdkHttpResult;
import com.song.commons.api.StringResult;
import com.song.commons.api.util.GsonUtil;
import com.song.commons.api.util.HttpUtil;
import com.song.commons.service.General;

public class ClientAccount {

	private static final String UTF8 = "utf-8";

	private static ClientAccount instance;

	private String uri;
	private String appKey;
	private String appSecret;

	private ClientAccount(Config config) {
		this.uri = config.getAccountApi();
		this.appKey = config.getAppKey();
		this.appSecret = config.getAppSecret();
	}

	public static ClientAccount getInstance(Config config) {
		if (instance == null) {
			instance = new ClientAccount(config);
		}
		return instance;
	}

	public User getUserById(long userId) throws MalformedURLException,
			ProtocolException, IOException, ApiException {
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey,
				appSecret, uri + "/user/getUser." + FormatType.json);

		StringBuilder sb = new StringBuilder();
		sb.append("userId=").append(userId);
		HttpUtil.setBodyParameter(sb, conn);

		SdkHttpResult shr = HttpUtil.returnResult(conn);
		if (shr.getHttpCode() != 200) {
			throw new ApiException(General.GEN_004.getErrCode(), "网络异常");
		}
		User u = (User) GsonUtil.fromJson(shr.getResult(), User.class);
		if (u != null && u.getErrCode() != null) {
			throw new ApiException(u.getErrCode(), u.getErrDesc(),
					u.getErrNotice());
		}
		return u;
	}

	public UserPages getUserList(String nickName, int currPage, int pageSize)
			throws MalformedURLException, ProtocolException, IOException,
			ApiException {
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey,
				appSecret, uri + "/user/getUserList." + FormatType.json);

		StringBuilder sb = new StringBuilder();
		if (nickName != null) {
			sb.append("nickName=").append(URLEncoder.encode(nickName, UTF8));
		}
		sb.append("&currPage=").append(currPage);
		sb.append("&pageSize=").append(pageSize);
		HttpUtil.setBodyParameter(sb, conn);

		SdkHttpResult shr = HttpUtil.returnResult(conn);
		if (shr.getHttpCode() != 200) {
			throw new ApiException(General.GEN_004.getErrCode(), "网络异常");
		}
		UserPages ups = (UserPages) GsonUtil.fromJson(shr.getResult(),
				UserPages.class);
		if (ups != null && ups.getErrCode() != null) {
			throw new ApiException(ups.getErrCode(), ups.getErrDesc(),
					ups.getErrNotice());
		}
		return ups;
	}

	public User register(String account, String password, String nick)
			throws MalformedURLException, ProtocolException, IOException,
			ApiException {
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey,
				appSecret, uri + "/user/register." + FormatType.json);

		StringBuilder sb = new StringBuilder();
		sb.append("account=").append(URLEncoder.encode(account, UTF8));
		sb.append("&password=").append(URLEncoder.encode(password, UTF8));
		sb.append("&nick=").append(URLEncoder.encode(nick, UTF8));
		HttpUtil.setBodyParameter(sb, conn);

		SdkHttpResult shr = HttpUtil.returnResult(conn);
		if (shr.getHttpCode() != 200) {
			throw new ApiException(General.GEN_004.getErrCode(), "网络异常");
		}
		User u = (User) GsonUtil.fromJson(shr.getResult(), User.class);
		if (u != null && u.getErrCode() != null) {
			throw new ApiException(u.getErrCode(), u.getErrDesc(),
					u.getErrNotice());
		}
		return u;
	}

	public User getUserBySessionId(String sessionId)
			throws MalformedURLException, ProtocolException, IOException,
			ApiException {
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey,
				appSecret, uri + "/ssoAuth/getUser." + FormatType.json);

		StringBuilder sb = new StringBuilder();
		sb.append("sessionId=").append(URLEncoder.encode(sessionId, UTF8));
		HttpUtil.setBodyParameter(sb, conn);

		SdkHttpResult shr = HttpUtil.returnResult(conn);
		if (shr.getHttpCode() != 200) {
			throw new ApiException(General.GEN_004.getErrCode(), "网络异常");
		}
		User u = (User) GsonUtil.fromJson(shr.getResult(), User.class);
		if (u != null && u.getErrCode() != null) {
			throw new ApiException(u.getErrCode(), u.getErrDesc(),
					u.getErrNotice());
		}
		return u;
	}

	public StringResult createClientSession(String appKey, String appSecret)
			throws MalformedURLException, ProtocolException, IOException,
			ApiException {
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey,
				appSecret, uri + "/ssoAuth/createClientSession."
						+ FormatType.json);

		StringBuilder sb = new StringBuilder();
		HttpUtil.setBodyParameter(sb, conn);

		SdkHttpResult shr = HttpUtil.returnResult(conn);
		if (shr.getHttpCode() != 200) {
			throw new ApiException(General.GEN_004.getErrCode(), "网络异常");
		}
		StringResult sr = (StringResult) GsonUtil.fromJson(shr.getResult(),
				StringResult.class);
		if (sr != null && sr.getErrCode() != null) {
			throw new ApiException(sr.getErrCode(), sr.getErrDesc(),
					sr.getErrNotice());
		}
		return sr;
	}

	/**
	 * 用户登入成功服务器给客户返回一个唯一sessionId。
	 * 
	 * @param account
	 * @param password
	 * @param sessionId
	 * @return
	 * @throws MalformedURLException
	 * @throws ProtocolException
	 * @throws IOException
	 * @throws ApiException
	 */
	public StringResult login(String account, String password, String sessionId)
			throws MalformedURLException, ProtocolException, IOException,
			ApiException {
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey,
				appSecret, uri + "/ssoAuth/login." + FormatType.json);

		StringBuilder sb = new StringBuilder();
		sb.append("account=").append(URLEncoder.encode(account, UTF8));
		sb.append("&password=").append(URLEncoder.encode(password, UTF8));
		if (sessionId != null) {
			sb.append("&sessionId=").append(URLEncoder.encode(sessionId, UTF8));
		}
		HttpUtil.setBodyParameter(sb, conn);

		SdkHttpResult shr = HttpUtil.returnResult(conn);
		if (shr.getHttpCode() != 200) {
			throw new ApiException(General.GEN_004.getErrCode(), "网络异常");
		}
		StringResult sr = (StringResult) GsonUtil.fromJson(shr.getResult(),
				StringResult.class);
		if (sr != null && sr.getErrCode() != null) {
			throw new ApiException(sr.getErrCode(), sr.getErrDesc(),
					sr.getErrNotice());
		}
		return sr;
	}

	/**
	 * 退出
	 * @param sessionId
	 * @return
	 * @throws MalformedURLException
	 * @throws ProtocolException
	 * @throws IOException
	 * @throws ApiException
	 */
	public Result logout(String sessionId) throws MalformedURLException,
			ProtocolException, IOException, ApiException {
		if (sessionId == null)
			return null;
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey,
				appSecret, uri + "/ssoAuth/logout." + FormatType.json);

		StringBuilder sb = new StringBuilder();
		sb.append("sessionId=").append(URLEncoder.encode(sessionId, UTF8));
		HttpUtil.setBodyParameter(sb, conn);

		SdkHttpResult shr = HttpUtil.returnResult(conn);
		if (shr.getHttpCode() != 200) {
			throw new ApiException(General.GEN_004.getErrCode(), "网络异常");
		}
		Result result = (Result) GsonUtil.fromJson(shr.getResult(),
				Result.class);
		if (result != null && result.getErrCode() != null) {
			throw new ApiException(result.getErrCode(), result.getErrDesc(),
					result.getErrNotice());
		}
		return result;
	}

	/**
	 * 获取融云TOKEN
	 * @param sessionId
	 * @param resAccountUri
	 * @return
	 * @throws MalformedURLException
	 * @throws ProtocolException
	 * @throws IOException
	 * @throws ApiException
	 */
	public StringResult getRongToken(String sessionId, String resAccountUri)
			throws MalformedURLException, ProtocolException, IOException,
			ApiException {
		if (sessionId == null)
			return null;
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey,
				appSecret, uri + "/ssoAuth/getRongToken." + FormatType.json);

		StringBuilder sb = new StringBuilder();
		sb.append("sessionId=").append(URLEncoder.encode(sessionId, UTF8));
		sb.append("&resAccountUri=").append(
				URLEncoder.encode(resAccountUri, UTF8));
		HttpUtil.setBodyParameter(sb, conn);

		SdkHttpResult shr = HttpUtil.returnResult(conn);
		if (shr.getHttpCode() != 200) {
			throw new ApiException(General.GEN_004.getErrCode(), "网络异常");
		}
		StringResult sr = (StringResult) GsonUtil.fromJson(shr.getResult(),
				StringResult.class);
		if (sr != null && sr.getErrCode() != null) {
			throw new ApiException(sr.getErrCode(), sr.getErrDesc(),
					sr.getErrNotice());
		}
		return sr;
	}

	/**
	 * 获取好友列表
	 * @param sessionId
	 * @return
	 * @throws MalformedURLException
	 * @throws ProtocolException
	 * @throws IOException
	 * @throws ApiException
	 */
	public FriendItems getFriendList(String sessionId)
			throws MalformedURLException, ProtocolException, IOException,
			ApiException {
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey,
				appSecret, uri + "/friend/getFriendListByUserId."
						+ FormatType.json);

		StringBuilder sb = new StringBuilder();
		sb.append("sessionId=").append(URLEncoder.encode(sessionId, UTF8));
		HttpUtil.setBodyParameter(sb, conn);

		SdkHttpResult shr = HttpUtil.returnResult(conn);
		if (shr.getHttpCode() != 200) {
			throw new ApiException(General.GEN_004.getErrCode(), "网络异常");
		}
		FriendItems rt = (FriendItems) GsonUtil.fromJson(shr.getResult(),
				FriendItems.class);
		if (rt != null && rt.getErrCode() != null) {
			throw new ApiException(rt.getErrCode(), rt.getErrDesc(),
					rt.getErrNotice());
		}
		return rt;
	}
	
	public FriendApply getFriendApplyById(String sessionId, long fayId)
			throws MalformedURLException, ProtocolException, IOException,
			ApiException {
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey,
				appSecret, uri + "/friend/getFriendApplyById."
						+ FormatType.json);

		StringBuilder sb = new StringBuilder();
		sb.append("sessionId=").append(URLEncoder.encode(sessionId, UTF8));
		sb.append("&fayId=").append(fayId);
		HttpUtil.setBodyParameter(sb, conn);

		SdkHttpResult shr = HttpUtil.returnResult(conn);
		if (shr.getHttpCode() != 200) {
			throw new ApiException(General.GEN_004.getErrCode(), "网络异常");
		}
		FriendApply rt = (FriendApply) GsonUtil.fromJson(shr.getResult(),
				FriendApply.class);
		if (rt != null && rt.getErrCode() != null) {
			throw new ApiException(rt.getErrCode(), rt.getErrDesc(),
					rt.getErrNotice());
		}
		return rt;
	}
	
	public FriendApply sendFriendApply(String sessionId, long toUserId, String reason)
			throws MalformedURLException, ProtocolException, IOException,
			ApiException {
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey,
				appSecret, uri + "/friend/sendFriendApply."
						+ FormatType.json);

		StringBuilder sb = new StringBuilder();
		sb.append("sessionId=").append(URLEncoder.encode(sessionId, UTF8));
		sb.append("&toUserId=").append(toUserId);
		sb.append("&reason=").append(URLEncoder.encode(reason, UTF8));
		HttpUtil.setBodyParameter(sb, conn);

		SdkHttpResult shr = HttpUtil.returnResult(conn);
		if (shr.getHttpCode() != 200) {
			throw new ApiException(General.GEN_004.getErrCode(), "网络异常");
		}
		FriendApply rt = (FriendApply) GsonUtil.fromJson(shr.getResult(),
				FriendApply.class);
		if (rt != null && rt.getErrCode() != null) {
			throw new ApiException(rt.getErrCode(), rt.getErrDesc(),
					rt.getErrNotice());
		}
		return rt;
	}
	
	public Result agreeFriendApply(String sessionId, long fayId)
			throws MalformedURLException, ProtocolException, IOException,
			ApiException {
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey,
				appSecret, uri + "/friend/agreeFriendApply."
						+ FormatType.json);

		StringBuilder sb = new StringBuilder();
		sb.append("sessionId=").append(URLEncoder.encode(sessionId, UTF8));
		sb.append("&fayId=").append(fayId);
		HttpUtil.setBodyParameter(sb, conn);

		SdkHttpResult shr = HttpUtil.returnResult(conn);
		if (shr.getHttpCode() != 200) {
			throw new ApiException(General.GEN_004.getErrCode(), "网络异常");
		}
		Result rt = (Result) GsonUtil.fromJson(shr.getResult(),
				Result.class);
		if (rt != null && rt.getErrCode() != null) {
			throw new ApiException(rt.getErrCode(), rt.getErrDesc(),
					rt.getErrNotice());
		}
		return rt;
	}
	
	public Result delFriendApplyById(String sessionId, long fayId)
			throws MalformedURLException, ProtocolException, IOException,
			ApiException {
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey,
				appSecret, uri + "/friend/delFriendApplyById."
						+ FormatType.json);

		StringBuilder sb = new StringBuilder();
		sb.append("sessionId=").append(URLEncoder.encode(sessionId, UTF8));
		sb.append("&fayId=").append(fayId);
		HttpUtil.setBodyParameter(sb, conn);

		SdkHttpResult shr = HttpUtil.returnResult(conn);
		if (shr.getHttpCode() != 200) {
			throw new ApiException(General.GEN_004.getErrCode(), "网络异常");
		}
		Result rt = (Result) GsonUtil.fromJson(shr.getResult(),
				Result.class);
		if (rt != null && rt.getErrCode() != null) {
			throw new ApiException(rt.getErrCode(), rt.getErrDesc(),
					rt.getErrNotice());
		}
		return rt;
	}
	
	public FriendApplyItems getFriendApplyList(String sessionId)
			throws MalformedURLException, ProtocolException, IOException,
			ApiException {
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey,
				appSecret, uri + "/friend/getFriendApplyList."
						+ FormatType.json);

		StringBuilder sb = new StringBuilder();
		sb.append("sessionId=").append(URLEncoder.encode(sessionId, UTF8));
		HttpUtil.setBodyParameter(sb, conn);

		SdkHttpResult shr = HttpUtil.returnResult(conn);
		if (shr.getHttpCode() != 200) {
			throw new ApiException(General.GEN_004.getErrCode(), "网络异常");
		}
		FriendApplyItems rt = (FriendApplyItems) GsonUtil.fromJson(shr.getResult(),
				FriendApplyItems.class);
		if (rt != null && rt.getErrCode() != null) {
			throw new ApiException(rt.getErrCode(), rt.getErrDesc(),
					rt.getErrNotice());
		}
		return rt;
	}
	
	public FriendApply getFriendApplyBoth(String sessionId, long toUserId)
			throws MalformedURLException, ProtocolException, IOException,
			ApiException {
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey,
				appSecret, uri + "/friend/getFriendApplyBoth."
						+ FormatType.json);

		StringBuilder sb = new StringBuilder();
		sb.append("sessionId=").append(URLEncoder.encode(sessionId, UTF8));
		sb.append("&toUserId=").append(toUserId);
		HttpUtil.setBodyParameter(sb, conn);

		SdkHttpResult shr = HttpUtil.returnResult(conn);
		if (shr.getHttpCode() != 200) {
			throw new ApiException(General.GEN_004.getErrCode(), "网络异常");
		}
		FriendApply rt = (FriendApply) GsonUtil.fromJson(shr.getResult(),
				FriendApply.class);
		if (rt != null && rt.getErrCode() != null) {
			throw new ApiException(rt.getErrCode(), rt.getErrDesc(),
					rt.getErrNotice());
		}
		return rt;
	}
	
	public FriendMessage sendFriendMessage(String sessionId, long fayId, long toUserId, String message)
			throws MalformedURLException, ProtocolException, IOException,
			ApiException {
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey,
				appSecret, uri + "/friend/sendFriendMessage."
						+ FormatType.json);

		StringBuilder sb = new StringBuilder();
		sb.append("sessionId=").append(URLEncoder.encode(sessionId, UTF8));
		sb.append("&fayId=").append(fayId);
		sb.append("&toUserId=").append(toUserId);
		sb.append("&message=").append(URLEncoder.encode(message, UTF8));
		HttpUtil.setBodyParameter(sb, conn);

		SdkHttpResult shr = HttpUtil.returnResult(conn);
		if (shr.getHttpCode() != 200) {
			throw new ApiException(General.GEN_004.getErrCode(), "网络异常");
		}
		FriendMessage rt = (FriendMessage) GsonUtil.fromJson(shr.getResult(),
				FriendMessage.class);
		if (rt != null && rt.getErrCode() != null) {
			throw new ApiException(rt.getErrCode(), rt.getErrDesc(),
					rt.getErrNotice());
		}
		return rt;
	}
	
	public FriendMessageItems getFriendMessageListByFayId(String sessionId, long fayId)
			throws MalformedURLException, ProtocolException, IOException,
			ApiException {
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey,
				appSecret, uri + "/friend/getFriendMessageListByFayId."
						+ FormatType.json);

		StringBuilder sb = new StringBuilder();
		sb.append("sessionId=").append(URLEncoder.encode(sessionId, UTF8));
		sb.append("&fayId=").append(fayId);
		HttpUtil.setBodyParameter(sb, conn);

		SdkHttpResult shr = HttpUtil.returnResult(conn);
		if (shr.getHttpCode() != 200) {
			throw new ApiException(General.GEN_004.getErrCode(), "网络异常");
		}
		FriendMessageItems rt = (FriendMessageItems) GsonUtil.fromJson(shr.getResult(),
				FriendMessageItems.class);
		if (rt != null && rt.getErrCode() != null) {
			throw new ApiException(rt.getErrCode(), rt.getErrDesc(),
					rt.getErrNotice());
		}
		return rt;
	}
		
	public Result delFriendById(String sessionId, long friendId)
			throws MalformedURLException, ProtocolException, IOException,
			ApiException {
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey,
				appSecret, uri + "/friend/delFriendById."
						+ FormatType.json);

		StringBuilder sb = new StringBuilder();
		sb.append("sessionId=").append(URLEncoder.encode(sessionId, UTF8));
		sb.append("&friendId=").append(friendId);
		HttpUtil.setBodyParameter(sb, conn);

		SdkHttpResult shr = HttpUtil.returnResult(conn);
		if (shr.getHttpCode() != 200) {
			throw new ApiException(General.GEN_004.getErrCode(), "网络异常");
		}
		Result rt = (Result) GsonUtil.fromJson(shr.getResult(),
				Result.class);
		if (rt != null && rt.getErrCode() != null) {
			throw new ApiException(rt.getErrCode(), rt.getErrDesc(),
					rt.getErrNotice());
		}
		return rt;
	}
	
	public StringResult isFriend(String sessionId, long aUserId, long bUserId)
			throws MalformedURLException, ProtocolException, IOException,
			ApiException {
		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey,
				appSecret, uri + "/friend/isFriend."
						+ FormatType.json);

		StringBuilder sb = new StringBuilder();
		sb.append("sessionId=").append(URLEncoder.encode(sessionId, UTF8));
		sb.append("&aUserId=").append(aUserId);
		sb.append("&bUserId=").append(bUserId);
		HttpUtil.setBodyParameter(sb, conn);

		SdkHttpResult shr = HttpUtil.returnResult(conn);
		if (shr.getHttpCode() != 200) {
			throw new ApiException(General.GEN_004.getErrCode(), "网络异常");
		}
		StringResult rt = (StringResult) GsonUtil.fromJson(shr.getResult(),
				StringResult.class);
		if (rt != null && rt.getErrCode() != null) {
			throw new ApiException(rt.getErrCode(), rt.getErrDesc(),
					rt.getErrNotice());
		}
		return rt;
	}
}
