package com.song.dapei.entity;

import java.util.Date;

import com.song.commons.entity.LazyLoadEntity;

public class ColClassify extends LazyLoadEntity implements java.io.Serializable {

	private static final long serialVersionUID = 3521692652618418024L;

	/** 主键 */
	private Long colClaId;
	/** 搭配ID */
	private Long colId;
	/** 类别id */
	private Long typeId;
	/** 添加时间 */
	private Date addTime;
	/** 操作员 */
	private Long oprId;

	public Long getColClaId() {
		return colClaId;
	}

	public void setColClaId(Long colClaId) {
		this.colClaId = colClaId;
	}

	public Long getColId() {
		return colId;
	}

	public void setColId(Long colId) {
		this.colId = colId;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
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
		if (this.addTime == null)
			this.addTime = new Date();
	}

}
