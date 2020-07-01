package com.demo.interview.bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.LogUtils;
import com.demo.interview.App;

import java.lang.ref.WeakReference;
import java.util.List;

import static android.bluetooth.BluetoothDevice.TRANSPORT_LE;

/**
 * 文 件 名：BlueCore
 * 创 建 人：李跃龙
 * 创建日期：2020/7/1 15:28
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：蓝牙试用类
 * 蓝牙试用流程
 * <p>
 * 开启蓝牙功能--》搜索附近蓝牙设备--》连接蓝牙--》发现服务--》发现Characteristic--》开启通知
 */
public class BleCore {

    private Context mContext;

    private BluetoothManager bluetoothManager;
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothDevice bluetoothDevice;

    private BluetoothGatt bluetoothGatt;
    private BluetoothGattService bluetoothGattService;
    private BluetoothGattCharacteristic bluetoothGattCharacteristic;

    private BleHandler bleHandler;

    //扫描超时时间
    private static final int SCAN_TIME_OUT = 60 * 1000;

    private static final int MESSAGE_TIME = 0X01;

    public BleCore(Context context) {
        this.mContext = context;
        bluetoothManager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        bluetoothAdapter = bluetoothManager.getAdapter();
        bleHandler = new BleHandler(context);
    }

    /**
     * 判断蓝牙是否开启
     *
     * @return true 开启
     * false 未开启
     */
    public boolean isEnable() {
        return bluetoothAdapter.isEnabled();
    }

    /**
     * 打开蓝牙
     */
    public void enableBle() {
        bluetoothAdapter.enable();
    }

    /**
     * 开始扫描蓝牙
     */
    public void startScan() {
        //发送超时消息
        bleHandler.sendMessageDelayed(Message.obtain(bleHandler, MESSAGE_TIME), SCAN_TIME_OUT);
        bluetoothAdapter.startLeScan(leScanCallback);
    }

    /**
     * 停止扫描蓝牙
     */
    public void stopScan() {
        bleHandler.removeMessages(MESSAGE_TIME);
        bluetoothAdapter.stopLeScan(leScanCallback);
    }

    /**
     * 扫描结果回调
     */
    private BluetoothAdapter.LeScanCallback leScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
            LogUtils.d("onLeScan address:" + device.getAddress() + " name:" + device.getName());
            if (null != scanCallback) scanCallback.onLeScanResult(device, rssi, scanRecord);
        }
    };

    /**
     * 蓝牙连接
     */
    public void connectBle(String address) {
        bluetoothDevice = bluetoothAdapter.getRemoteDevice(address);
        if (null == bluetoothDevice) return;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            bluetoothGatt = bluetoothDevice.connectGatt(mContext, false, bluetoothGattCallback, TRANSPORT_LE);
        } else {
            bluetoothGatt = bluetoothDevice.connectGatt(mContext, false, bluetoothGattCallback);
        }
    }

    /**
     * 断开蓝牙连接
     */
    public void disConnectBle() {
        if (null != bluetoothGatt) bluetoothGatt.disconnect();
    }

    /**
     * GATT消息回调
     */
    private BluetoothGattCallback bluetoothGattCallback = new BluetoothGattCallback() {
        // 连接状态改变的回调
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            super.onConnectionStateChange(gatt, status, newState);
            // 代表连接成功,此处我们可以发送一个广播回去告诉activity已成功连接
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                //连接成功后启动服务发现
                LogUtils.d("连接成功");
                //启动发现服务
                boolean ret = bluetoothGatt.discoverServices();
            }
            LogUtils.d("onConnectionStateChange");
        }


        //成功发现服务后可以调用相应方法得到该BLE设备的所有服务，
        // 并且打印每一个服务的UUID和每个服务下各个特征的UUID
        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            super.onServicesDiscovered(gatt, status);
            LogUtils.d("onServicesDiscovered");
            if (status == BluetoothGatt.GATT_SUCCESS) {
                //获取所有发现的服务
                List<BluetoothGattService> services = bluetoothGatt.getServices();
                for (int i = 0; i < services.size(); i++) {
                    LogUtils.d("service =" + services.get(i).getUuid().toString());
                    if (BleConstant.SERVICE_UUID.equals(services.get(i).getUuid().toString())) {
                        List<BluetoothGattCharacteristic> characteristics =
                                services.get(i).getCharacteristics();
                        bluetoothGattService = services.get(i);
                        for (int j = 0; j < characteristics.size(); j++) {
                            if (BleConstant.CHARACTERISTIC_UUID.equals(characteristics.get(i).getUuid().toString())) {
                                bluetoothGattCharacteristic = characteristics.get(i);
                                break;
                            }
                            LogUtils.d("characteristic =" + characteristics.get(i).getUuid().toString());
                        }
                    }
                }
                //开启该特征通知功能
                if (null != bluetoothGattCharacteristic)
                    bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, true);
            }
        }

        /**
         * 读操作的回调
         * @param gatt
         * @param characteristic
         * @param status
         */
        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicRead(gatt, characteristic, status);
            LogUtils.d("onCharacteristicRead");
        }

        /**
         * 写操作的回调
         * @param gatt
         * @param characteristic
         * @param status
         */
        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicWrite(gatt, characteristic, status);
            LogUtils.d("onCharacteristicWrite");
        }

        /**
         * 数据返回的回调
         * @param gatt
         * @param characteristic
         */
        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            super.onCharacteristicChanged(gatt, characteristic);
            LogUtils.d("onCharacteristicChanged：" + characteristic.getValue());
        }

        @Override
        public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            super.onDescriptorRead(gatt, descriptor, status);
            LogUtils.d("onDescriptorRead");
        }

        @Override
        public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            super.onDescriptorWrite(gatt, descriptor, status);
            LogUtils.d("onDescriptorWrite");
        }

        @Override
        public void onMtuChanged(BluetoothGatt gatt, int mtu, int status) {
            super.onMtuChanged(gatt, mtu, status);
            LogUtils.d("onMtuChanged");
        }
    };

    public BluetoothAdapter getBluetoothAdapter() {
        return bluetoothAdapter;
    }

    public BluetoothManager getBluetoothManager() {
        return bluetoothManager;
    }

    private ScanCallback scanCallback;

    public void setScanCallback(ScanCallback scanCallback) {
        this.scanCallback = scanCallback;
    }

    public interface ScanCallback {
        void onLeScanResult(BluetoothDevice device, int rssi, byte[] scanRecord);
    }


    /**
     * BlueTooth处理类
     */
    public class BleHandler<T> extends Handler {
        private WeakReference<T> reference;

        public BleHandler(T activity) {
            reference = new WeakReference<T>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Activity activity = (Activity) reference.get();
            if (null != activity) {

            }
            switch (msg.what) {
                case MESSAGE_TIME:
                    BleCore.this.stopScan();
                    break;
                default:
                    break;
            }
        }
    }
}
