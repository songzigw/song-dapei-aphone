package com.song.dapei.entity;

import java.util.Date;

import com.song.commons.entity.LazyLoadEntity;

public class ColFavorite extends LazyLoadEntity implements java.io.Serializable {

	private static final long serialVersionUID = -9121562706779377027L;

	/** 搭配ID */
	private Long colId;
	/** 用户id */
	private Long userId;
	/** 添加时间 */
	private Date addTime;

	public Long getColId() {
		return colId;
	}

	public void setColId(Long colId) {
		this.colId = colId;
	}

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

	@Override
	public void init() {
		if (this.addTime == null) {
			this.addTime = new Date();
		}
	}
}
