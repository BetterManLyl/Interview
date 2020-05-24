package com.demo.interview.data_store;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 文 件 名：SQLFunction
 * 创 建 人：李跃龙
 * 创建日期：2020/5/24 19:25
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class SQLFunction {

    static DBHelper helper;

    public static void initTable(Context context) {
        helper = new DBHelper(context);
        helper.getReadableDatabase();
    }

    /**
     * 【插入数据】
     **/
    public static void insert(Context context, Object[] data) {

        Log.i("TAG:", "插入数据到数据库表：person中:" + data.toString());

        DBManager sqlManager = new DBManager(context);
        helper = new DBHelper(context);
        helper.getWritableDatabase();
        String sql = "insert into person ( name , info ) values ( ? , ?)";
        Object[] bindArgs = data;
        sqlManager.updateSQLite(sql, bindArgs);
    }


    /**
     * 【模糊查询】
     **/
    public static ArrayList<HashMap<String, String>> query(Context context, String where1, String where2) {
        DBManager sqlManager = new DBManager(context);
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        String sql = "select * from person where name like ? and info like ?";
        if (where1 == null) {
            list = sqlManager.querySQLite(sql, new String[]{"%", "%"});
        } else {
            where1 = "%" + where1 + "%";
            where2 = "%" + where2 + "%";
            list = sqlManager.querySQLite(sql, new String[]{where1, where2});
        }

        Log.i("TAG:", "查询完毕，返回数据：" + list.size());

        return list;
    }

    /**
     * 【删除数据】
     **/
    public static void delete(Context context, Object[] data) {
        DBManager sqlmanager = new DBManager(context);
        String sql = "delete from person where _id =  ? ";
        sqlmanager.updateSQLite(sql, data);
    }

    /**
     * 【更新数据】
     **/
    public static void update(Context context, Object[] data) {
        helper = new DBHelper(context);
        helper.getReadableDatabase();
        DBManager sqlManager = new DBManager(context);
        String sql = "update person set name=? , info=? where _id=?";
        sqlManager.updateSQLite(sql, data);
    }

}
