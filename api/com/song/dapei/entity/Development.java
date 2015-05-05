package com.song.dapei.entity;

import java.util.Date;

import com.song.commons.entity.LazyLoadEntity;

/**
 * 动态
 * 
 * @author 张松
 * 
 */
public class Development extends LazyLoadEntity implements java.io.Serializable {

	private static final long serialVersionUID = 6804540853080186145L;

	/**
	 * 动作类型
	 * 
	 * @author 张松
	 * 
	 */
	public static enum DevType {
		/** 搭配发布 */
		COL_PUBLISH(0),
		/** 关注用户 */
		USER_FOLLOW(1);

		private final int value;

		private DevType(int v) {
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

		public static DevType getInstance(int value) {
			for (DevType o : DevType.values()) {
				if (o.getValue() == value) {
					return o;
				}
			}
			return null;
		}
	}

	/** 动态id */
	private Long devId;
	/** 用户id */
	private Long userId;
	/** 动态描述 */
	private String description;
	/** 动态类型 */
	private DevType devType;
	/** 目标id */
	private Long targetId;
	/** 添加时间 */
	private Date addTime;

	public Long getDevId() {
		return devId;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DevType getDevType() {
		return devType;
	}

	public void setDevType(DevType devType) {
		this.devType = devType;
	}

	public Long getTargetId() {
		return targetId;
	}

	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Override
	public void init() {
		if (this.addTime == null) {
			this.addTime = new Date();
		}
	}
}
