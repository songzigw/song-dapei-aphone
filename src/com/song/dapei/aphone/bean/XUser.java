package com.song.dapei.aphone.bean;

import com.song.account.entity.User;

/**
 * 重载User对象：若还有其他需要增加的属性可在此添加
 * 
 * @author songzigw
 *
 */
public class XUser extends User {

	private static final long serialVersionUID = 1941505327759442595L;

	/**
	 * 显示数据拼音的首字母
	 */
	private String sortLetters;

	public String getSortLetters() {
		return sortLetters;
	}

	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}

}
