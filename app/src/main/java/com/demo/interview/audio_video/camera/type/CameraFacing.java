package com.demo.interview.audio_video.camera.type;

import android.hardware.camera2.CameraMetadata;

/**
 * 文 件 名：CameraFacing
 * 创 建 人：魏海锋
 * 创建日期：2020/1/18 08:56
 * 邮    箱：hfwei@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public enum CameraFacing {
    FRONT(CameraMetadata.LENS_FACING_FRONT),
    BACK(CameraMetadata.LENS_FACING_BACK);

    public final int value;

    CameraFacing(int value) {
        this.value = value;
    }
}
