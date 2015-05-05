package com.song.dapei.entity;

import java.util.Date;

import com.song.commons.entity.LazyLoadEntity;

public class ColStatistics extends LazyLoadEntity implements java.io.Serializable {

	private static final long serialVersionUID = -1944467670048125091L;

	/** 搭配ID */
	private Long colId;
	/** 点攒数 */
	private Integer praiseCount;
	/** 评论数 */
	private Integer commentCount;
	/** 收藏数 */
	private Integer favoriteCount;
	/** 转发数 */
	private Integer forwardCount;
	/** 添加时间 */
	private Date addTime;
	
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

	public Integer getPraiseCount() {
		return praiseCount;
	}

	public void setPraiseCount(Integer praiseCount) {
		this.praiseCount = praiseCount;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Integer getFavoriteCount() {
		return favoriteCount;
	}

	public void setFavoriteCount(Integer favoriteCount) {
		this.favoriteCount = favoriteCount;
	}

	public Integer getForwardCount() {
		return forwardCount;
	}

	public void setForwardCount(Integer forwardCount) {
		this.forwardCount = forwardCount;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

}
