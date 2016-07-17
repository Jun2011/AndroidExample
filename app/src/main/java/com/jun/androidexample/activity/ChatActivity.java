package com.jun.androidexample.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jun.androidexample.BaseActivity;
import com.jun.androidexample.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 简易聊天室
 */
public class ChatActivity extends BaseActivity {

    @BindView(R.id.show_message)
    TextView showMessage;
    @BindView(R.id.edit_message)
    EditText editMessage;
    @BindView(R.id.send_message)
    Button sendMessage;

    private static final String HOST = "192.168.56.1";
    private static final int PORT = 12345;

    private StringBuilder sb = null;
    private String content = "";
    private Socket socket = null;
    private BufferedReader reader = null;
    private PrintWriter writer = null;

    // UI操作
    public Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0x123) {
                sb.append(content);
                showMessage.setText(sb.toString());
            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_chat;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        sb = new StringBuilder();

        // 连接服务器并实时监听
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 连接服务器
                    socket = new Socket(HOST, PORT);

                    reader = new BufferedReader(
                            new InputStreamReader(
                                    socket.getInputStream(), "UTF-8"));

                    // 读取服务器传递过来的数据
                    while (true) {
                        if (socket.isConnected()) {
                            if (!socket.isInputShutdown()) {
                                try {
                                    if ((content = reader.readLine()) != null) {
                                        content += "\n";
                                        handler.sendEmptyMessage(0x123);
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    // 点击按钮发送数据到服务器
    @OnClick(R.id.send_message)
    public void onClick() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    writer = new PrintWriter(
                            new BufferedWriter(
                                    new OutputStreamWriter(
                                            socket.getOutputStream(), "UTF-8")), true);

                    String message = editMessage.getText().toString();
                    if (socket.isConnected()) {
                        if (!socket.isOutputShutdown()) {
                            writer.println(message);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    // 启动当前Activity
    public static void launch(Context context) {
        Intent intent = new Intent(context, ChatActivity.class);
        context.startActivity(intent);
    }
}
