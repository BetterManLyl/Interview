package com.demo.interview.message;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.demo.interview.R;
import com.demo.interview.base.BaseActivity;

/**
 * 文 件 名：AsyncTaskActivity
 * 创 建 人：李跃龙
 * 创建日期：2020/5/21 22:53
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class AsyncTaskActivity extends BaseActivity {

    private TextView textView;
    private AsyncTask<String, Integer, String> asyncTask;

    @Override
    public int getLayoutId() {
        return R.layout.activity_async_task;
    }

    String result;

    @Override
    public void initView() {
        textView = findViewById(R.id.tv_result);
        findViewById(R.id.btn_request).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View v) {
/**
 * // a. Params：开始异步任务执行时传入的参数类型，对应excute（）中传递的参数
 *     // b. Progress：异步任务执行过程中，返回下载进度值的类型
 *     // c. Result：异步任务执行完成后，返回的结果类型，与doInBackground()的返回值类型保持一致
 */

/**
 * a. 必须在UI线程中调用
 *                  *    b. 同一个AsyncTask实例对象只能执行1次，若执行第2次将会抛出异常
 *                  *    c. 执行任务中，系统会自动调用AsyncTask的一系列方法：onPreExecute() 、doInBackground()、onProgressUpdate() 、onPostExecute()
 *                  *    d. 不能手动调用上述方法
 *
 */
//        AsyncTask<Params, Progress, Result>
                /**
                 * 创建AsyncTask子类的实例对象（即 任务实例）
                 * 注：AsyncTask子类的实例必须在UI线程中创建
                 */
                asyncTask=  new AsyncTask<String, Integer, String>() {
                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        LogUtils.d("onPreExecute");
                    }

                    @Override
                    protected String doInBackground(String... strings) {
                        int count = 99;
                        int length = 0;
                        while (length < count) {
                            length++;
                            try {
                                Thread.sleep(50);
                                //更新进度  回调onProgressUpdate()方法
                                publishProgress(length);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        return result;
                    }


                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        LogUtils.d("result:" + s);
                    }

                    @Override
                    protected void onProgressUpdate(Integer... values) {
                        super.onProgressUpdate(values);
                        LogUtils.d("onProgressUpdate:" + values[0]);
                        textView.setText("加载中" + values[0]);
                    }
                };
                asyncTask.execute("你好");
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null!=asyncTask)asyncTask.cancel(true);
    }
}
