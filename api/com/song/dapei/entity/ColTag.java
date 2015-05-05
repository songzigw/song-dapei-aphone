package com.song.dapei.entity;

import java.util.Date;

import com.song.commons.entity.LazyLoadEntity;

public class ColTag extends LazyLoadEntity implements java.io.Serializable {

	private static final long serialVersionUID = -2537746561805716013L;

	/** 标签ID */
	private Long tagId;
	/** 标签名称 */
	private String tagName;
	/** 标签类别ID */
	private Long typeId;
	/** 创建时间 */
	private Date addTime;
	/** 父级标签ID */
	private Long parentId;
	
	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Override
	public void init() {
		if (this.addTime == null)
			this.addTime = new Date();
	}

}
