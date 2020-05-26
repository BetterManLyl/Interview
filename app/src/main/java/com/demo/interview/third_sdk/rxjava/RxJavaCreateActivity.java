package com.demo.interview.third_sdk.rxjava;

import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.demo.interview.R;
import com.demo.interview.base.BaseActivity;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 文 件 名：RxJavaCreateActivity
 * 创 建 人：李跃龙
 * 创建日期：2020/5/23 11:31
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：创建型操作符
 */
public class RxJavaCreateActivity extends BaseActivity implements View.OnClickListener {

    private TextView textView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_rxjava_create;
    }

    @Override
    public void initView() {
        findViewById(R.id.btn_create).setOnClickListener(this);
        findViewById(R.id.btn_just).setOnClickListener(this);
        findViewById(R.id.btn_from_array).setOnClickListener(this);
        findViewById(R.id.btn_fromIterable).setOnClickListener(this);
        findViewById(R.id.btn_defer).setOnClickListener(this);
        findViewById(R.id.btn_timer).setOnClickListener(this);
        findViewById(R.id.btn_interval).setOnClickListener(this);
        findViewById(R.id.btn_intervalRange).setOnClickListener(this);

        findViewById(R.id.btn_range).setOnClickListener(this);
        findViewById(R.id.btn_rangeLong).setOnClickListener(this);
        findViewById(R.id.btn_rx_poll_net).setOnClickListener(this);
        textView = findViewById(R.id.tv_result);

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_create:
//                create();
                testCreate();
                break;
            case R.id.btn_just:
                just();
                break;
            case R.id.btn_from_array:
                fromArray();
                break;
            case R.id.btn_fromIterable:
                fromIterable();
                break;
            case R.id.btn_defer:
                defer();
                break;
            case R.id.btn_timer:
                timer();
                break;
            case R.id.btn_interval:
                interval();
                break;
            case R.id.btn_intervalRange:
                intervalRange();
                break;
            case R.id.btn_range:
                range();
                break;
            case R.id.btn_rangeLong:
                rangeLong();
                break;
            case R.id.btn_rx_poll_net:
                netPoll();
                break;
            default:
                break;
        }
    }


    private Disposable disposable;

    /**
     * rxjava 创建操作符
     */
    private void create() {
        Observable.create((ObservableOnSubscribe<String>) emitter -> {
            emitter.onNext("你好，你叫什么名字");
            emitter.onNext("我叫李跃龙");
            emitter.onComplete();
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(String s) {
                LogUtils.d("onNext:" + s);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d("onError:" + e.getMessage());
            }

            @Override
            public void onComplete() {
                LogUtils.d("onComplete");
            }
        });
//        注：整体方法调用顺序：观察者.onSubscribe（）> 被观察者.subscribe（）> 观察者.onNext（）>观察者.onComplete()
    }

    /**
     * 分布创建 用于演示
     */
    private void testCreate() {

        /**
         * 1、创建被观察者 Observable 对象
         */
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {

            // create() 是 RxJava 最基本的创造事件序列的方法
            // 此处传入了一个 OnSubscribe 对象参数
            // 当 Observable 被订阅时，OnSubscribe 的 call() 方法会自动被调用，即事件序列就会依照设定依次被触发
            // 即观察者会依次调用对应事件的复写方法从而响应事件
            // 从而实现被观察者调用了观察者的回调方法 & 由被观察者向观察者的事件传递，即观察者模式

            // 2. 在复写的subscribe（）里定义需要发送的事件
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                // 通过 ObservableEmitter类对象产生事件并通知观察者
                // ObservableEmitter类介绍
                // a. 定义：事件发射器
                // b. 作用：定义需要发送的事件 & 向观察者发送事件
                emitter.onNext(1);
                emitter.onNext(2);
                Thread.sleep(1000);
                emitter.onNext(3);
                emitter.onNext(4);
                emitter.onComplete();
            }
        });


        /**
         * 2、创建观察者 （Observer ）并 定义响应事件的行为
         */
        // 方式 1. 创建观察者 （Observer ）对象

        Observer<Integer> observer = new Observer<Integer>() {
            // 2. 创建对象时通过对应复写对应事件方法 从而 响应对应事件

            // 观察者接收事件前，默认最先调用复写 onSubscribe（）
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.d("onSubscribe");
            }

            // 当被观察者生产Next事件 & 观察者接收到时，会调用该复写方法 进行响应
            @Override
            public void onNext(Integer integer) {
                LogUtils.d("onNext:" + integer);

            }

            // 当被观察者生产Error事件& 观察者接收到时，会调用该复写方法 进行响应
            @Override
            public void onError(Throwable e) {
                LogUtils.d("onError:");

            }

            // 当被观察者生产Complete事件& 观察者接收到时，会调用该复写方法 进行响应
            @Override
            public void onComplete() {
                LogUtils.d("onComplete:");
            }
        };

        //方式2 rxjava2已经不能通过 observable.subscribe(subscriber)；进行订阅了
        // 说明：Subscriber类 = RxJava 内置的一个实现了 Observer 的抽象类，对 Observer 接口进行了扩展
        Subscriber<Integer> subscriber = new Subscriber<Integer>() {
            // 2. 创建对象时通过对应复写对应事件方法 从而 响应对应事件
            // 观察者接收事件前，默认最先调用复写 onSubscribe（）
            @Override
            public void onSubscribe(Subscription s) {

            }

            // 当被观察者生产Next事件 & 观察者接收到时，会调用该复写方法 进行响应
            @Override
            public void onNext(Integer integer) {

            }

            // 当被观察者生产Error事件& 观察者接收到时，会调用该复写方法 进行响应
            @Override
            public void onError(Throwable t) {

            }

            // 当被观察者生产Complete事件& 观察者接收到时，会调用该复写方法 进行响应
            @Override
            public void onComplete() {

            }
        };
