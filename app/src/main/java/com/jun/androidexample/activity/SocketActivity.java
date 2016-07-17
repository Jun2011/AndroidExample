package com.jun.androidexample.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.jun.androidexample.BaseActivity;
import com.jun.androidexample.R;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Socket通信
 */
public class SocketActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_socket;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findViewById(R.id.send_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // 创建Socket实例
                            // 访问的服务器IP地址：192.168.56.1，端口号：12345。
                            Socket socket = new Socket("192.168.56.1", 12345);

                            // 获取客户端的IP地址
                            InetAddress inetAddress = InetAddress.getLocalHost();
                            String ip = inetAddress.getHostAddress();

                            PrintWriter writer = new PrintWriter(
                                    new OutputStreamWriter(
                                            socket.getOutputStream(), "utf-8"));
                            writer.write("客户端的IP地址：" + ip);
                            writer.flush();
                            writer.close();
                            // 关闭输出流
                            socket.shutdownOutput();
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }

    // 启动当前Activity
    public static void launch(Context context) {
        Intent intent = new Intent(context, SocketActivity.class);
        context.startActivity(intent);
    }
}
