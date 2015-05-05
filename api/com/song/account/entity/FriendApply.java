package com.song.account.entity;

import java.util.Date;

import com.song.commons.entity.LazyLoadEntity;

public class FriendApply extends LazyLoadEntity {

	private static final long serialVersionUID = 5823259536513113806L;

	@Override
	public void init() {
		if (this.addTime == null) {
			this.addTime = new Date();
		}
		if (this.resultStatus == null) {
			this.resultStatus = 0;
		}
		if (this.fromDelFlag == null) {
			this.fromDelFlag = false;
		}
		if (this.toDelFlag == null) {
			this.toDelFlag = false;
		}
	}

	/** 主键ID */
	private Long fayId;
	/** 申请人 */
	private Long fromUserId;
	/** 接受方 */
	private Long toUserId;
	/** 申请时间 */
	private Date addTime;
	/** 发送的信息 */
	private String friendMessage;
	/** 申请结果：0未处理 1同意  */
	private Integer resultStatus;
	/** 消息在申请方是否删除 */
	private Boolean fromDelFlag;
	/** 消息在接受方是否删除 */
	private Boolean toDelFlag;

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

	public Integer getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(Integer resultStatus) {
		this.resultStatus = resultStatus;
	}

	public String getFriendMessage() {
		return friendMessage;
	}

	public void setFriendMessage(String friendMessage) {
		this.friendMessage = friendMessage;
	}

	public Boolean getFromDelFlag() {
		return fromDelFlag;
	}

	public void setFromDelFlag(Boolean fromDelFlag) {
		this.fromDelFlag = fromDelFlag;
	}

	public Boolean getToDelFlag() {
		return toDelFlag;
	}

	public void setToDelFlag(Boolean toDelFlag) {
		this.toDelFlag = toDelFlag;
	}

	public static enum ResultStatus {
		/** 0未处理 */
		UN_OPE(0),
		/** 1同意 */
		AGREE(1);

		private final int value;

		private ResultStatus(int v) {
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

		public static ResultStatus getInstance(int value) {
			for (ResultStatus o : ResultStatus.values()) {
				if (o.getValue() == value) {
					return o;
				}
			}
			return null;
		}
	}
}