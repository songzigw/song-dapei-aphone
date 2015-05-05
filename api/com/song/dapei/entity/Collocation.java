package com.song.dapei.entity;

import java.util.Date;

import com.song.commons.entity.LazyLoadEntity;
import com.song.dapei.entity.ColCheck.CheckCode;

/**
 * 主（要）搭配类
 * @author 张松
 *
 */
public class Collocation extends LazyLoadEntity implements java.io.Serializable {

	private static final long serialVersionUID = 759608450286330655L;

	/** 搭配ID */
	private Long colId;
	/** 发布者ID */
	private Long promulgatorId;
	/** 搭配描述 */
	private String description;
	/** 搭配照主图ID */
	private Long colPicId;
	/** 搭配照主图地址 */
	private String colPicUrl;
	/** 发布时间 */
	private Date addTime;
	/** 是否删除 */
	private Boolean delFlag;
	/** 搭配标签 */
	private String tags;
	/** 是否是本人照 */
	private Boolean meFlag;
	/** 被转发搭配ID */
	private Long forwardColId;
	/** 原始搭配ID */
	private Long  firstColId;
	/** 审核状态码 */
	private CheckCode checkCode;
	
	/** 统计信息 */
	private ColStatistics colStatistics;
	
	@Override
	public void init() {
		if (this.addTime == null)
			this.addTime = new Date();
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getColPicId() {
		return colPicId;
	}

	public void setColPicId(Long colPicId) {
		this.colPicId = colPicId;
	}

	public String getColPicUrl() {
		return colPicUrl;
	}

	public void setColPicUrl(String colPicUrl) {
		this.colPicUrl = colPicUrl;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Boolean getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Boolean getMeFlag() {
		return meFlag;
	}

	public void setMeFlag(Boolean meFlag) {
		this.meFlag = meFlag;
	}

	public Long getForwardColId() {
		return forwardColId;
	}

	public void setForwardColId(Long forwardColId) {
		this.forwardColId = forwardColId;
	}

	public Long getFirstColId() {
		return firstColId;
	}

	public void setFirstColId(Long firstColId) {
		this.firstColId = firstColId;
	}

	public CheckCode getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(CheckCode checkCode) {
		this.checkCode = checkCode;
	}

	public ColStatistics getColStatistics() {
		return colStatistics;
	}

	public void setColStatistics(ColStatistics colStatistics) {
		this.colStatistics = colStatistics;
	}

}
