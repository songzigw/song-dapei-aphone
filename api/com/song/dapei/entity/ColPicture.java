package com.song.dapei.entity;

import java.util.Date;

import com.song.commons.entity.LazyLoadEntity;

public class ColPicture extends LazyLoadEntity implements java.io.Serializable {

	private static final long serialVersionUID = 9091822952842064837L;

	/** 搭配照id */
	private Long colPicId;
	/** 搭配主表id */
	private Long colId;
	/** 发布者id */
	private Long promulgatorId;
	/** 是否主图 */
	private Boolean coverFlag;
	/** 发布时间 */
	private Date addTime;
	/** 照片描述 */
	private String description;
	/** 搭配照地址 */
	private String picUrl;
	
	@Override
	public void init() {
		if (this.addTime == null)
			this.addTime = new Date();
	}

	public Long getColPicId() {
		return colPicId;
	}

	public void setColPicId(Long colPicId) {
		this.colPicId = colPicId;
	}

	public Long getColId() {
		return colId;
	}

	public void setColId(Long colId) {
		this.colId = colId;
	}

	public Long getPromulgatorId() {
		return promulgatorId;
	}

	public void setPromulgatorId(Long promulgatorId) {
		this.promulgatorId = promulgatorId;
	}

	public Boolean getCoverFlag() {
		return coverFlag;
	}

	public void setCoverFlag(Boolean coverFlag) {
		this.coverFlag = coverFlag;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

}
