package com.demo.interview.audio_video.camera.type;

/**
 * 文 件 名：CameraFlash
 * 创 建 人：魏海锋
 * 创建日期：2020/1/18 09:07
 * 邮    箱：hfwei@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public enum CameraFlash {
    OFF("off"),
    ON("on"),
    AUTO("auto"),
    ALWAYS("ALWAYS"),
    RED_EYE("red-eye"),
    EXTERNAL("EXTERNAL"),
    TORCH("torch");

    public final String value;

    CameraFlash(String value) {
        this.value = value;
    }
}
