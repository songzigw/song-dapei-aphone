package com.song.dapei.entity;

import java.util.Date;

import com.song.commons.entity.LazyLoadEntity;

/**
 * 用户粉丝
 * 
 * @author 张松
 * 
 */
public class UserFollow extends LazyLoadEntity implements java.io.Serializable {

	private static final long serialVersionUID = -1847225569223634721L;

	/** 用户ID（焦点人物） */
	private Long focusId;
	/** 添加时间 */
	private Date addTime;
	/** 粉丝ID */
	private Long followId;

	public UserFollow() {
		super();
	}

	public UserFollow(Long focusId) {
		this();
		this.focusId = focusId;
	}

	public void init() {
		if (this.addTime == null) {
			this.addTime = new Date();
		}
	}
	
	/** 用户ID */
	public Long getFocusId() {
		return focusId;
	}

	/** 用户ID */
	public void setFocusId(Long focusId) {
		this.focusId = focusId;
	}

	/** 添加时间 */
	public Date getAddTime() {
		return addTime;
	}

	/** 添加时间 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Long getFollowId() {
		return followId;
	}

	public void setFollowId(Long followId) {
		this.followId = followId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((focusId == null) ? 0 : focusId.hashCode());
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
		UserFollow other = (UserFollow) obj;
		if (focusId == null) {
			if (other.focusId != null)
				return false;
		} else if (!focusId.equals(other.focusId))
			return false;
		return true;
	}

}
