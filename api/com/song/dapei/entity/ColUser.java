package com.song.dapei.entity;

import java.util.Date;

import com.song.commons.entity.LazyLoadEntity;

/**
 * 用户（搭配）设置
 * 
 * @author 张松
 */
public class ColUser extends LazyLoadEntity implements java.io.Serializable {

	private static final long serialVersionUID = -5464306581964730059L;

	/** 用户ID */
	private Long userId;
	/** 添加时间 */
	private Date addTime;
	/** 用户标签 */
	private String Tags;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getTags() {
		return Tags;
	}

	public void setTags(String tags) {
		Tags = tags;
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
		ColUser other = (ColUser) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ColUser [userId=" + userId + "]";
	}

	@Override
	public void init() {
		if (addTime == null)
			addTime = new Date();
	}

}
