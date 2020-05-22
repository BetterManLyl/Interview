package com.demo.interview.audio_video.camera;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.media.Image;
import android.media.ImageReader;
import android.text.TextUtils;
import android.util.Size;
import android.view.Surface;

import com.blankj.utilcode.util.LogUtils;
import com.demo.interview.audio_video.camera.api.CameraAttributes;
import com.demo.interview.audio_video.camera.api.ICamera;
import com.demo.interview.audio_video.camera.api.ICameraCallback;
import com.demo.interview.audio_video.camera.common.ByteArrayPool;
import com.demo.interview.audio_video.camera.common.ThreadHandler;
import com.demo.interview.audio_video.camera.type.CameraFacing;
import com.demo.interview.audio_video.camera.type.CameraFlash;
import com.demo.interview.audio_video.camera.type.CameraSize;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;

import androidx.annotation.NonNull;

import static android.hardware.camera2.CameraCharacteristics.CONTROL_AE_AVAILABLE_MODES;
import static android.hardware.camera2.CameraCharacteristics.SENSOR_ORIENTATION;

/**
 * 文 件 名：Camera2
 * 创 建 人：魏海锋
 * 创建日期：2020/1/18 12:50
 * 邮    箱：hfwei@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class Camera2 implements ICamera {

    private ThreadHandler mCameraHandler;

    private CameraManager mCameraManager;

    private CameraDevice mCamera;

    @SuppressWarnings("unused")
    private Attributes mAttributes;

    private CameraCaptureSession mCaptureSession;

    private CaptureRequest.Builder mPreviewRequestBuilder;

    private ImageReader mPreviewImageReader;

    private ICameraCallback mCallback;

    private final ImageReader.OnImageAvailableListener mImageListener = reader -> {
        mCameraHandler.post(new ImageTransfer(reader.acquireNextImage()));
    };

    private CameraCaptureSession.CaptureCallback mCaptureCallback
            = new CameraCaptureSession.CaptureCallback() {

        private void process(CaptureResult result) {
            /*switch (mState) {
                case STATE_PREVIEW: {
                    // We have nothing to do when the camera preview is working normally.
                    break;
                }
                case STATE_WAITING_LOCK: {
                    Integer afState = result.get(CaptureResult.CONTROL_AF_STATE);
                    if (afState == null) {
                        captureStillPicture();
                    } else if (CaptureResult.CONTROL_AF_STATE_FOCUSED_LOCKED == afState ||
                            CaptureResult.CONTROL_AF_STATE_NOT_FOCUSED_LOCKED == afState) {
                        // CONTROL_AE_STATE can be null on some devices
                        Integer aeState = result.get(CaptureResult.CONTROL_AE_STATE);
                        if (aeState == null ||
                                aeState == CaptureResult.CONTROL_AE_STATE_CONVERGED) {
                            mState = STATE_PICTURE_TAKEN;
                            captureStillPicture();
                        } else {
                            runPrecaptureSequence();
                        }
                    }
                    break;
                }
                case STATE_WAITING_PRECAPTURE: {
                    // CONTROL_AE_STATE can be null on some devices
                    Integer aeState = result.get(CaptureResult.CONTROL_AE_STATE);
                    if (aeState == null ||
                            aeState == CaptureResult.CONTROL_AE_STATE_PRECAPTURE ||
                            aeState == CaptureRequest.CONTROL_AE_STATE_FLASH_REQUIRED) {
                        mState = STATE_WAITING_NON_PRECAPTURE;
                    }
                    break;
                }
                case STATE_WAITING_NON_PRECAPTURE: {
                    // CONTROL_AE_STATE can be null on some devices
                    Integer aeState = result.get(CaptureResult.CONTROL_AE_STATE);
                    if (aeState == null || aeState != CaptureResult.CONTROL_AE_STATE_PRECAPTURE) {
                        mState = STATE_PICTURE_TAKEN;
                        captureStillPicture();
                    }
                    break;
                }
            }*/
        }

        @Override
        public void onCaptureProgressed(@NonNull CameraCaptureSession session,
                                        @NonNull CaptureRequest request,
                                        @NonNull CaptureResult partialResult) {
            process(partialResult);
        }

        @Override
        public void onCaptureCompleted(@NonNull CameraCaptureSession session,
                                       @NonNull CaptureRequest request,
                                       @NonNull TotalCaptureResult result) {
            process(result);
        }

    };

    public Camera2(final Context context, ICameraCallback callback) {
        mCameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
        mCameraHandler = new ThreadHandler("CameraHandler");
        this.mCallback = callback;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void open(CameraFacing facing) {
        LogUtils.i("open, facing:" + facing);
        try {
            mCameraHandler.create();
            String cameraId = null;
            String[] cameraIdList = mCameraManager.getCameraIdList();
            for (String id : cameraIdList) {
                final Integer lensFacing = mCameraManager
                        .getCameraCharacteristics(id)
                        .get(CameraCharacteristics.LENS_FACING);
                if (null != lensFacing && facing.value == lensFacing) {
                    cameraId = id;
                    break;
                }
            }
            if (TextUtils.isEmpty(cameraId)) {
                LogUtils.e("can't find camera id");
                mCallback.onCameraError();
                return;
            }
            final CameraCharacteristics characteristics = mCameraManager.getCameraCharacteristics(cameraId);
            mCameraManager.openCamera(
                    cameraId,
                    new CameraDevice.StateCallback() {
                        @Override
                        public void onOpened(@NonNull CameraDevice camera) {
                            LogUtils.i("onOpened");
                            mCamera = camera;
                            mCallback.onCameraOpened(mAttributes = new Attributes(facing, characteristics));
                        }

                        @Override
                        public void onDisconnected(@NonNull CameraDevice camera) {
                            LogUtils.i("onDisconnected");
                            if (null != mCamera) {
                                mCamera.close();
                                mCamera = null;
                            }
                            mCallback.onCameraClosed();
                        }

                        @Override
                        public void onError(@NonNull CameraDevice camera, int error) {
                            LogUtils.i("onError, error:" + error);
                            if (null != mCamera) {
                                mCamera.close();
                                mCamera = null;
                            }
                            mCallback.onCameraError();
                        }
                    },
                    mCameraHandler);
        } catch (CameraAccessException | RuntimeException e) {
            LogUtils.i("open camera fail:" + e.getMessage());
            if (null != mCamera) {
                mCamera.close();
                mCamera = null;
            }
            mCallback.onCameraError();
        }
    }

    @Override
    public void release() {
        LogUtils.i("release, camera is null:" + (null == mCamera));
        if (null != mCamera) {
            mCamera.close();
            mCamera = null;
        }
        mAttributes = null;
        mCameraHandler.destroy();
    }

    @Override
    public void setPreviewOrientation(int degrees) {
        LogUtils.i("setPreviewOrientation, degrees:" + degrees
                + ", camera is null:" + (null == mCamera));

    }

    @Override
    public void setPreviewSize(CameraSize size) {
        LogUtils.i("setPreviewSize, size:" + size + ", camera is null:" + (null == mCamera));
        if (null == mCamera) return;
        mPreviewImageReader = ImageReader.newInstance(
                size.width, size.height,
                ImageFormat.YUV_420_888, 2);
        mPreviewImageReader.setOnImageAvailableListener(mImageListener, mCameraHandler);
    }

    @Override
    public void startPreview(SurfaceTexture texture) {
        LogUtils.i("startPreview, camera is null:" + (null == mCamera));
        if (null == mCamera) return;
        try {
            final Surface surface = new Surface(texture);
            mCamera.createCaptureSession(
                    (null == mPreviewImageReader)
                            ? Collections.singletonList(surface)
                            : Arrays.asList(surface, mPreviewImageReader.getSurface()),
                    new CameraCaptureSession.StateCallback() {
                        @Override
                        public void onConfigured(@NonNull CameraCaptureSession session) {
                            LogUtils.i("onConfigured, camera is null:" + (null == mCamera));
                            if (null == mCamera) return;
                            mCaptureSession = session;
                            try {
                                mPreviewRequestBuilder = mCamera
                                        .createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
                                mPreviewRequestBuilder.addTarget(surface);
                                if (null != mPreviewImageReader) {
                                    mPreviewRequestBuilder.addTarget(mPreviewImageReader.getSurface());
                                }
                                mPreviewRequestBuilder.set(
                                        CaptureRequest.CONTROL_AF_MODE,
                                        CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);
                                mPreviewRequestBuilder.set(
                                        CaptureRequest.CONTROL_MODE,
                                        CaptureRequest.CONTROL_MODE_AUTO);
                                mCaptureSession.setRepeatingRequest(
                                        mPreviewRequestBuilder.build(),
                                        mCaptureCallback,
                                        mCameraHandler);
                            } catch (CameraAccessException e) {
                                LogUtils.i("create capture request fail:" + e.getMessage());
                            }
                        }

                        @Override
                        public void onConfigureFailed(@NonNull CameraCaptureSession session) {
                            LogUtils.i("onConfigureFailed");
                        }
                    },
                    mCameraHandler);
        } catch (CameraAccessException e) {
            LogUtils.i("create capture session fail:" + e.getMessage());
        }
    }

    @Override
    public void stopPreview() {
        LogUtils.i("stopPreview, camera is null:" + (null == mCamera));
        if (null != mCaptureSession) {
            try {
                mCaptureSession.stopRepeating();
                mCaptureSession.abortCaptures();
                mCaptureSession.close();
            } catch (CameraAccessException e) {
                LogUtils.i("close capture session fail:" + e.getMessage());
            }
        }
        if (null != mPreviewImageReader) {
            mPreviewImageReader.close();
        }
    }

    @Override
    public void setFlash(CameraFlash flash) {
        LogUtils.i("setFlash, flash:" + flash + ", camera is null:" + (null == mCamera));

    }

    @Override
    public void setPhotoSize(CameraSize size) {
        LogUtils.i("setPhotoSize, size:" + size + ", camera is null:" + (null == mCamera));

    }

    @Override
    public void capturePhoto() {
        LogUtils.i("capturePhoto, camera is null:" + (null == mCamera));

    }

    @SuppressWarnings("ConstantConditions")
    class Attributes extends CameraAttributes {

        CameraCharacteristics characteristics;

        private Attributes(CameraFacing facing,
                           CameraCharacteristics characteristics) {
            this.facing = facing;
            this.characteristics = characteristics;
            this.sensorOrientation = characteristics.get(SENSOR_ORIENTATION);
            transferPreviewSizes(characteristics
                    .get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)
                    .getOutputSizes(SurfaceTexture.class));
            transferPhotoSizes(characteristics
                    .get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)
                    .getOutputSizes(ImageFormat.JPEG));
            transferFlashModes(characteristics.get(CONTROL_AE_AVAILABLE_MODES));
        }

        void transferPreviewSizes(Size[] sizes) {
            this.previewSizes.clear();
            for (Size size : sizes) {
                this.previewSizes.add(new CameraSize(size.getWidth(), size.getHeight()));
            }
        }

        void transferPhotoSizes(Size[] sizes) {
            this.photoSizes.clear();
            for (Size size : sizes) {
                this.photoSizes.add(new CameraSize(size.getWidth(), size.getHeight()));
            }
        }

        void transferFlashModes(int[] flashes) {
            this.flashes.clear();
            if (null == flashes) return;
            for (int flash : flashes) {
                switch (flash) {
                    case CaptureRequest.CONTROL_AE_MODE_OFF:
                        this.flashes.add(CameraFlash.OFF);
                        break;
                    case CaptureRequest.CONTROL_AE_MODE_ON:
                        this.flashes.add(CameraFlash.ON);
                        break;
                    case CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH:
                        this.flashes.add(CameraFlash.AUTO);
                        break;
                    case CaptureRequest.CONTROL_AE_MODE_ON_ALWAYS_FLASH:
                        this.flashes.add(CameraFlash.ALWAYS);
                        break;
                    case CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH_REDEYE:
                        this.flashes.add(CameraFlash.RED_EYE);
                        break;
                    case CaptureRequest.CONTROL_AE_MODE_ON_EXTERNAL_FLASH:
                        this.flashes.add(CameraFlash.EXTERNAL);
                        break;
                }
            }
        }
    }

    /**
     * YUV_420_888转NV21格式
     */
    @SuppressWarnings("FieldCanBeLocal")
    private class ImageTransfer implements Runnable {

        // The YUV_420_888 image
        private Image mImage;

        private int mWidth;

        private int mHeight;

        private int mArea;

        ImageTransfer(Image image) {
            mImage = image;
            mWidth = mImage.getWidth();
            mHeight = mImage.getHeight();
            mArea = mWidth * mHeight;
        }

        @Override
        public void run() {
            final byte[] data = ByteArrayPool.acquire(mArea * 3 / 2);

            // 获取y分量值
            ByteBuffer yBuffer = mImage.getPlanes()[0].getBuffer();
            byte[] yData = ByteArrayPool.acquire(yBuffer.remaining());
            yBuffer.get(yData);

            // 获取u分量值
            ByteBuffer uBuffer = mImage.getPlanes()[1].getBuffer();
            byte[] uData = ByteArrayPool.acquire(uBuffer.remaining());
            uBuffer.get(uData);

            // 获取u分量值
            ByteBuffer vBuffer = mImage.getPlanes()[2].getBuffer();
            byte[] vData = ByteArrayPool.acquire(vBuffer.remaining());
            vBuffer.get(vData);
            mImage.close();

            // 拷贝y分量
            System.arraycopy(yData, 0, data, 0, mArea);
            // 拷贝vu分量（不包括最后一个u分量的值）
            System.arraycopy(vData, 0, data, mArea, vData.length);
            // 拷贝vu分量最后一个u分量的值
            data[data.length - 1] = uData[uData.length - 1];
            ByteArrayPool.release(yData, yBuffer.remaining());
            ByteArrayPool.release(uData, uBuffer.remaining());
            ByteArrayPool.release(vData, vBuffer.remaining());


            if (null != mCallback) {
                mCallback.onPreviewFrame(data);
            }
        }
    }
}
