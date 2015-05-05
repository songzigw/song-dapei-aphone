package com.song.account.entity;

import java.util.Date;

import com.song.commons.entity.LazyLoadEntity;

/**
 * 平台绑定
 * 
 * @author 张松
 * 
 */
public class BindSite extends LazyLoadEntity {

	private static final long serialVersionUID = -9031247091155132294L;

	/**
	 * 第三方平台标示
	 * 
	 * @author 张松
	 * 
	 */
	public static enum SiteMark {
		QQ(1),
		SINA(2);

		private final int value;

		private SiteMark(int v) {
			this.value = v;
		}

		/**
		 * 得到枚举值
		 * 
		 * @return
		 */
		public int getValue() {
			return value;
		}

		public static SiteMark getInstance(int value) {
			for (SiteMark sm : SiteMark.values()) {
				if (sm.getValue() == value) {
					return sm;
				}
			}
			return null;
		}
	}

	@Override
	public void init() {
		if (this.addTime == null) {
			this.addTime = new Date();
		}
		if (this.regFlag == null) {
			this.regFlag = false;
		}
		if (this.startDate == null)
			this.startDate = new Date();
		if (this.updTime == null)
			this.updTime = new Date();
	}

	/** 主键ID */
	private Long bindSiteId;
	/** 添加时间 */
	private Date addTime;
	/** 用户ID */
	private Long userId;
	/** 第三方平台标示 */
	private SiteMark siteMark;
	/** 第三方OpenId */
	private String openId;
	/** access_token */
	private String accessToken;
	/** 授权起始日期 */
	private Date startDate;
	/** 授权结束日期 */
	private Date endDate;
	/** 第三方平台用户名称 */
	private String nickName;
	/** 是否是第三方用户注册 */
	private Boolean regFlag;
	/** 修改时间 */
	private Date updTime;
	/** 头像地址 */
	private String photoPath;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getUpdTime() {
		return updTime;
	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public Boolean getRegFlag() {
		return regFlag;
	}

	public void setRegFlag(Boolean regFlag) {
		this.regFlag = regFlag;
	}

	public Long getBindSiteId() {
		return bindSiteId;
	}

	public void setBindSiteId(Long bindSiteId) {
		this.bindSiteId = bindSiteId;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public SiteMark getSiteMark() {
		return siteMark;
	}

	public void setSiteMark(SiteMark siteMark) {
		this.siteMark = siteMark;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
}
