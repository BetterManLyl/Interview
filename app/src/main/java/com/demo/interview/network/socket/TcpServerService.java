package com.demo.interview.network.socket;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.blankj.utilcode.util.LogUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

import androidx.annotation.Nullable;

/**
 * 文 件 名：TcpServerService
 * 创 建 人：李跃龙
 * 创建日期：2020/5/28 14:25
 * 邮    箱：ylli10@iflytek.com
 * 功    能：
 * 修 改 人：
 * 修改时间：
 * 修改备注：
 */
public class TcpServerService extends Service {
    private static final String TAG = "TCPServerService";
    private boolean isServiceDestroyed = false;
    private String[] mMessages = new String[]{
            "Hello! Body!",
            "用户不在线！请稍后再联系！",
            "请问你叫什么名字呀？",
            "厉害了，我的哥！",
            "Google 不需要科学上网是真的吗？",
            "扎心了，老铁！！！"
    };

    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(new TCPServer()).start();
        super.onCreate();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class TCPServer implements Runnable {
        @Override
        public void run() {
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(8888);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            while (!isServiceDestroyed) {
// receive request from client
                try {
                    final Socket client = serverSocket.accept();
                    Log.d(TAG, "=============== accept ========= == =======");
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                responseClient(client);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void responseClient(Socket client) throws IOException {
        //receive message
        BufferedReader in = new BufferedReader(
                new InputStreamReader(client.getInputStream()));
//        InputStream inputStream = client.getInputStream();
        //send message
        PrintWriter out = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(
                                client.getOutputStream())), true);
        out.println("欢迎来到聊天室！");
        while (!isServiceDestroyed) {
            String str = in.readLine();
//            byte[] bytes = new byte[1024];
//            inputStream.read(bytes);
//            LogUtils.d("inputStream" + bytes.toString());
            Log.d(TAG, "message from client: " + str);
            if (str == null) {
                return;
            }
            Random random = new Random();
            int index = random.nextInt(mMessages.length);
            String msg = mMessages[index];
            out.println(msg);
            Log.d(TAG, "send Message: " + msg);
        }
        out.close();
        in.close();
        client.close();
    }
}
