package com.example.pavithra.credit;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="user.db";
    public static final String TABLE_NAME="registration";
    //public  static String USER_ID="userid";
    public  static String USER_NAME="username";
    public  static String USER_BALANCE="balance";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
	public void insert(){
		SQLiteDatabase db=this.getWritableDatabase();//String[] str={"Swami","Pavi"};
		ContentValues con=new ContentValues();
		con.put(USER_NAME,"Swami");
		con.put(USER_BALANCE,0);
		db.insert(TABLE_NAME,null,con);
    }
	
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE registration(username TEXT PRIMARY KEY,balance INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" +TABLE_NAME); //Drop older table if exists
        onCreate(db);
    }
}