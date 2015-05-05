package com.song.dapei.entity;

import java.util.Date;

import com.song.commons.entity.LazyLoadEntity;

public class ColCheck extends LazyLoadEntity implements java.io.Serializable {

	private static final long serialVersionUID = 8301655108594492012L;

	/**
	 * 操作状态码
	 * 
	 * @author 张松
	 * 
	 */
	public static enum CheckCode {
		/** 待审核 */
		CHECK_START(0),
		/** 已删除 */
		DEL_YES(1),
		/** 已上线 */
		ONLINE(2),
		/** 已下线 */
		DOWNLINE(3),
		/** 审核不通过 */
		CHECK_BAD(4);

		private final int value;

		private CheckCode(int v) {
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

		public static CheckCode getInstance(int value) {
			for (CheckCode cc : CheckCode.values()) {
				if (cc.getValue() == value) {
					return cc;
				}
			}
			return null;
		}
	}
	
	/** 操作ID */
	private Long colCheckId;
	/** 搭配ID */
	private Long colId;
	/** 操作者id */
	private Long oprId;
	/** 审核状态码 */
	private CheckCode checkCode;
	/** 审核描述 */
	private String chcekDesc;
	/** 添加时间 */
	private Date addTime;
	
	@Override
	public void init() {
		if (this.addTime == null)
			this.addTime = new Date();
	}

	public Long getColCheckId() {
		return colCheckId;
	}

	public void setColCheckId(Long colCheckId) {
		this.colCheckId = colCheckId;
	}

	public Long getColId() {
		return colId;
	}

	public void setColId(Long colId) {
		this.colId = colId;
	}

	public Long getOprId() {
		return oprId;
	}

	public void setOprId(Long oprId) {
		this.oprId = oprId;
	}

	public CheckCode getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(CheckCode checkCode) {
		this.checkCode = checkCode;
	}

	public String getChcekDesc() {
		return chcekDesc;
	}

	public void setChcekDesc(String chcekDesc) {
		this.chcekDesc = chcekDesc;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

}
