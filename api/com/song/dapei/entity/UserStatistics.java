package com.song.dapei.entity;

import java.util.Date;

import com.song.commons.entity.LazyLoadEntity;

/**
 * 用户统计
 * 
 * @author 张松
 * 
 */
public class UserStatistics extends LazyLoadEntity implements java.io.Serializable {

	private static final long serialVersionUID = 455308859134214335L;

	/** 用户ID */
	private Long userId;
	/** 添加时间 */
	private Date addTime;
	/** 粉丝数 */
	private Integer followCount;
	/** 关注数 */
	private Integer regardCount;
	/** 搭配数 */
	private Integer dapeiCount;

	public UserStatistics() {
		super();
	}

	public UserStatistics(Long userId) {
		this();
		this.userId = userId;
	}

	public void init() {
		if (this.addTime == null) {
			this.addTime = new Date();
		}
		if(this.followCount==null)
			this.followCount = 0;
		if(this.regardCount==null)
			this.regardCount = 0;
		if(this.dapeiCount==null)
			this.dapeiCount = 0;
	}
	
	/** 用户ID */
	public Long getUserId() {
		return userId;
	}

	/** 用户ID */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/** 添加时间 */
	public Date getAddTime() {
		return addTime;
	}

	/** 添加时间 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Integer getFollowCount() {
		return followCount;
	}

	public void setFollowCount(Integer followCount) {
		this.followCount = followCount;
	}

	public Integer getRegardCount() {
		return regardCount;
	}

	public void setRegardCount(Integer regardCount) {
		this.regardCount = regardCount;
	}

	public Integer getDapeiCount() {
		return dapeiCount;
	}

	public void setDapeiCount(Integer dapeiCount) {
		this.dapeiCount = dapeiCount;
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
		UserStatistics other = (UserStatistics) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserStatistics [addTime=" + addTime + ", dapeiCount="
				+ dapeiCount + ", followCount=" + followCount
				+ ", regardCount=" + regardCount + ", userId=" + userId + "]";
	}

}
