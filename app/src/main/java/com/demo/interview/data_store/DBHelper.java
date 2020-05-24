package com.demo.interview.data_store;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;

/**
 * 文 件 名：DBHelper
 * 创 建 人：李跃龙
 * 创建日期：2020/5/24 19:17
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class DBHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "test.db";  //数据库名字
    private static final int DATABASE_VERSION = 1;         //数据库版本号

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    /**
     * 创建数据库表：person
     * _id为主键，自增
     **/
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        LogUtils.d("创建person数据库表");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS person(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " name VARCHAR,info TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase sqLiteDatabase) {
        super.onOpen(sqLiteDatabase);
    }
}
