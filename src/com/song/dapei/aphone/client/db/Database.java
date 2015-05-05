package com.song.dapei.aphone.client.db;

import com.song.commons.dao.Fields;
import com.song.commons.dao.Tables;

public abstract class Database {

	public static final int DB_VERSION = 1;
	public static final String DB_NAME = "song_dapei.db";
	
	public static enum T implements Tables {
		/** 用户表 */
		ACC_USER,
	}
	
	/**
	 * 用户表的字段
	 * 
	 * @author 张松
	 * 
	 */
	public static enum UserF implements Fields {
		/** 客户端唯一标示 */
		SESSION_ID,
		/** 用户ID */
		USER_ID,
		/** 账号 */
		ACCOUNT,
		/** 昵称 */
		NICK_NAME,
		/** 用户姓名 */
		USER_NAME,
		/** 添加时间 */
		ADD_TIME,
		/** 头像路径 */
		PHOTO_PATH,
		/** 性别 */
		SEX,
		/** Enable(激活的) */
		EN_EMAIL,
		/** 生日-年 */
		BIRTHDAY_YEAR,
		/** 生日-月 */
		BIRTHDAY_MONTH,
		/** 生日-日 */
		BIRTHDAY_DAY,
		/** 简介 */
		SUMMARY,
		/** E通信地址（聊天工具） */
		EADDRESS,
		/** 用户在RongCloud服务器上的Token */
		RONG_TOKEN,
	}	
}
