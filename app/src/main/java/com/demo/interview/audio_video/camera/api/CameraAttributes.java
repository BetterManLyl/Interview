package com.demo.interview.audio_video.camera.api;

import com.demo.interview.audio_video.camera.type.CameraFacing;
import com.demo.interview.audio_video.camera.type.CameraFlash;
import com.demo.interview.audio_video.camera.type.CameraSize;


import java.util.ArrayList;
import java.util.List;

/**
 * 文 件 名：CameraAttributes
 * 创 建 人：魏海锋
 * 创建日期：2020/1/18 09:10
 * 邮    箱：hfwei@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
@SuppressWarnings("WeakerAccess")
public abstract class CameraAttributes {
    public CameraFacing facing;
    public int sensorOrientation;
    public List<CameraSize> previewSizes = new ArrayList<>();
    public List<CameraSize> photoSizes = new ArrayList<>();
    public List<CameraFlash> flashes = new ArrayList<>();
}
