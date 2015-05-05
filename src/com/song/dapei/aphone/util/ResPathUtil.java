package com.song.dapei.aphone.util;

import com.song.dapei.aphone.config.ConfigManager;

public class ResPathUtil {

	public static String getUserAvatar(String photoPath) {
		ConfigManager cm = ConfigManager.getInstance();
		if (!photoPath.startsWith("http://")) {
			photoPath = cm.getResAccountUri() + photoPath;
		}
		return photoPath;
	}

}
