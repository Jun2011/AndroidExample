package com.jun.androidexample.activity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jun.androidexample.BaseActivity;
import com.jun.androidexample.R;
import com.jun.androidexample.service.MessengerService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 使用Messenger进行交互
 */
public class MessengerActivity extends BaseActivity {

    @BindView(R.id.bind_service_button)
    Button bindServiceButton;
    @BindView(R.id.unbind_service_button)
    Button unbindServiceButton;
    @BindView(R.id.invoke_method_button)
    Button invokeMethodButton;

    private Messenger messenger = null;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            // 使用服务端的IBinder对象实例化一个Messenger对象
            messenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            // 释放资源
            messenger = null;
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_messenger;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bind_service_button, R.id.unbind_service_button, R.id.invoke_method_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bind_service_button:

                bindService(
                        new Intent(MessengerActivity.this, MessengerService.class),
                        serviceConnection,
                        Service.BIND_AUTO_CREATE);
                Toast.makeText(MessengerActivity.this, "绑定服务", Toast.LENGTH_SHORT).show();
                break;
            case R.id.unbind_service_button:

                unbindService(serviceConnection);
                Toast.makeText(MessengerActivity.this, "解除绑定", Toast.LENGTH_SHORT).show();
                // 释放资源
                messenger = null;
                break;
            case R.id.invoke_method_button:

                if (messenger == null) {
                    Toast.makeText(MessengerActivity.this, "请先绑定服务", Toast.LENGTH_SHORT).show();
                    // 直接结束
                    return;
                }

                // 实例化一个Message对象
                Message message = Message.obtain(null, MessengerService.MSG_SAY_HELLO, 0, 0);
                try {
                    // 把Message独享传递给服务端处理
                    messenger.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    // 启动当前Activity
    public static void launch(Context context) {
        Intent intent = new Intent(context, MessengerActivity.class);
        context.startActivity(intent);
    }
}
