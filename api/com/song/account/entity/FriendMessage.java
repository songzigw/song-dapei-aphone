package com.song.account.entity;

import java.util.Date;

import com.song.commons.entity.LazyLoadEntity;

public class FriendMessage extends LazyLoadEntity {

	private static final long serialVersionUID = 3307392933409072221L;

	@Override
	public void init() {
		if (this.addTime == null) {
			this.addTime = new Date();
		}
	}

	/** 主键ID */
	private Long fmeId;
	/** 好友申请ID */
	private Long fayId;
	/** 消息放送方 */
	private Long fromUserId;
	/** 消息接受方 */
	private Long toUserId;
	/** 发送时间 */
	private Date addTime;
	/** 信息内容 */
	private String message;

	public Long getFayId() {
		return fayId;
	}
	public void setFayId(Long fayId) {
		this.fayId = fayId;
	}
	public Long getFromUserId() {
		return fromUserId;
	}
	public void setFromUserId(Long fromUserId) {
		this.fromUserId = fromUserId;
	}
	public Long getToUserId() {
		return toUserId;
	}
	public void setToUserId(Long toUserId) {
		this.toUserId = toUserId;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public Long getFmeId() {
		return fmeId;
	}
	public void setFmeId(Long fmeId) {
		this.fmeId = fmeId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}