package com.jun.androidexample.activity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import com.jun.androidexample.BaseActivity;
import com.jun.androidexample.R;
import com.jun.androidexample.service.BindService;
import com.jun.androidexample.service.DownloadService;
import com.jun.androidexample.service.ForegroundService;
import com.jun.androidexample.service.StartService;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Service的Activity
 */
public class ServiceActivity extends BaseActivity {

    @BindView(R.id.start_service_button)
    Button startServiceButton;
    @BindView(R.id.stop_service_button)
    Button stopServiceButton;
    @BindView(R.id.bind_service_button)
    Button bindServiceButton;
    @BindView(R.id.unbind_service_button)
    Button unbindServiceButton;
    @BindView(R.id.download_service_button)
    Button downloadServiceButton;
    @BindView(R.id.start_foreground_service_button)
    Button foregroundServiceButton;
    @BindView(R.id.stop_foreground_service_button)
    Button stopForegroundServiceButton;

    private BindService.MyBinder myBinder;

    @Override
    public int getLayoutId() {
        return R.layout.activity_service;
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            Logger.i("onServiceConnected()");

            // 获取到Service传递过来的IBinder对象
            myBinder = (BindService.MyBinder) service;
            // 调用Service的方法
            myBinder.display();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            // 当绑定的服务被意外销毁时调用，正常解除绑定不会调用。
            Logger.i("onServiceDisconnected()");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        initButton();
    }

    private void initButton() {

        startServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ServiceActivity.this, StartService.class);
                // 启动服务
                startService(intent);
            }
        });

        stopServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ServiceActivity.this, StartService.class);
                // 停止服务
                stopService(intent);
            }
        });

        bindServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ServiceActivity.this, BindService.class);
                // 绑定服务，绑定时自动创建服务。
                bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE);
            }
        });

        unbindServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 释放资源
                myBinder = null;

                // 解除绑定
                unbindService(serviceConnection);
            }
        });

        downloadServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ServiceActivity.this, DownloadService.class);
                startService(intent);
            }
        });

        foregroundServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ServiceActivity.this, ForegroundService.class);
                startService(intent);
            }
        });

        stopForegroundServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ServiceActivity.this, ForegroundService.class);
                stopService(intent);
            }
        });
    }

    // 启动当前Activity
    public static void launch(Context context) {
        Intent intent = new Intent(context, ServiceActivity.class);
        context.startActivity(intent);
    }
}