//<--特别注意：2种方法的区别，即Subscriber 抽象类与Observer 接口的区别 -->
// 相同点：二者基本使用方式完全一致（实质上，在RxJava的 subscribe 过程中，Observer总是会先被转换成Subscriber再使用）
// 不同点：Subscriber抽象类对 Observer 接口进行了扩展，新增了两个方法：
        // 1. onStart()：在还未响应事件前调用，用于做一些初始化工作
        // 2. unsubscribe()：用于取消订阅。在该方法被调用后，观察者将不再接收 & 响应事件
        // 调用该方法前，先使用 isUnsubscribed() 判断状态，确定被观察者Observable是否还持有观察者Subscriber的引用，如果引用不能及时释放，就会出现内存泄露

        observable.subscribe(observer);
        //rxjava2 已经不能这样订阅subscriber了
//        observable.subscribe(subscriber)；
    }

    /**
     * 快速创建。
     * 最多只能创建10个以下事件
     */
    private void just() {
        Observable.just("1", "2", "3", "4", "5", "6", "7", "8", "9", "0")
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        LogUtils.d("onNext:" + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d("onComplete");
                    }
                });
    }

    /**
     * // 注：可发送10个以上参数
     * // 若直接传递一个list集合进去，否则会直接把list当做一个数据元素发送
     */
    private void fromArray() {
        //也可以传一个集合
        List<String> list = new ArrayList<String>() {
            {
                for (int i = 0; i < 10; i++) {
                    add("" + i);
                }
            }
        };

        Observable.fromArray(list).subscribe(new Observer<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<String> strings) {
                for (int i = 0; i < strings.size(); i++) {
                    LogUtils.d("onNext list:" + strings.get(i));
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        // 1. 设置需要传入的数组
        String[] strings = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        // 2. 创建被观察者对象（Observable）时传入数组
        // 在创建后就会将该数组转换成Observable & 发送该对象中的所有数据
        Observable.fromArray(strings)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        LogUtils.d("onNext:" + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d("onComplete");
                    }
                });
    }

    /**
     * 将集合中的每一个数据发送出去
     */
    private void fromIterable() {
        // 1. 设置一个集合
        List<String> list = new ArrayList<String>() {
            {
                for (int i = 0; i < 10; i++) {
                    add("" + i);
                }
            }
        };

        //2、通过fromIterable()将集合中的对象 / 数据发送出去
        Observable.fromIterable(list).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                LogUtils.d("onNext:" + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        // 该方法创建的被观察者对象发送事件的特点：仅发送Complete事件，直接通知完成
//        Observable.empty();
        // 该方法创建的被观察者对象发送事件的特点：仅发送Error事件，直接通知异常
//        Observable.error(new RuntimeException());
        // 该方法创建的被观察者对象发送事件的特点：不发送任何事件
//        Observable.never();

    }

    String txt = "你好";

    /**
     * 1、通过 Observable工厂方法创建被观察者对象（Observable）
     * 2、每次订阅后，都会得到一个刚创建的最新的Observable对象，
     * 这可以确保Observable对象里的数据是最新的
     * 3、defer 就相当于懒加载，只有等observable 与observer建立了订阅关系时，observable才会建立
     */
    private void defer() {
        Observable<String> observable = Observable.defer(() -> Observable.just(txt));
        txt = "我不好";
        observable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                LogUtils.d("onNext:" + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 延迟指定时间后，发送1个数值0（Long类型）
     * timer操作符默认运行在一个新线程上
     */
    private void timer() {
        Observable.timer(2000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//切换到主线程
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        LogUtils.d("onNext:" + aLong +
                                " thread-name:" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    /**
     * // 参数说明：
     * // 参数1 = 第1次延迟时间；
     * // 参数2 = 间隔时间数字；
     * // 参数3 = 时间单位；
     */
    private void interval() {
        Observable.interval(1000, 1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(Long aLong) {
                        LogUtils.d("onNext:" + aLong);
                        textView.setText("onNext:" + aLong);
                        if (5 == aLong) {
                            //切断观察者 与 被观察者 之间的连接
                            disposable.dispose();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("error:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    /**
     * 每隔指定时间 就发送 事件，可指定发送的数据的数量
     * // 参数说明：
     * // 参数1 = 事件序列起始点；
     * // 参数2 = 事件数量；
     * // 参数3 = 第1次事件延迟发送时间；
     * // 参数4 = 间隔时间数字；
     * // 参数5 = 时间单位
     */
    private void intervalRange() {
        Observable.intervalRange(2, 10, 1000, 1000,
                TimeUnit.MILLISECONDS)
                // 该例子发送的事件序列特点：
                // 1. 从2开始，一共发送10个事件；
                // 2. 第1次延迟2s发送，之后每隔1秒产生1个数字（从0开始递增1，无限个）
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        LogUtils.d("onNext:" + aLong);
                        textView.setText("onNext:" + aLong);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d("onComplete");
                    }
                });
    }

    /**
     * 连续发送 1个事件序列，可指定范围
     * / 参数说明：
     * // 参数1 = 事件序列起始点；
     * // 参数2 = 事件数量；
     * // 注：若设置为负数，则会抛出异常
     */
    private void range() {
        Observable.range(2, 10)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        LogUtils.d("onNext:" + integer);
                        textView.setText("onNext:" + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    /**
     * 与range用法相同，不错支持数据类型long
     */
    private void rangeLong() {
        range();
//        Observable.rangeLong()
    }


    /**
     * 网络轮询请求操作
     * retrofit+rxjava 轮询请求
     */
    private void netPoll() {
        /*
         * 步骤1：采用interval（）延迟发送
         * 注：此处主要展示无限次轮询，若要实现有限次轮询，仅需将interval（）改成intervalRange（）
         * 即可
         **/
        Observable.interval(2, 1, TimeUnit.SECONDS)
                // 参数说明：
                // 参数1 = 第1次延迟时间；
                // 参数2 = 间隔时间数字；
                // 参数3 = 时间单位；
                // 该例子发送的事件特点：延迟2s后发送事件，每隔1秒产生1个数字（从0开始递增1，无限个）

                /*
                 * 步骤2：每次发送数字前发送1次网络请求（doOnNext（）在执行Next事件前调用）
                 * 即每隔1秒产生1个数字前，就发送1次网络请求，从而实现轮询需求
                 **/
                .doOnNext(aLong -> {
                    LogUtils.d("doOnNext");
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://fy.iciba.com/")//设置网络请求
                            .addConverterFactory(GsonConverterFactory.create())//设置使用Gson解析(记得加入依赖)
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())// 支持RxJava
                            .build();

                    // b. 创建 网络请求接口 的实例
                    GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);
                    // c. 采用Observable<...>形式 对 网络请求 进行封装
                    Observable<Translation> observable = request.getCall();
                    // d. 通过线程切换发送网络请求
                    observable
                            .subscribeOn(Schedulers.io())// 切换到IO线程进行网络请求
                            .observeOn(AndroidSchedulers.mainThread()) // 切换回到主线程 处理请求结果
                            .subscribe(new Observer<Translation>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                }

                                @Override
                                public void onNext(Translation result) {
                                    // e.接收服务器返回的数据
                                    result.show();
                                }

                                @Override
                                public void onError(Throwable e) {
                                    LogUtils.e("请求失败");
                                }

                                @Override
                                public void onComplete() {

                                }
                            });

                }).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                //可结束轮询操作
                disposable = d;
            }

            @Override
            public void onNext(Long aLong) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    @Override
    protected void onDestroy() {
        LogUtils.d("onDestroy");
        super.onDestroy();
        //可采用 Disposable.dispose() 切断观察者 与 被观察者 之间的连接
        if (null != disposable) disposable.dispose();
    }
}
