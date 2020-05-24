package com.demo.interview.data_store;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.util.ToastUtils;
import com.demo.interview.R;
import com.demo.interview.base.BaseActivity;
import com.demo.interview.sqlite.Student;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.litepal.LitePal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 文 件 名：DataStoreActivity
 * 创 建 人：李跃龙
 * 创建日期：2020/5/24 18:20
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：数据存储
 */
public class DataStoreActivity extends BaseActivity implements View.OnClickListener {

    private EditText editText;
    private FileOutputStream fileOutputStream;

    @Override
    public int getLayoutId() {
        return R.layout.activity_data_store;
    }

    @Override
    public void initView() {

        findViewById(R.id.btn_save_file).setOnClickListener(this);
        findViewById(R.id.btn_save_jpeg).setOnClickListener(this);
        findViewById(R.id.btn_write_shared).setOnClickListener(this);
        findViewById(R.id.btn_read_shared).setOnClickListener(this);
        findViewById(R.id.btn_sqlite).setOnClickListener(this);
        findViewById(R.id.btn_litePal).setOnClickListener(this);
        editText = findViewById(R.id.ed_share);

    }

    @SuppressLint("CheckResult")
    @Override
    public void initData() {
        //请求读写权限
        new RxPermissions(this)
                .requestEachCombined(Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(permission -> {
                    if (!permission.granted) {
                        finish();
                    }
                });

        SQLFunction.initTable(this);
        SQLiteDatabase db = LitePal.getDatabase();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save_file:
                saveFile();
                break;
            case R.id.btn_save_jpeg:
                break;
            case R.id.btn_write_shared:
                writeShared(editText.getText().toString());
                break;
            case R.id.btn_read_shared:
                ToastUtils.showShort(readShared());
                break;
            case R.id.btn_sqlite:
                DB_i_u_d_s();
                break;
            case R.id.btn_litePal:
                litePal();
                break;
            default:
                break;
        }
    }

    private void saveFile() {
        //如果有该文件夹  interview  可以不判断file文件是否存在
        File file = new File(Environment.getExternalStorageDirectory() + "/interview/", "test.txt");
        try {
            fileOutputStream = new FileOutputStream(file, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //        try {
//            fileOutputStream = new FileOutputStream(file, true);
//            String txt = "aaaaa";
//            int length = txt.getBytes().length;
//            fileOutputStream.write(txt.getBytes(), 0, length);
//            fileOutputStream.flush();
//        } catch (Exception e) {
//            LogUtils.e("error:" + e.getMessage());
//        } finally {
//            try {
//                fileOutputStream.close();
//            } catch (IOException e) {
//                LogUtils.e("error:" + e.getMessage());
//            }
//        }

        AssetManager assetManager = getAssets();
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open("text.txt");
            fileOutputStream = new FileOutputStream(file);
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(bytes, 0, 1024)) != -1) {
                fileOutputStream.write(bytes, 0, bytes.length);
                fileOutputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveImg() {

    }

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private void writeShared(String txt) {
        if (null == sharedPreferences || null == editor) {
            sharedPreferences = this.getSharedPreferences("test", MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }
        editor.putString("test", txt);
        editor.commit();
    }

    private String readShared() {
        return sharedPreferences.getString("test", "没有数据");
    }

    /**
     * 【操作数据库的方法】
     **/
    private void DB_i_u_d_s() {
        SQLFunction function = new SQLFunction();

        /**
         Log.i("TAG:","插入数据！");
         Object[] data = {"root","123456"};
         function.insert(MainActivity.this,data);
         */


        Log.i("TAG:", "通过id来修改数据！");
        String name = "sale";
        String info = "TestSale";
        int _id = 7;
        Object[] data = {name, info, _id};
        function.update(this, data);


//        Log.i("TAG:", "通过id来删除数据！");
//        int d_id = 2;
//        Object[] did = {d_id};
//        function.delete(this, did);

        Log.i("TAG:", "查询数据！");
        String where1 = null;
        String where2 = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        list = function.query(this, where1, where2);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Log.i("TAG:", "从数据库中拿到的数据 _ID为：" + list.get(i).get("_id"));
                Log.i("TAG:", "从数据库中拿到的数据name为：" + list.get(i).get("name"));
                Log.i("TAG:", "从数据库中拿到的数据info为：" + list.get(i).get("info"));
            }
        } else {
            Log.i("TAG:", "从数据库中没有拿到数据！");
        }
    }

    private void litePal() {
        Student student2 = new Student();
        student2.setName("lyl1111");
        student2.setSex("男");

        student2.save();
    }
}
