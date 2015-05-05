package com.song.dapei.entity;

import java.util.Date;

import com.song.commons.entity.LazyLoadEntity;

/**
 * 搭配评论
 * @author songzigw
 *
 */
public class ColComment extends LazyLoadEntity implements java.io.Serializable {

	private static final long serialVersionUID = -3532014846666970996L;

	/** 主键ID */
	private Long colComId;
	/** 用户id */
	private Long userId;
	/** 搭配id */
	private Long colId;
	/** 添加时间 */
	private Date addTime;
	/** 评语 */
	private String comText;

	public Long getColComId() {
		return colComId;
	}

	public void setColComId(Long colComId) {
		this.colComId = colComId;
	}

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

	public String getComText() {
		return comText;
	}

	public void setComText(String comText) {
		this.comText = comText;
	}

	@Override
	public void init() {
		if (this.addTime == null) {
			this.addTime = new Date();
		}
	}
}
