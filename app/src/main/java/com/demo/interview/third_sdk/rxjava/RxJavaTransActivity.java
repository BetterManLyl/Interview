package com.demo.interview.third_sdk.rxjava;

import android.annotation.SuppressLint;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.demo.interview.R;
import com.demo.interview.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * 文 件 名：RxJavaTransActivity
 * 创 建 人：李跃龙
 * 创建日期：2020/5/23 21:04
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class RxJavaTransActivity extends BaseActivity implements View.OnClickListener {
    @Override
    public int getLayoutId() {
        return R.layout.activity_rxjava_trans;
    }

    @Override
    public void initView() {

        findViewById(R.id.btn_map).setOnClickListener(this);
        findViewById(R.id.btn_flatMap).setOnClickListener(this);
        findViewById(R.id.btn_concatMap).setOnClickListener(this);
        findViewById(R.id.btn_buffer).setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_map:
                map();
                break;
            case R.id.btn_flatMap:
                flatMap();
                test();
                break;
            case R.id.btn_concatMap:
                concatMap();
                break;
            case R.id.btn_buffer:
                buffer();
                break;
            default:
                break;
        }
    }

    private void buffer() {
        Observable.fromArray("1").flatMap(new Function<String, ObservableSource<?>>() {
            @Override
            public ObservableSource<Integer> apply(String s) throws Exception {
                return Observable.fromIterable(new ArrayList<>());
            }
        }).subscribe();


        // 被观察者 需要发送5个数字
        Observable.just(1, 2, 3, 4, 5)
                .buffer(3, 1) // 设置缓存区大小 & 步长
                // 缓存区大小 = 每次从被观察者中获取的事件数量
                // 步长 = 每次获取新事件的数量
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Integer> stringList) {
                        LogUtils.d(" 缓存区里的事件数量 = " + stringList.size());
                        for (Integer value : stringList) {
                            LogUtils.d("事件：" + value);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    private void concatMap() {
        {
            ArrayList<Student.Source> sources = new ArrayList<Student.Source>() {
                {
                    add(new Student.Source("语文", "1"));
                    add(new Student.Source("数学", "2"));
                    add(new Student.Source("英语", "3"));
                }
            };

            Observable.create((ObservableOnSubscribe<Student>) emitter -> {
                emitter.onNext(new Student("lyl", "男", sources));
                emitter.onNext(new Student("cml", "女", sources));
            })
                    .concatMap((Function<Student, ObservableSource<Student.Source>>) student -> {
                        LogUtils.d("apply:" + student.getName());
                        return Observable.fromIterable(student.getList());
                    })
                    .subscribe(new Observer<Student.Source>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Student.Source source) {
                            if (source.getSourceName().equals("语文")) {
                                try {
                                    Thread.sleep(500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }

                            LogUtils.d("onNext:" + source.getSourceName());
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });

        }
    }

    /**
     * 将字符串 转变成 Integer类型
     */
    private void map() {
        Observable.create((ObservableOnSubscribe<String>) emitter -> {
            emitter.onNext("1");
            emitter.onNext("2");
            emitter.onNext("3");
            emitter.onNext("4");
        }).map(s -> Integer.parseInt(s))
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        LogUtils.d("onNext:" + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d("onComplete:");

                    }
                });
    }


    /**
     *
     */
    private void flatMap() {
        ArrayList<Student.Source> sources = new ArrayList<Student.Source>() {
            {
                add(new Student.Source("语文", "1"));
                add(new Student.Source("数学", "2"));
                add(new Student.Source("英语", "3"));
            }
        };

        Observable.create((ObservableOnSubscribe<Student>) emitter -> {
            emitter.onNext(new Student("lyl", "男", sources));
            emitter.onNext(new Student("cml", "女", sources));
        })
                .flatMap((Function<Student, ObservableSource<Student.Source>>) student -> {
                    LogUtils.d("apply:" + student.getName());
                    return Observable.fromIterable(student.getList());
                })
                .subscribe(new Observer<Student.Source>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Student.Source source) {
                        if (source.getSourceName().equals("语文")) {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        LogUtils.d("onNext:" + source.getSourceName());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @SuppressLint("CheckResult")
    private void test() {

        ArrayList<Student.Source> sources = new ArrayList<Student.Source>() {
            {
                add(new Student.Source("语文", "1"));
                add(new Student.Source("数学", "2"));
                add(new Student.Source("英语", "3"));
            }
        };

        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("lyl", "男", sources));
        students.add(new Student("cml", "男", sources));
        Observable.fromIterable(students)
                .flatMap(new Function<Student, ObservableSource<Student.Source>>() {
            @Override
            public ObservableSource<Student.Source> apply(Student student) throws Exception {
                return Observable.fromIterable(student.getList());
            }
        }).subscribe(new Consumer<Student.Source>() {
            @Override
            public void accept(Student.Source source) throws Exception {
                LogUtils.d("accept:" + source.getSourceName());
            }
        });
    }
}
