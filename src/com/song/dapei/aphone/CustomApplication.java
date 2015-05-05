package com.song.dapei.aphone;

//import io.rong.imkit.RongIM;

import java.io.File;

import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.song.account.entity.User;
import com.song.dapei.aphone.client.SSOAuth;
import com.song.dapei.aphone.util.SharePreferenceUtil;
//import cn.bmob.im.BmobChat;
//import cn.bmob.im.BmobUserManager;
//import cn.bmob.im.bean.BmobChatUser;
//import cn.bmob.im.db.BmobDB;
//import cn.bmob.v3.datatype.BmobGeoPoint;
//
//import com.baidu.location.BDLocation;
//import com.baidu.location.BDLocationListener;
//import com.baidu.location.LocationClient;
//import com.baidu.mapapi.SDKInitializer;
//import com.bmob.im.demo.util.CollectionUtils;

/**
 * 自定义全局Applcation类
 * @author songzigw
 *
 */
public class CustomApplication extends Application {

	public static CustomApplication mInstance;

	// public LocationClient mLocationClient;
	// public MyLocationListener mMyLocationListener;

	// public static BmobGeoPoint lastPoint = null;// 上一次定位到的经纬度

	@Override
	public void onCreate() {
		super.onCreate();
		// 初始化RongCloud。
        //RongIM.init(this);
		// 是否开启debug模式--默认开启状态
		// BmobChat.DEBUG_MODE = true;
		mInstance = this;
		init();
	}

	private void init() {
		mMediaPlayer = MediaPlayer.create(this, R.raw.notify);
		mNotificationManager = (NotificationManager) getSystemService(android.content.Context.NOTIFICATION_SERVICE);
		initImageLoader(getApplicationContext());
		initBaidu();
	}

	/**
	 * 初始化百度相关sdk initBaidumap
	 * 
	 * @Title: initBaidumap
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	private void initBaidu() {
		// 初始化地图Sdk
		// SDKInitializer.initialize(this);
		// 初始化定位sdk
		initBaiduLocClient();
	}

	/**
	 * 初始化百度定位sdk
	 * 
	 * @Title: initBaiduLocClient
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	private void initBaiduLocClient() {
		// mLocationClient = new LocationClient(this.getApplicationContext());
		// mMyLocationListener = new MyLocationListener();
		// mLocationClient.registerLocationListener(mMyLocationListener);
	}

	/**
	 * 实现实位回调监听
	 */
	// public class MyLocationListener implements BDLocationListener {
	//
	// @Override
	// public void onReceiveLocation(BDLocation location) {
	// // Receive Location
	// double latitude = location.getLatitude();
	// double longtitude = location.getLongitude();
	// if (lastPoint != null) {
	// if (lastPoint.getLatitude() == location.getLatitude()
	// && lastPoint.getLongitude() == location.getLongitude()) {
	// // BmobLog.i("两次获取坐标相同");// 若两次请求获取到的地理位置坐标是相同的，则不再定位
	// mLocationClient.stop();
	// return;
	// }
	// }
	// lastPoint = new BmobGeoPoint(longtitude, latitude);
	// }
	// }

	/** 初始化ImageLoader */
	public static void initImageLoader(Context context) {
		File cacheDir = StorageUtils.getOwnCacheDirectory(context,
				"bmobim/Cache");// 获取到缓存的目录地址
		// 创建配置ImageLoader(所有的选项都是可选的,只使用那些你真的想定制)，这个可以设定在APPLACATION里面，设置为全局的配置参数
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context)
				// 线程池内加载的数量
				.threadPoolSize(3).threadPriority(Thread.NORM_PRIORITY - 2)
				.memoryCache(new WeakMemoryCache())
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				// 将保存的时候的URI名称用MD5 加密
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.discCache(new UnlimitedDiscCache(cacheDir))// 自定义缓存路径
				// .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
				.writeDebugLogs() // Remove for release app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);// 全局初始化此配置
	}

	public static CustomApplication getInstance() {
		return mInstance;
	}

	// 单例模式，才能及时返回数据
	SharePreferenceUtil mSpUtil;
	public static final String PREFERENCE_NAME = "sharedinfo_";

	public synchronized SharePreferenceUtil getSpUtil() {
		if (mSpUtil == null) {
			User u = SSOAuth.getInstance(getApplicationContext()).getCurrUser();
			if (u != null) {
				String sharedName = PREFERENCE_NAME + u.getUserId();
				mSpUtil = new SharePreferenceUtil(this, sharedName);
			}
		}
		return mSpUtil;
	}

	NotificationManager mNotificationManager;

	public NotificationManager getNotificationManager() {
		if (mNotificationManager == null)
			mNotificationManager = (NotificationManager) getSystemService(android.content.Context.NOTIFICATION_SERVICE);
		return mNotificationManager;
	}

	MediaPlayer mMediaPlayer;

	public synchronized MediaPlayer getMediaPlayer() {
		if (mMediaPlayer == null)
			mMediaPlayer = MediaPlayer.create(this, R.raw.notify);
		return mMediaPlayer;
	}

	public final String PREF_LONGTITUDE = "longtitude";// 经度
	private String longtitude = "";

	/**
	 * 获取经度
	 * 
	 * @return
	 */
	public String getLongtitude() {
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		longtitude = preferences.getString(PREF_LONGTITUDE, "");
		return longtitude;
	}

	/**
	 * 设置经度
	 * 
	 * @param pwd
	 */
	public void setLongtitude(String lon) {
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		SharedPreferences.Editor editor = preferences.edit();
		if (editor.putString(PREF_LONGTITUDE, lon).commit()) {
			longtitude = lon;
		}
	}

	public final String PREF_LATITUDE = "latitude";// 经度
	private String latitude = "";

	/**
	 * 获取纬度
	 * 
	 * @return
	 */
	public String getLatitude() {
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		latitude = preferences.getString(PREF_LATITUDE, "");
		return latitude;
	}

	/**
	 * 设置维度
	 * 
	 * @param pwd
	 */
	public void setLatitude(String lat) {
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		SharedPreferences.Editor editor = preferences.edit();
		if (editor.putString(PREF_LATITUDE, lat).commit()) {
			latitude = lat;
		}
	}

	/**
	 * 退出登录,清空缓存数据
	 */
	public void logout() {
		SSOAuth.getInstance(getApplicationContext()).logout();
		setLatitude(null);
		setLongtitude(null);
	}

}
