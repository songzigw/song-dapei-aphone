package com.song.dapei.aphone.client.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.song.account.entity.User;
import com.song.dapei.aphone.client.db.Database.UserF;

public class DapeiDB {

	private static DapeiDB instance;

	private Context context;

	private List<User> contactList = new ArrayList<User>();

	public static DapeiDB getInstance(Context context) {
		if (instance == null) {
			instance = new DapeiDB(context);
		}
		return instance;
	}

	private DapeiDB(Context context) {
		if (this.context == null) {
			this.context = context;
		}
	}

	/**
	 * 获取本地数据库中的用户
	 * 
	 * @return
	 */
	public User getUser(String sessionId) {
		if (sessionId == null) {
			return null;
		}

		SQLiteDatabase db = DBHelper.getInstance(context).getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from " + Database.T.ACC_USER
				+ " where " + Database.UserF.SESSION_ID + "=?",
				new String[] { sessionId });
		User u = null;
		if (cursor.moveToFirst()) {
			long userId = cursor.getLong(cursor
					.getColumnIndex(Database.UserF.USER_ID.name()));
			String account = cursor.getString(cursor
					.getColumnIndex(Database.UserF.ACCOUNT.name()));
			String nickName = cursor.getString(cursor
					.getColumnIndex(Database.UserF.NICK_NAME.name()));
			String userName = cursor.getString(cursor
					.getColumnIndex(Database.UserF.USER_NAME.name()));
			String photoPath = cursor.getString(cursor
					.getColumnIndex(Database.UserF.PHOTO_PATH.name()));
			String summary = cursor.getString(cursor
					.getColumnIndex(Database.UserF.SUMMARY.name()));
			String eaddress = cursor.getString(cursor
					.getColumnIndex(Database.UserF.EADDRESS.name()));
			int birthdayYear = cursor.getInt(cursor
					.getColumnIndex(UserF.BIRTHDAY_YEAR.name()));
			int birthdayMonth = cursor.getInt(cursor
					.getColumnIndex(UserF.BIRTHDAY_MONTH.name()));
			int birthdayDay = cursor.getInt(cursor
					.getColumnIndex(UserF.BIRTHDAY_DAY.name()));
			// String addTime = cursor.getString(cursor
			// .getColumnIndex(UserF.ADD_TIME.name()));
			String enEmail = cursor.getString(cursor
					.getColumnIndex(UserF.EN_EMAIL.name()));
			int sex = cursor.getInt(cursor.getColumnIndex(UserF.SEX.name()));
			String rongToken = cursor.getString(cursor
					.getColumnIndex(UserF.RONG_TOKEN.name()));
			// String sessionId =
			// cursor.getString(cursor.getColumnIndex(UserF.SESSION_ID.name()));
			u = new User();
			u.setUserId(userId);
			u.setAccount(account);
			u.setNickName(nickName);
			u.setUserName(userName);
			u.setPhotoPath(photoPath);
			u.setSummary(summary);
			u.setEaddress(eaddress);
			u.setBirthdayYear(birthdayYear);
			u.setBirthdayMonth(birthdayMonth);
			u.setBirthdayDay(birthdayDay);
			// u.setAddTime(addTime);
			u.setEnEmail(enEmail);
			u.setSex(sex);
			u.setRongToken(rongToken);
		}
		cursor.close();

		return u;
	}

	public void insert(String sessionId, User u) {
		deleteUser(sessionId);
		deleteUser(u.getUserId());
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		StringBuffer fields = new StringBuffer();
		fields.append(UserF.SESSION_ID).append(",");
		fields.append(UserF.USER_ID).append(",");
		fields.append(UserF.USER_NAME).append(",");
		fields.append(UserF.ADD_TIME).append(",");
		fields.append(UserF.ACCOUNT).append(",");
		fields.append(UserF.NICK_NAME).append(",");
		fields.append(UserF.PHOTO_PATH).append(",");
		fields.append(UserF.SEX).append(",");
		fields.append(UserF.BIRTHDAY_YEAR).append(",");
		fields.append(UserF.BIRTHDAY_MONTH).append(",");
		fields.append(UserF.BIRTHDAY_DAY).append(",");
		fields.append(UserF.SUMMARY).append(",");
		fields.append(UserF.EADDRESS).append(",");
		fields.append(UserF.EN_EMAIL).append(",");
		fields.append(UserF.RONG_TOKEN).append("");
		db.execSQL(
				"insert into " + Database.T.ACC_USER + "(" + fields.toString()
						+ ")" + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
				new Object[] { sessionId, u.getUserId(), u.getUserName(),
						u.getAddTime(), u.getAccount(), u.getNickName(),
						u.getPhotoPath(), u.getSex(), u.getBirthdayYear(),
						u.getBirthdayMonth(), u.getBirthdayDay(),
						u.getSummary(), u.getEaddress(), u.getEnEmail(),
						u.getRongToken() });
	}

	public void updateUser(String sessionId, User currUser) {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();

		ContentValues cv = new ContentValues();
		cv.put(Database.UserF.USER_ID.name(), currUser.getUserId());
		cv.put(Database.UserF.ACCOUNT.name(), currUser.getAccount());
		cv.put(Database.UserF.NICK_NAME.name(), currUser.getNickName());
		cv.put(Database.UserF.USER_NAME.name(), currUser.getUserName());
		cv.put(Database.UserF.PHOTO_PATH.name(), currUser.getPhotoPath());
		cv.put(Database.UserF.SUMMARY.name(), currUser.getSummary());
		cv.put(Database.UserF.EADDRESS.name(), currUser.getEaddress());
		// cv.put(Database.UserF.ADD_TIME.name(), currUser.getAddTime());
		cv.put(Database.UserF.BIRTHDAY_DAY.name(), currUser.getBirthdayDay());
		cv.put(Database.UserF.BIRTHDAY_MONTH.name(),
				currUser.getBirthdayMonth());
		cv.put(Database.UserF.BIRTHDAY_YEAR.name(), currUser.getBirthdayYear());
		cv.put(Database.UserF.EN_EMAIL.name(), currUser.getEnEmail());
		cv.put(Database.UserF.SEX.name(), currUser.getSex());
		cv.put(Database.UserF.RONG_TOKEN.name(), currUser.getRongToken());

		db.update(Database.T.ACC_USER.name(), cv, Database.UserF.SESSION_ID
				+ "=?", new String[] { sessionId });
	}

	public void deleteUser(String sessionId) {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		db.delete(Database.T.ACC_USER.name(), Database.UserF.SESSION_ID + "=?",
				new String[] { sessionId });
	}

	public void deleteUser(long userId) {
		SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
		db.delete(Database.T.ACC_USER.name(), Database.UserF.USER_ID + "=?",
				new String[] { userId + "" });
	}

	public List<User> getBlackList() {
		return null;
	}

	/**
	 * 是否有新的好友请求
	 * 
	 * @return
	 */
	public boolean hasNewInvite() {
		return false;
	}

	public List<User> getContactList() {
		return contactList;
	}

	public void setContactList(List<User> contactList) {
		if (contactList == null) {
			contactList = new ArrayList<User>();
		}
		this.contactList = contactList;
	}

	public void updateUserRongToken(String sessionId, String rongToken) {

	}
}
