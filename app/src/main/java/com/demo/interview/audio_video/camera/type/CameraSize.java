package com.demo.interview.audio_video.camera.type;

import androidx.annotation.NonNull;

/**
 * 文 件 名：CameraSize
 * 创 建 人：魏海锋
 * 创建日期：2020/1/18 08:59
 * 邮    箱：hfwei@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class CameraSize {
    public int width;
    public int height;

    public CameraSize(int width, int heigth) {
        this.width = width;
        this.height = heigth;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int area() {
        return width * height;
    }

    public float aspectRatio() {
        if (0 == width || 0 == height) return 1f;
        return width / height;
    }

    @NonNull
    @Override
    public String toString() {
        return width + "-" + height;
    }
}
