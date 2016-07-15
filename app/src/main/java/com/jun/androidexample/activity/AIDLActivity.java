package com.jun.androidexample.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jun.androidexample.BaseActivity;
import com.jun.androidexample.R;
import com.jun.app1.IDog;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 使用AIDL的Activity
 */
public class AIDLActivity extends BaseActivity {

    @BindView(R.id.bind_service_button)
    Button bindServiceButton;
    @BindView(R.id.unbind_service_button)
    Button unbindServiceButton;
    @BindView(R.id.get_data_button)
    Button getDataButton;

    private IDog iDog;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            Logger.i("成功绑定服务");

            // IDog.Stub.asInterface，获取接口。
            iDog = IDog.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            iDog = null;
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_aidl;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bind_service_button, R.id.unbind_service_button, R.id.get_data_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bind_service_button:

                // 设置Intent目标是com.jun.app1包的RemoteService
                Intent intent = new Intent("com.jun.app1.RemoteService");
                intent.setPackage("com.jun.app1");

                bindService(intent, serviceConnection, BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service_button:

                if (serviceConnection != null) {

                    unbindService(serviceConnection);
                    // 解除绑定时需要回收iDog连接资源
                    iDog = null;
                }
                break;
            case R.id.get_data_button:

                if (iDog != null) {
                    try {
                        String name = iDog.getName();
                        int age = iDog.getAge();
                        Logger.i("name: " + name + "\n" + "age: " + age);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(AIDLActivity.this, "请先绑定服务", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    // 启动当前Activity
    public static void launch(Context context) {
        Intent intent = new Intent(context, AIDLActivity.class);
        context.startActivity(intent);
    }
}
