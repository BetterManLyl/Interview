package com.demo.interview.performance_optimize;

import android.content.Context;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.demo.interview.R;
import com.demo.interview.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 文 件 名：MemoryOptActivity
 * 创 建 人：李跃龙
 * 创建日期：2020/5/24 09:31
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：内存优化 参考博客 https://www.jianshu.com/p/97fb764f2669
 *
 * 性能优化 之 内存优化
 *
 * 内存泄漏的原因：
 * 1、集合类
 * 2、Static关键字修饰的成员变量
 * 3、非静态内部类 / 匿名类
 * 4、资源对象使用后未关闭
 */
public class MemoryOptActivity extends BaseActivity implements View.OnClickListener {
    @Override
    public int getLayoutId() {
        return R.layout.activity_memory_opt;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

    }


    /**
     * 一：集合类造成的内存泄漏
     * 内存泄露原因
     * 集合类 添加元素后，仍引用着 集合元素对象，导致该集合元素对象不可被回收，从而 导致内存泄漏
     * <p>
     * // 通过 循环申请Object 对象 & 将申请的对象逐个放入到集合List
     */
    private void collection_memory_leak() {
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Object o = new Object();
            list.add(o);
            o = null;
        }
        // 虽释放了集合元素引用的本身：o=null）
// 但集合List 仍然引用该对象，故垃圾回收器GC 依然不可回收该对象

/**
 *集合类 添加集合元素对象 后，在使用后必须从集合中删除
 * 由于1个集合中有许多元素，故最简单的方法 = 清空集合对象 & 设置为null
 */
        list.clear();
        list = null;
    }


    /**
     * 二：Static 关键字修饰的成员变量
     * <p>
     * 储备知识：
     * 被 Static 关键字修饰的成员变量的生命周期 = 应用程序的生命周期
     * <p>
     * 泄露原因：
     * 若使被 Static 关键字修饰的成员变量 引用耗费资源过多的实例（如Context），
     * 则容易出现该成员变量的生命周期 > 引用实例生命周期的情况，当引用实例需结束生命周期销毁时，
     * 会因静态变量的持有而无法被回收，从而出现内存泄露
     */
    //定义一个静态的成员变量
    private static Context context;

    private void static_mem_leak() {
        // 引用的是Activity的context
        context = this;
        // 当Activity需销毁时，由于mContext = 静态 & 生命周期 = 应用程序的生命周期，
        // 故 Activity无法被回收，从而出现内存泄露
        /**
         * 解决方案：
         * 1、尽量避免 Static 成员变量引用资源耗费过多的实例（如 Context）
         * 若需引用 Context，则尽量使用Application的Context
         * 2、使用 弱引用（WeakReference） 代替 强引用 持有实例
         *
         * 注：静态成员变量有个非常典型的例子 = 单例模式
         * 储备知识
         * 单例模式 由于其静态特性，其生命周期的长度 = 应用程序的生命周期
         * 泄露原因
         * 若1个对象已不需再使用 而单例对象还持有该对象的引用，那么该对象将不能被正常回收
         * 从而 导致内存泄漏
         */

        /**
         * 若this=activity 则即使activity退出，该activity的内存也不会被回收
         */
        SingleInstanceMemoryleak.getInstanceMemoryleak(this);

    }


    /**
     * 三、非静态匿名内部类导致的内存泄漏
     * <p>
     * 储备知识
     * 非静态内部类 / 匿名类 默认持有 外部类的引用；而静态内部类则不会
     * 常见情况
     * 3种，分别是：非静态内部类的实例 = 静态、多线程、消息传递机制（Handler）
     * <p>
     * 解决方案
     * 1、将非静态内部类设置为：静态内部类（静态内部类默认不持有外部类的引用）
     * 2、该内部类抽取出来封装成一个单例
     * 3、尽量 避免 非静态内部类所创建的实例 = 静态
     * 若需使用Context，建议使用 Application 的 Context
     */
    // 非静态内部类的实例的引用
    // 注：设置为静态
    public static InnerClass innerClass = null;

    private void no_static_anon_inner_class() {
        // 保证非静态内部类的实例只有1个
        if (null == innerClass)
            innerClass = new InnerClass();
    }

    /**
     * 非静态内部类的定义
     */
    private class InnerClass {
//
    }

    /**
     * 静态内部类的定义
     */
    private static class InnerClass2 {
//
    }

    /**
     * 多线程：AsyncTask、实现Runnable接口、继承Thread类引起的内存泄漏
     * <p>
     * 储备知识
     * 多线程的使用方法 = 非静态内部类 / 匿名类；即 线程类 属于 非静态内部类 / 匿名类
     * <p>
     * 泄露原因
     * 当 工作线程正在处理任务 & 外部类需销毁时， 由于 工作线程实例 持有外部类引用，
     * 将使得外部类无法被垃圾回收器（GC）回收，从而造成 内存泄露
     * <p>
     * 1、多线程主要使用的是：AsyncTask、实现Runnable接口 & 继承Thread类
     * 2、前3者内存泄露的原理相同，此处主要以继承Thread类 为例说明
     */
    private void thread_mem_leak() {
        //方式一： 通过创建的内部类 实现多线程
        new MyThread().start();
        //解决方案
        new MyThread1().start();


        // 方式二：通过匿名内部类 实现多线程
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    LogUtils.d("执行了多线程");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }


    private class MyThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(5000);
                LogUtils.d("执行了多线程");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 解决方式1：静态内部类
     * 原理：静态内部类 不默认持有外部类的引用，从而使得 “工作线程实例 持有 外部类引用” 的引用关系不复存在
     * 具体实现：将Thread的子类设置成 静态内部类
     */
    private static class MyThread1 extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(5000);
                LogUtils.d("执行了多线程");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     *
     * 四：资源对象使用后未关闭
     * 泄露原因
     * 对于资源的使用（如 广播BraodcastReceiver、文件流File、数据库游标Cursor、图片资源Bitmap等），
     * 若在Activity销毁时无及时关闭 / 注销这些资源，则这些资源将不会被回收，从而造成内存泄漏
     *
     * 解决方案
     * 在Activity销毁时 及时关闭 / 注销资源
     */
    private void resource_no_close_leak(){

        // 对于 广播BraodcastReceiver：注销注册
//        unregisterReceiver()
//
//// 对于 文件流File：关闭流
//        InputStream / OutputStream.close()
//
//// 对于数据库游标cursor：使用后关闭游标
//        cursor.close（）
//
//// 对于 图片资源Bitmap：Android分配给图片的内存只有8M，若1个Bitmap对象占内存较多，
// 当它不再被使用时，应调用recycle()回收此对象的像素所占用的内存；最后再赋为null
//        Bitmap.recycle()；
//        Bitmap = null;

// 对于动画（属性动画）
// 将动画设置成无限循环播放repeatCount = “infinite”后
// 在Activity退出时记得停止动画

    }

    /**
     * 解决方案2：当外部类结束生命周期时，强制结束线程
     * 原理：使得 工作线程实例的生命周期 与 外部类的生命周期 同步
     * 具体实现：当 外部类（此处以Activity为例） 结束生命周期时（此时系统会调用onDestroy（）），强制结束线程（调用stop（））
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Thread.interrupted();
        // 外部类Activity生命周期结束时，强制结束线程
    }
}
