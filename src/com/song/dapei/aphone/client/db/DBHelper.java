package com.song.dapei.aphone.client.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	private static DBHelper mInstance;

	public static DBHelper getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new DBHelper(context);
		}
		return mInstance;
	}

	private DBHelper(Context context) {
		super(context, Database.DB_NAME, null, Database.DB_VERSION);
	}

	public void onCreate(SQLiteDatabase db) {
		StringBuffer sbu = new StringBuffer();
		sbu.append("create table "+Database.T.ACC_USER);
		sbu.append("( ");
		sbu.append(Database.UserF.SESSION_ID +" text, ");
		sbu.append(Database.UserF.USER_ID +" integer primary key, ");
		sbu.append(Database.UserF.ACCOUNT +" text, ");
		sbu.append(Database.UserF.NICK_NAME +" text, ");
		sbu.append(Database.UserF.USER_NAME +" text, ");
		sbu.append(Database.UserF.PHOTO_PATH +" text, ");
		sbu.append(Database.UserF.SUMMARY +" text, ");
		sbu.append(Database.UserF.EADDRESS +" text, ");
		sbu.append(Database.UserF.BIRTHDAY_YEAR +" integer, ");
		sbu.append(Database.UserF.BIRTHDAY_MONTH +" integer, ");
		sbu.append(Database.UserF.BIRTHDAY_DAY +" integer, ");
		sbu.append(Database.UserF.EN_EMAIL +" text, ");
		sbu.append(Database.UserF.SEX +" integer, ");
		sbu.append(Database.UserF.RONG_TOKEN +" text, ");
		sbu.append(Database.UserF.ADD_TIME +" text");
		sbu.append(" )");
		
		db.execSQL(sbu.toString());
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		 String sql = "DROP TABLE IF EXISTS " + Database.T.ACC_USER;
		 db.execSQL(sql);
		 onCreate(db);
	}
}
