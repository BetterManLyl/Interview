package com.demo.interview.audio_video.camera.common;

import android.util.SparseArray;

import java.lang.ref.WeakReference;

import androidx.core.util.Pools;

/**
 * 文 件 名：ByteArrayPool
 * 创 建 人：魏海锋
 * 创建日期：2019/1/25 17:07
 * 邮    箱：hfwei@iflytek.com
 * 功    能：音频数据池，减少频繁gc引起内存抖动
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */

public class ByteArrayPool {

    // 音频数据池
    private static SparseArray<WeakReference<Pools.SimplePool<byte[]>>> mPools;

    // 锁
    private final static Object mLock = new Object();

    static {
        mPools = new SparseArray<>();
    }

    /**
     * 获取byte数组
     *
     * @param len 需要的byte数据长度
     * @return byte数组
     */
    public static byte[] acquire(int len) {
        synchronized (mLock) {
            Pools.SimplePool<byte[]> pool = null;
            if (-1 != mPools.indexOfKey(len) && null != mPools.get(len)) {
                pool = mPools.get(len).get();
            }
            if (null == pool) {
                pool = new Pools.SimplePool<>(Short.MAX_VALUE);
                mPools.put(len, new WeakReference<>(pool));
            }
            byte[] bytes = pool.acquire();
            if (null == bytes || len != bytes.length) {
                bytes = new byte[len];
            }
            return bytes;
        }
    }

    /**
     * 释放byte数据
     *
     * @param bytes byte数组
     * @param len   数据长度
     * @return true：成功；false：失败
     */
    public static boolean release(byte[] bytes, int len) {
        synchronized (mLock) {
            Pools.SimplePool<byte[]> pool = null;
            if (-1 != mPools.indexOfKey(len) && null != mPools.get(len)) {
                pool = mPools.get(len).get();
            }
            if (null != pool) {
                return pool.release(bytes);
            }
            return false;
        }
    }
}
