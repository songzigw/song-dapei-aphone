package com.song.dapei.entity;

import java.util.Date;

import com.song.commons.entity.LazyLoadEntity;

public class ColPraise extends LazyLoadEntity implements java.io.Serializable {

	private static final long serialVersionUID = -2284671793169453120L;

	/** 用户ID */
	private Long userId;
	/** 搭配ID */
	private Long colId;
	/** 添加时间 */
	private Date addTime;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getColId() {
		return colId;
	}

	public void setColId(Long colId) {
		this.colId = colId;
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
