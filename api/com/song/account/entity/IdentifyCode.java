package com.song.account.entity;

import java.util.Date;

import com.song.commons.entity.LazyLoadEntity;

/**
 * 数据验证码
 * @author 张松
 *
 */
public class IdentifyCode extends LazyLoadEntity {

	private static final long serialVersionUID = -3470626013864841738L;
	
	/** 主键ID */
	private Long icId;
	/** 添加时间 */
	private Date addTime;
	/** 待验证数据ID */
	private Long dataId;
	/** 表名称 */
	private String tableName;
	/** 字段名称 */
	private String fieldName;
	/** 验证码 */
	private String code;
	/** 验证起始时间 */
	private Date startTime;
	/** 验证结束时间 */
	private Date endTime;
	/** 验证提交时间 */
	private Date verifyTime;
	/** 验证状态（0,未验证;1验证成功;2;验证失败） */
	private VerifyState verifyState;
	/** 内容描述 */
	private String textDesc;
	
	/**
	 * 验证状态
	 * 
	 * @author 张松
	 * 
	 */
	public static enum VerifyState {
		/** 未验证 */
		NO_VERIFY(0),
		/** 验证成功 */
		SUCCESS(1),
		/** 验证失败 */
		FAILING(2);

		private final int value;

		private VerifyState(int v) {
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

		public static VerifyState getInstance(int value) {
			for (VerifyState cc : VerifyState.values()) {
				if (cc.getValue() == value) {
					return cc;
				}
			}
			return null;
		}
	}
	
	@Override
	public void init() {
		if (this.addTime == null)
			this.addTime = new Date();
		if (this.verifyState == null)
			this.verifyState = VerifyState.NO_VERIFY;
	}

	public Long getIcId() {
		return icId;
	}

	public void setIcId(Long icId) {
		this.icId = icId;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Long getDataId() {
		return dataId;
	}

	public void setDataId(Long dataId) {
		this.dataId = dataId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getVerifyTime() {
		return verifyTime;
	}

	public void setVerifyTime(Date verifyTime) {
		this.verifyTime = verifyTime;
	}

	public VerifyState getVerifyState() {
		return verifyState;
	}

	public void setVerifyState(VerifyState verifyState) {
		this.verifyState = verifyState;
	}

	public String getTextDesc() {
		return textDesc;
	}

	public void setTextDesc(String textDesc) {
		this.textDesc = textDesc;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

}
