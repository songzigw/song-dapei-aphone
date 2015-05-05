package com.song.dapei.entity.x;

import java.util.List;

import com.song.commons.api.Result;
import com.song.dapei.entity.ColPicture;
import com.song.dapei.entity.Collocation;

public class XCollocation extends Result {

	private static final long serialVersionUID = 1777865823651353821L;

	private Collocation collocation;

	private List<ColPicture> colPictureList;

	public Collocation getCollocation() {
		return collocation;
	}

	public void setCollocation(Collocation collocation) {
		this.collocation = collocation;
	}

	public List<ColPicture> getColPictureList() {
		return colPictureList;
	}

	public void setColPictureList(List<ColPicture> colPictureList) {
		this.colPictureList = colPictureList;
	}

}
