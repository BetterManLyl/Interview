package com.demo.interview.audio_video.camera.api;

import android.graphics.SurfaceTexture;

import com.demo.interview.audio_video.camera.type.CameraFacing;
import com.demo.interview.audio_video.camera.type.CameraFlash;
import com.demo.interview.audio_video.camera.type.CameraSize;

/**
 * 文 件 名：ICamera
 * 创 建 人：魏海锋
 * 创建日期：2020/1/18 08:51
 * 邮    箱：hfwei@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public interface ICamera {
    void open(CameraFacing facing);

    void release();

    void setPreviewOrientation(int degrees);

    void setPreviewSize(CameraSize size);

    void startPreview(SurfaceTexture texture);

    void stopPreview();

    void setFlash(CameraFlash flash);

    void setPhotoSize(CameraSize size);

    void capturePhoto();
}
