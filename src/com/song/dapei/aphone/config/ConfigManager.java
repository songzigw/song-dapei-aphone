package com.song.dapei.aphone.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.song.dapei.aphone.CustomApplication;

/**
 * 属性管理器
 * 
 * @author 张松
 * 
 */
public class ConfigManager {

	/** 搭配服务API网址 */
	private static final String WEBSITE_DAPEI_API = "website.dapei.api";
	/** 账号系统API网址 */
	private static final String WEBSITE_ACCOUNT_API = "website.account.api";
	/** 账号系统资源文件 */
	private static final String RES_ACCOUNT_URI = "res.account.uri";
	/** 账号系统URI网址 */
	private static final String WEBSITE_ACCOUNT_URI = "website.account.uri";
	/** 搭配网运营平台URI网址 */
	private static final String WEBSITE_OPNDP_URI = "website.opndp.uri";
	/** 搭配网URI网站 */
	private static final String WEBSITE_DAPEI_URI = "website.dapei.uri";
	/** 属性文件全名 */
	private static final String PFILE = "basic-config-account.properties";

	/** 本类存在的唯一实例 */
	private static ConfigManager instance;

	/** 属性文件的文件对象 */
	// private File file;

	/** 属性文件的最后修改日期 */
	// private long lastModifiedTime = 0;

	/** 属性文件对应的属性对象 */
	private Properties props;

	private ConfigManager() {
		try {
			props = new Properties();
			InputStream in = CustomApplication.getInstance().getResources().getAssets()
					.open(PFILE);
			props.load(in);
		} catch (IOException e) {
		}
	}

	/**
	 * 静态工厂方法
	 * 
	 * @return
	 */
	public static ConfigManager getInstance() {
		if (instance == null) {
			instance = new ConfigManager();
		}
		return instance;
	}

	/**
	 * 读取一个特定的属性项
	 * 
	 * @param key
	 * @return
	 */
	public final Object getConfigItem(String key) {
		return props.getProperty(key);
	}

	/**
	 * 获取账号系统资源文件
	 * 
	 * @return
	 */
	public String getResAccountUri() {
		return (String) this.getConfigItem(RES_ACCOUNT_URI);
	}

	/**
	 * 获取账号系统URI网址
	 * 
	 * @return
	 */
	public String getWebsiteAccountUri() {
		return (String) this.getConfigItem(WEBSITE_ACCOUNT_URI);
	}

	/**
	 * 获取运营平台URI网址
	 * 
	 * @return
	 */
	public String getWebsiteOpndpUri() {
		return (String) this.getConfigItem(WEBSITE_OPNDP_URI);
	}

	public String getWebsiteDapeiUri() {
		return (String) this.getConfigItem(WEBSITE_DAPEI_URI);
	}
	
	public String getWebsiteAccountApi() {
		return (String) this.getConfigItem(WEBSITE_ACCOUNT_API);
	}
	
	public String getWebsiteDapeiApi() {
		return (String) this.getConfigItem(WEBSITE_DAPEI_API);
	}
}
