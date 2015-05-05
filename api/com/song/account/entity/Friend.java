package com.song.account.entity;

import java.util.Date;

import com.song.commons.entity.LazyLoadEntity;

public class Friend extends LazyLoadEntity {

	private static final long serialVersionUID = -1802565668588384621L;

	@Override
	public void init() {
		if (this.addTime == null) {
			this.addTime = new Date();
		}
	}
	
	private Long userId;
	private Long friendUserId;
	private Date addTime;

	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getFriendUserId() {
		return friendUserId;
	}
	public void setFriendUserId(Long friendUserId) {
		this.friendUserId = friendUserId;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

}