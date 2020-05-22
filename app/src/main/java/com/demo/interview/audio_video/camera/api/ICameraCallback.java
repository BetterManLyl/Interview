package com.demo.interview.audio_video.camera.api;

/**
 * 文 件 名：ICameraCallback
 * 创 建 人：魏海锋
 * 创建日期：2020/1/18 09:08
 * 邮    箱：hfwei@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public interface ICameraCallback {
    void onCameraOpened(CameraAttributes attributes);

    void onCameraClosed();

    void onCameraError();

    void onPreviewStarted();

    void onPreviewFrame(byte[] data);

    void onPreviewStopped();

    void onPreviewError();

    void onPhoto(byte[] data);
}
