package com.demo.interview.audio_video.camera;

import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;


import com.blankj.utilcode.util.LogUtils;
import com.demo.interview.audio_video.camera.api.CameraAttributes;
import com.demo.interview.audio_video.camera.api.ICamera;
import com.demo.interview.audio_video.camera.api.ICameraCallback;
import com.demo.interview.audio_video.camera.type.CameraFacing;
import com.demo.interview.audio_video.camera.type.CameraFlash;
import com.demo.interview.audio_video.camera.type.CameraSize;

import java.io.IOException;
import java.util.List;

import androidx.annotation.NonNull;

/**
 * 文 件 名：Camera1
 * 创 建 人：魏海锋
 * 创建日期：2020/1/18 08:46
 * 邮    箱：hfwei@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
@SuppressWarnings("deprecation")
public class Camera1 implements ICamera {

    // 一代接口相机实例
    private Camera mCamera;

    // 相机数据
    private CameraAttributes mAttributes;

    // 回调
    private ICameraCallback mCallback;

    public Camera1(@NonNull ICameraCallback callback) {
        this.mCallback = callback;
    }

    @Override
    public void open(CameraFacing facing) {
        LogUtils.i("open, facing:" + facing);
        int cameraId = (CameraFacing.BACK == facing)
                ? Camera.CameraInfo.CAMERA_FACING_BACK
                : Camera.CameraInfo.CAMERA_FACING_FRONT;

        int numberOfCameras = Camera.getNumberOfCameras();
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == cameraId) {
                try {
                    this.mCamera = Camera.open(i);
                } catch (Exception e) {
                    LogUtils.i("open camera fail:" + e.getMessage());
                    mCallback.onCameraError();
                    return;
                }
                mCallback.onCameraOpened(mAttributes = new Attributes(
                        facing,
                        cameraInfo,
                        mCamera.getParameters()));
                return;
            }
        }
        mCallback.onCameraError();
    }

    @Override
    public void release() {
        LogUtils.i("release, camera is null:" + (null == mCamera));
        if (null == mCamera) return;
        mCamera.stopPreview();
        mCamera.setPreviewCallback(null);
        mCamera.release();
        mCamera = null;
        mAttributes = null;
        mCallback.onCameraClosed();
        mCallback = null;
    }

    @Override
    public void setPreviewOrientation(int degrees) {
        LogUtils.i("setPreviewOrientation, degrees:" + degrees
                + ", camera is null:" + (null == mCamera));
        if (null == mCamera) return;
        mCamera.setDisplayOrientation(degrees);
    }

    @Override
    public void setPreviewSize(CameraSize size) {
        LogUtils.i("setPreviewSize, size:" + size + ", camera is null:" + (null == mCamera));
        if (null == mCamera) return;
        final Camera.Parameters parameters = mCamera.getParameters();
        parameters.setPreviewFormat(ImageFormat.NV21);
        parameters.setPreviewSize(size.width, size.height);
        if (null != parameters.getSupportedFocusModes()
                && parameters.getSupportedFocusModes()
                .contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        }
        try {
            mCamera.setParameters(parameters);
        } catch (Exception e) {
            LogUtils.i("set parameters fail:" + e.getMessage());
            mCallback.onCameraError();
        }
    }

    @Override
    public void startPreview(SurfaceTexture texture) {
        LogUtils.i("startPreview, camera is null:" + (null == mCamera));
        if (null == mCamera) return;
        try {
            mCamera.setPreviewTexture(texture);
            Camera.Parameters parameters = mCamera.getParameters();
            int bufferSize = parameters.getPreviewSize().width * parameters.getPreviewSize().height
                    * ImageFormat.getBitsPerPixel(parameters.getPreviewFormat()) / 8;
            for (int i = 0; i < 2; i++) {
                mCamera.addCallbackBuffer(new byte[bufferSize]);
            }
            mCamera.setPreviewCallbackWithBuffer((data, camera) -> {
                mCallback.onPreviewFrame(data);
                camera.addCallbackBuffer(data);
            });
            mCamera.startPreview();
            mCallback.onPreviewStarted();
        } catch (IOException e) {
            LogUtils.i("start preview fail:" + e.getMessage());
            mCallback.onCameraError();
        }
    }

    @Override
    public void stopPreview() {
        LogUtils.i("stopPreview, camera is null:" + (null == mCamera));
        if (null == mCamera) return;
        mCamera.stopPreview();
        mCallback.onPreviewStopped();
    }

    @Override
    public void setFlash(CameraFlash flash) {
        LogUtils.i("setFlash, flash:" + flash + ", camera is null:" + (null == mCamera));
        if (null == mCamera) return;
        final Camera.Parameters parameters = mCamera.getParameters();
        parameters.setFlashMode(flash.value);
        try {
            mCamera.setParameters(parameters);
        } catch (Exception e) {
            LogUtils.i("set parameters fail:" + e.getMessage());
            mCallback.onCameraError();
        }
    }

    @Override
    public void setPhotoSize(CameraSize size) {
        LogUtils.i("setPhotoSize, size:" + size + ", camera is null:" + (null == mCamera));
        if (null == mCamera) return;
        final Camera.Parameters parameters = mCamera.getParameters();
        parameters.setPictureFormat(ImageFormat.JPEG);
        parameters.setPictureSize(size.width, size.height);
        try {
            mCamera.setParameters(parameters);
        } catch (Exception e) {
            LogUtils.i("set parameters fail:" + e.getMessage());
            mCallback.onCameraError();
        }
    }

    @Override
    public void capturePhoto() {
        LogUtils.i("capturePhoto, camera is null:" + (null == mCamera));
        if (null == mCamera) return;
        mCamera.takePicture(null, null, (data, camera) -> mCallback.onPhoto(data));
    }

    @SuppressWarnings("deprecation")
    class Attributes extends CameraAttributes {
        private Attributes(CameraFacing facing,
                           Camera.CameraInfo cameraInfo,
                           Camera.Parameters parameters) {
            this.facing = facing;
            this.sensorOrientation = cameraInfo.orientation;
            transferPreviewSizes(parameters.getSupportedPreviewSizes());
            transferPhotoSizes(parameters.getSupportedPictureSizes());
            transferFlashModes(parameters.getSupportedFlashModes());
        }

        void transferPreviewSizes(List<Camera.Size> sizes) {
            this.previewSizes.clear();
            for (Camera.Size size : sizes) {
                this.previewSizes.add(new CameraSize(size.width, size.height));
            }
        }

        void transferPhotoSizes(List<Camera.Size> sizes) {
            this.photoSizes.clear();
            for (Camera.Size size : sizes) {
                this.photoSizes.add(new CameraSize(size.width, size.height));
            }
        }

        void transferFlashModes(List<String> flashes) {
            this.flashes.clear();
            if (null == flashes) return;
            for (String flash : flashes) {
                LogUtils.i(flash);
                switch (flash) {
                    case Camera.Parameters.FLASH_MODE_OFF:
                        this.flashes.add(CameraFlash.OFF);
                        break;
                    case Camera.Parameters.FLASH_MODE_ON:
                        this.flashes.add(CameraFlash.ON);
                        break;
                    case Camera.Parameters.FLASH_MODE_AUTO:
                        this.flashes.add(CameraFlash.AUTO);
                        break;
                    case Camera.Parameters.FLASH_MODE_RED_EYE:
                        this.flashes.add(CameraFlash.RED_EYE);
                        break;
                    case Camera.Parameters.FLASH_MODE_TORCH:
                        this.flashes.add(CameraFlash.TORCH);
                        break;
                }
            }
        }
    }
}
