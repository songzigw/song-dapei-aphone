package com.song.dapei.entity;

import java.util.Date;

import com.song.commons.entity.LazyLoadEntity;

public class ColType extends LazyLoadEntity implements java.io.Serializable {

	private static final long serialVersionUID = -8817169561753836634L;

	/** 搭配类别ID */
	private Long typeId;
	/** 搭配类别名称 */
	private String typeName;
	/** 搭配类别状态 */
	private Integer typeState;
	/** 添加时间 */
	private Date addTime;
	/** 搭配类别描述 */
	private String typeDesc;
	/** 图标 */
	private String pictureIco;
	/** 图标2 */
	private String pictureIco2;

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getTypeState() {
		return typeState;
	}

	public void setTypeState(Integer typeState) {
		this.typeState = typeState;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public String getPictureIco() {
		return pictureIco;
	}

	public void setPictureIco(String pictureIco) {
		this.pictureIco = pictureIco;
	}

	public String getPictureIco2() {
		return pictureIco2;
	}

	public void setPictureIco2(String pictureIco2) {
		this.pictureIco2 = pictureIco2;
	}

	@Override
	public void init() {
		if (this.addTime == null) {
			this.addTime = new Date();
		}
	}
}
