package com.demo.interview.audio_video.mic;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.util.Log;


import com.demo.interview.audio_video.camera.common.ByteArrayPool;

import androidx.annotation.NonNull;

/**
 * 文 件 名：AudioRecorder
 * 创 建 人：魏海锋
 * 创建日期：2019/1/1 19:10
 * 邮    箱：hfwei@com.iflytek.com
 * 功    能：录音实现类
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class AudioHelper {

    public interface IAudioCallback {
        /**
         * 录音出错
         */
        void onAudioError();

        /**
         * 实时音频数据回调
         *
         * @param data 音频数据
         * @param len  音频数据长度
         */
        void onAudioData(byte[] data, int len);
    }

    // 单例模式
    private static volatile AudioHelper sInstance;
    // 默认音频源：音频输入-麦克风
    private final static int AUDIO_INPUT = MediaRecorder.AudioSource.MIC;
    // 默认采样率
    // 44100是目前的标准，但是某些设备仍然支持22050，16000，11025
    // 采样频率一般共分为22.05KHz、44.1KHz、48KHz三个等级
    private final static int AUDIO_SAMPLE_RATE = 16000;
    // 默认音频通道 单声道
    private final static int AUDIO_CHANNEL = AudioFormat.CHANNEL_IN_MONO;
    // 默认音频格式：PCM编码
    private final static int AUDIO_ENCODING = AudioFormat.ENCODING_PCM_16BIT;
    // 默认音频录音缓存间隔（毫秒）
    private final static int AUDIO_INTERVAL = 40;
    // 缓冲区大小：缓冲区字节大小
    private int mBufferSizeInBytes = 0;
    // 录音对象
    private AudioRecord mAudioRecord;
    // 取录音线程
    private RecordThread mRecordThread;
    // 实际配置的音频录音缓存间隔（毫秒）
    private int mAudioInterval;
    // 监听
    private IAudioCallback mCallback;

    private AudioHelper() {

    }

    /**
     * 获取录音实例（单例模式）
     *
     * @return 录音实例
     */
    public static AudioHelper getInstance() {
        if (null == sInstance) {
            synchronized (AudioHelper.class) {
                if (null == sInstance) {
                    sInstance = new AudioHelper();
                }
            }
        }
        return sInstance;
    }

    /**
     * 创建默认录音对象
     */
    public AudioHelper createRecord() {
        Log.i("AudioRecorder", "createRecord");
        return createRecord(
                AUDIO_SAMPLE_RATE,
                AUDIO_CHANNEL,
                AUDIO_ENCODING,
                AUDIO_INTERVAL);
    }

    /**
     * 创建录音对象
     *
     * @param audioInterval 音频录音缓存间隔（毫秒）
     */
    public AudioHelper createRecord(int audioInterval) {
        Log.i("AudioRecorder", "createRecord, audioInterval:" + audioInterval);
        return createRecord(
                AUDIO_SAMPLE_RATE,
                AUDIO_CHANNEL,
                AUDIO_ENCODING,
                audioInterval);
    }

    /**
     * 创建录音对象
     *
     * @param audioSampleRate 音频采样率
     * @param audioInterval   音频录音缓存间隔（毫秒）
     */
    public AudioHelper createRecord(int audioSampleRate, int audioInterval) {
        Log.i("AudioRecorder", "createRecord, audioSampleRate:" + audioSampleRate
                + ", audioInterval:" + audioInterval);
        return createRecord(
                audioSampleRate,
                AUDIO_CHANNEL,
                AUDIO_ENCODING,
                audioInterval);
    }

    /**
     * 创建录音对象
     *
     * @param audioSampleRate 音频采样率
     * @param audioChannel    音频通道
     * @param audioEncoding   音频格式
     * @param audioInterval   音频录音缓存间隔（毫秒）
     */
    public AudioHelper createRecord(
            int audioSampleRate,
            int audioChannel,
            int audioEncoding,
            int audioInterval) {
        Log.i("AudioRecorder", "createRecord, audioSampleRate:" + audioSampleRate + ", audioChannel:"
                + audioChannel + ", audioEncoding:" + audioEncoding + "，audioInterval:" + audioInterval);
        this.mAudioInterval = audioInterval;
        int minBufferSize = AudioRecord.getMinBufferSize(audioSampleRate, audioChannel, audioEncoding);
        int calcBufferSize = mAudioInterval * audioSampleRate
                * ((AudioFormat.CHANNEL_IN_MONO == audioChannel) ? 1 : 2) * audioEncoding / 1000;
        mBufferSizeInBytes = Math.max(minBufferSize, calcBufferSize * 2);
        Log.i("AudioRecorder", "minBufferSize:" + minBufferSize + ", calcBufferSize:"
                + calcBufferSize + ", mBufferSizeInBytes:" + mBufferSizeInBytes);
        mAudioRecord = new AudioRecord(
                AUDIO_INPUT,
                audioSampleRate,
                audioChannel,
                audioEncoding,
                mBufferSizeInBytes);
        return sInstance;
    }

    /**
     * 设置录音监听
     *
     * @param callback 录音监听
     */
    public void setOnAudioRecorderListener(final IAudioCallback callback) {
        this.mCallback = callback;
    }

    /**
     * 开始录音
     *
     * @param callback 录音监听
     */
    public void startRecord(@NonNull final IAudioCallback callback) {
        Log.i("AudioRecorder", "startRecord, audio status:" + mAudioRecord.getState()
                + ", audio recording state: " + mAudioRecord.getRecordingState());
        this.mCallback = callback;
        if (AudioRecord.STATE_INITIALIZED != mAudioRecord.getState()) {
            Log.i("AudioRecorder", "audio record is not ready");
            mCallback.onAudioError();
            return;
        }
        if (AudioRecord.RECORDSTATE_RECORDING == mAudioRecord.getRecordingState()) {
            Log.i("AudioRecorder", "audio record is already start");
            mCallback.onAudioError();
            return;
        }
        mAudioRecord.startRecording();
        if (AudioRecord.RECORDSTATE_RECORDING != mAudioRecord.getRecordingState()) {
            Log.i("AudioRecorder", "audio record error");
            mCallback.onAudioError();
            return;
        }

        mRecordThread = new RecordThread("AudioRecorder", mCallback) {
            @Override
            public void run() {
                super.run();
            }
        };
        mRecordThread.start();
    }

    /**
     * 停止录音
     */
    public void stopRecord() {
        if (null == mAudioRecord) {
            Log.i("AudioRecorder", "stopRecord, but audio record instance is not create");
            return;
        }
        Log.i("AudioRecorder", "stopRecord, audio status:" + mAudioRecord.getState()
                + ", audio recording state: " + mAudioRecord.getRecordingState());
        if (AudioRecord.RECORDSTATE_RECORDING != mAudioRecord.getRecordingState()) {
            Log.i("AudioRecorder", "audio record is not start");
        } else {
            mAudioRecord.stop();
        }
    }

    /**
     * 释放录音
     */
    public void release() {
        if (null == mAudioRecord) {
            Log.i("AudioRecorder", "release, but audio record instance is not create");
            return;
        }
        Log.i("AudioRecorder", "release, audio status:" + mAudioRecord.getState()
                + ", audio recording state: " + mAudioRecord.getRecordingState());
        mAudioRecord.release();
        mAudioRecord = null;
    }

    // 取录音数据线程
    private class RecordThread extends Thread {

        // 录音回调
        private IAudioCallback mCallback;

        private RecordThread(String name, final IAudioCallback callback) {
            super(name);
            this.mCallback = callback;
        }

        @Override
        public void run() {
            while (AudioRecord.RECORDSTATE_RECORDING == mAudioRecord.getRecordingState()) {
                long beginTime = System.currentTimeMillis();
                byte[] audioData = ByteArrayPool.acquire(mBufferSizeInBytes / 2);
                int ret = mAudioRecord.read(
                        audioData,
                        0,
                        mBufferSizeInBytes / 2);
                if (AudioRecord.ERROR_INVALID_OPERATION != ret) {   // 取录音数据成功
                    if (null != mCallback) {
                        mCallback.onAudioData(audioData, mBufferSizeInBytes / 2);
                    }
                } else if (AudioRecord.RECORDSTATE_RECORDING == mAudioRecord.getRecordingState()) {
                    if (null != mCallback) mCallback.onAudioError();
                    break;
                }
                long consumeTime = System.currentTimeMillis() - beginTime;
                if ((int) consumeTime < mAudioInterval) {
                    try {
                        Thread.sleep(mAudioInterval - (int) consumeTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
