package com.song.dapei.aphone.config;

import android.annotation.SuppressLint;
import android.os.Environment;

/**
 * 
 * @author songzigw
 *
 */
@SuppressLint("SdCardPath")
public class DapeiConstants {

	/** 存放发送图片的目录 */
	public static String DAPEI_PICTURE_PATH = Environment
			.getExternalStorageDirectory() + "/dapei/image/";

	/** 我的头像保存目录 */
	public static String MY_AVATAR_DIR = "/sdcard/dapei/avatar/";

	public static final int REQUESTCODE_UPLOADAVATAR_CAMERA = 1;// 拍照修改头像
	public static final int REQUESTCODE_UPLOADAVATAR_LOCATION = 2;// 本地相册修改头像
	public static final int REQUESTCODE_UPLOADAVATAR_CROP = 3;// 系统裁剪头像

	public static final int REQUESTCODE_TAKE_CAMERA = 0x000001;// 拍照
	public static final int REQUESTCODE_TAKE_LOCAL = 0x000002;// 本地图片
	public static final int REQUESTCODE_TAKE_LOCATION = 0x000003;// 位置
	public static final String EXTRA_STRING = "extra_string";

	/** 注册成功之后登陆页面退出 */
	public static final String ACTION_REGISTER_SUCCESS_FINISH = "register.success.finish";
}
