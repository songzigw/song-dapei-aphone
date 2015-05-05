package com.song.account.entity;

import java.util.Date;

import com.song.commons.entity.LazyLoadEntity;

/**
 * APP相关信息
 * @author 张松
 */
public class AppInfo extends LazyLoadEntity {

	private static final long serialVersionUID = 4834431788318413010L;

	@Override
	public void init() {
		if (this.addTime == null) {
			this.addTime = new Date();
		}
	}

	/** appKey */
	private Long appKey;
	/** appSecret */
	private String appSecret;
	/** 添加时间 */
	private Date addTime;

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Long getAppKey() {
		return appKey;
	}

	public void setAppKey(Long appKey) {
		this.appKey = appKey;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
}
