package com.demo.interview.network.socket;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.demo.interview.R;
import com.demo.interview.base.BaseActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 文 件 名：TCPClientActivity
 * 创 建 人：李跃龙
 * 创建日期：2020/5/28 14:22
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class TCPClientActivity extends BaseActivity {
    private EditText editText;
    private TextView textView;

    private Socket mClientSocket = null;
    private PrintWriter mPrint;
    private StringBuilder stringBuilder;
    private static final int MESSAGE_READY = 0X01;
    private static final int MESSAGE_RECEIVED = 0X02;

    private byte[] bytes = {0x01, 0x02};

    @SuppressLint("HandlerLeak")
    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MESSAGE_READY:
                    LogUtils.d("MESSAGE_READY");
                case MESSAGE_RECEIVED:
                    stringBuilder.append(msg.obj).append("\n");
                    textView.setText(stringBuilder.toString());
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_socket_client;
    }

    @Override
    public void initView() {
        findViewById(R.id.btn_connect).setOnClickListener(v ->
                new Thread(() -> connectTcpServer()).start());
        editText = findViewById(R.id.ed_text);
        findViewById(R.id.btn_send).setOnClickListener(v -> sendMsg());
        textView = findViewById(R.id.tv_result);
        findViewById(R.id.btn_close).setOnClickListener(v -> {
            if (null != mClientSocket) {
                try {

                    mClientSocket.close();
                    mClientSocket.shutdownInput();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @SuppressLint("CheckResult")
    private void sendMsg() {
        if (null != mPrint) {
            String msg = editText.getText().toString();
            Observable.create((ObservableOnSubscribe<String>)
                    emitter -> {
//                        try {
//                            mClientSocket.getOutputStream().write(msg.getBytes());
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
                        emitter.onNext(msg);
                        mPrint.println(msg);
                    }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(s -> {
                        editText.setText("");
                        String time = formatDateTime(System.currentTimeMillis());
                        String showedMsg = "self " + time + ":" + msg + "\n";
                        stringBuilder.append(showedMsg);
                    });
        }
    }

    @Override
    public void initData() {
        stringBuilder = new StringBuilder();
        Intent intent = new Intent(TCPClientActivity.this,
                TcpServerService.class);
        startService(intent);
    }

    private void connectTcpServer() {
        Socket socket = null;
        while (null == socket) {
            try {
                socket = new Socket("127.0.0.1", 8888);
//                InetSocketAddress inetSocketAddress = new InetSocketAddress();
//                socket.connect(inetSocketAddress);
                mClientSocket = socket;
                mPrint = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream())), true);
                handler.sendEmptyMessage(MESSAGE_READY);
            } catch (IOException e) {
                LogUtils.e("error:" + e.getMessage());
            }
        }

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader
                    (socket.getInputStream()));
            while (!isFinishing()) {
                String msg = bufferedReader.readLine();
                if (null != msg) {
                    String showedMsg = "server " + formatDateTime(System.currentTimeMillis()) + ":" + msg + "\n";
                    handler.obtainMessage(MESSAGE_RECEIVED, showedMsg)
                            .sendToTarget();
                }
            }
        } catch (IOException e) {
            LogUtils.e("error:" + e.getMessage());
        }
    }

    private String formatDateTime(long time) {
        return new SimpleDateFormat("(HH:mm:ss)").format(new Date(time));
    }

    @Override
    protected void onDestroy() {
        if (null != mClientSocket) {
            try {
                mClientSocket.shutdownInput();
                mClientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onDestroy();
    }
}
