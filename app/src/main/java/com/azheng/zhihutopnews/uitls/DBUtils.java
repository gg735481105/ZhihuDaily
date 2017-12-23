package com.azheng.zhihutopnews.uitls;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.azheng.zhihutopnews.bean.CollectCfg;
import com.azheng.zhihutopnews.config.Config;

import java.util.ArrayList;
import java.util.List;


public class DBUtils {
    public static final String CREATE_TABLE_IF_NOT_EXISTS = "create table if not exists %s " +
            "(id integer  primary key autoincrement,key text unique,is_read integer)";
    public static final String CREATE_TABLE_IF_NOT_COLLECT = "create table if not exists %s " +
            "(id integer  primary key autoincrement,key text unique,is_collect integer,title TEXT,date TEXT)";

    private static DBUtils sDBUtis;
    private SQLiteDatabase mSQLiteDatabase;

    private DBUtils(Context context) {
        mSQLiteDatabase = new DBHelper(context, Config.DB__IS_READ_NAME + ".db").getWritableDatabase();
    }

    public static synchronized DBUtils getDB(Context context) {
        if (sDBUtis == null)
            sDBUtis = new DBUtils(context);
        return sDBUtis;
    }


    public void insertHasRead(String table, String key, int value) {
        Cursor cursor = mSQLiteDatabase.query(table, null, null, null, null, null, "id asc");
        if (cursor.getCount() > 200 && cursor.moveToNext()) {
            mSQLiteDatabase.delete(table, "id=?", new String[]{String.valueOf(cursor.getInt(cursor.getColumnIndex("id")))});
        }
        cursor.close();
        ContentValues contentValues = new ContentValues();
        contentValues.put("key", key);
        contentValues.put("is_read", value);
        mSQLiteDatabase.insertWithOnConflict(table, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
    }
    //是否已经阅读
    public boolean isRead(String table, String key, int value) {
        boolean isRead = false;
        Cursor cursor = mSQLiteDatabase.query(table, null, "key=?", new String[]{key}, null, null, null);
        if (cursor.moveToNext() && (cursor.getInt(cursor.getColumnIndex("is_read")) == value)) {
            isRead = true;
        }
        cursor.close();
        return isRead;
    }
    public void setCollect(String table, String key, int value,String title,String date){
        //如果已经存在则不写入
        Cursor cursor = mSQLiteDatabase.query(table, null, "key=?", new String[]{key}, null, null, null);
        if (cursor.moveToFirst() && cursor.getString(cursor.getColumnIndex("id")).equals(key)) {
            return;
        }
        cursor.close();
        ContentValues contentValues = new ContentValues();
        contentValues.put("key", key);
        contentValues.put("is_collect", value);
        contentValues.put("title", title);
        contentValues.put("date", date);
        try {
            mSQLiteDatabase.insertWithOnConflict(table, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean isCollect(String table, String key) {
        boolean isCoolect = false;
        Cursor cursor = mSQLiteDatabase.query(table, null, "key=?", new String[]{key}, null, null, null);
        if (cursor.moveToNext() && (cursor.getInt(cursor.getColumnIndex("is_collect")) == 1)) {
            isCoolect = true;
        }
        cursor.close();
        return isCoolect;
    }

    /*
    * 当用户将收藏变为非收藏时，删除该条数据库
    */
    public void deleteCollect(String table, String key){
        Cursor cursor = mSQLiteDatabase.query(table, null, "key=?", new String[]{key}, null, null, null);
        if (cursor.moveToFirst()){
            mSQLiteDatabase.delete(table, "key=?", new String[]{String.valueOf(cursor.getInt(cursor.getColumnIndex("key")))});
        }
        cursor.close();
    }

    /*
    * 返回收藏信息
    * id，is_collect，title，time
    */
    public List<CollectCfg> getCollect(String table){
        List<CollectCfg> mCollectList = new ArrayList<>();
        Cursor cursor = mSQLiteDatabase.query(table, null, null, null, null, null, "id desc");
        while (cursor.moveToNext()){
            CollectCfg item = new CollectCfg();
            String key = cursor.getString(cursor.getColumnIndex("key"));
            item.setId(key);
            int iscollect = cursor.getInt(cursor.getColumnIndex("is_collect"));
            item.setIsCollect(iscollect);
            String title = cursor.getString(cursor.getColumnIndex("title"));
            item.setTitle(title);
            String time = cursor.getString(cursor.getColumnIndex("date"));
            item.setTime(time);
            mCollectList.add(item);
        }
        cursor.close();
        return mCollectList;
    }

    public class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name) {
            super(context, name, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(String.format(CREATE_TABLE_IF_NOT_EXISTS, Config.ZHIHU));
            db.execSQL(String.format(CREATE_TABLE_IF_NOT_EXISTS, Config.TOPNEWS));
            db.execSQL(String.format(CREATE_TABLE_IF_NOT_COLLECT, Config.COLLECT));
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
