package com.song.account.entity;

import java.util.Date;

import com.song.commons.entity.LazyLoadEntity;

/**
 * 用户
 * 
 * @author 张松
 * 
 */
public class User extends LazyLoadEntity {

	private static final long serialVersionUID = 8776550770295897410L;

	/** 用户ID */
	private Long userId;
	/** 账号 */
	private String account;
	/** 密码 */
	private String password;
	/** 昵称 */
	private String nickName;
	/** 用户姓名 */
	private String userName;
	/** 添加时间 */
	private Date addTime;
	/** 头像路径 */
	private String photoPath;
	/** 性别 */
	private Integer sex;
	/** 电子邮箱 */
	private String email;
	/** Enable(激活的) */
	private String enEmail;
	/** 电子邮件激活验证码 */
	private Long emIcId;
	/** 生日-年 */
	private Integer birthdayYear;
	/** 生日-月 */
	private Integer birthdayMonth;
	/** 生日-日 */
	private Integer birthdayDay;
	/** 简介 */
	private String summary;
	/** E通信地址（聊天工具） */
	private String eaddress;
	/** 用户在RongCloud服务器上的Token */
	private String rongToken;

	public User() {
		super();
	}

	public String getRongToken() {
		return rongToken;
	}

	public void setRongToken(String rongToken) {
		this.rongToken = rongToken;
	}

	public User(Long userId) {
		this();
		this.userId = userId;
	}

	public void init() {
		if (this.addTime == null)
			this.addTime = new Date();
	}

	/** 用户ID */
	public Long getUserId() {
		return userId;
	}

	/** 用户ID */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/** 用户姓名 */
	public String getUserName() {
		return userName;
	}

	/** 用户姓名 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/** 添加时间 */
	public Date getAddTime() {
		return addTime;
	}

	/** 添加时间 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	/** 账号 */
	public String getAccount() {
		return account;
	}

	/** 账号 */
	public void setAccount(String account) {
		this.account = account;
	}

	/** 密码 */
	public String getPassword() {
		return password;
	}

	/** 密码 */
	public void setPassword(String password) {
		this.password = password;
	}

	/** 昵称 */
	public String getNickName() {
		return nickName;
	}

	/** 昵称 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public String getAvatar() {
		return photoPath;
	}
	
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getBirthdayYear() {
		return birthdayYear;
	}

	public void setBirthdayYear(Integer birthdayYear) {
		this.birthdayYear = birthdayYear;
	}

	public Integer getBirthdayMonth() {
		return birthdayMonth;
	}

	public void setBirthdayMonth(Integer birthdayMonth) {
		this.birthdayMonth = birthdayMonth;
	}

	public Integer getBirthdayDay() {
		return birthdayDay;
	}

	public void setBirthdayDay(Integer birthdayDay) {
		this.birthdayDay = birthdayDay;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getEaddress() {
		return eaddress;
	}

	public void setEaddress(String eaddress) {
		this.eaddress = eaddress;
	}

	public void setEnEmail(String enEmail) {
		this.enEmail = enEmail;
	}

	public void setEmIcId(Long emIcId) {
		this.emIcId = emIcId;
	}

	public String getEnEmail() {
		return enEmail;
	}

	public Long getEmIcId() {
		return emIcId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [account=" + account + ", addTime=" + addTime
				+ ", birthdayDay=" + birthdayDay + ", birthdayMonth="
				+ birthdayMonth + ", birthdayYear=" + birthdayYear
				+ ", eaddress=" + eaddress + ", email=" + email + ", nickName="
				+ nickName + ", password=" + password + ", photoPath="
				+ photoPath + ", sex=" + sex + ", summary=" + summary
				+ ", userId=" + userId + ", userName=" + userName + "]";
	}
}
