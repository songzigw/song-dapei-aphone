package com.song.dapei.entity;

import java.util.Date;

import com.song.commons.entity.LazyLoadEntity;

public class ColEveryday extends LazyLoadEntity implements java.io.Serializable {

	private static final long serialVersionUID = -2814273507706078219L;

	/** 搭配ID */
	private Long colId;
	/** 添加时间 */
	private Date addTime;
	/** 操作员 */
	private Long  oprId;

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

	public Long getOprId() {
		return oprId;
	}

	public void setOprId(Long oprId) {
		this.oprId = oprId;
	}

	@Override
	public void init() {
		if (this.addTime == null) {
			this.addTime = new Date();
		}
	}
}
