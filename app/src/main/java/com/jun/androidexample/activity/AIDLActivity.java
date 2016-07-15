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
import com.jun.app1.IBaseData;
import com.jun.app1.ICustomData;
import com.jun.app1.IDog;
import com.jun.app1.model.Person;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.bind_service2_button)
    Button bindService2Button;
    @BindView(R.id.unbind_service2_button)
    Button unbindService2Button;
    @BindView(R.id.invoke_method_button2)
    Button invokeMethodButton2;
    @BindView(R.id.bind_service3_button)
    Button bindService3Button;
    @BindView(R.id.unbind_service3_button)
    Button unbindService3Button;
    @BindView(R.id.invoke_method_button3)
    Button invokeMethodButton3;

    private IDog iDog;
    private IBaseData iBaseData;
    private ICustomData iCustomData;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            Logger.i("成功绑定服务1");

            // 拿到远程服务的代理
            iDog = IDog.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            iDog = null;
        }
    };

    private ServiceConnection serviceConnection2 = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            Logger.i("成功绑定服务2");

            iBaseData = IBaseData.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            iBaseData = null;
        }
    };

    private ServiceConnection serviceConnection3 = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            Logger.i("成功绑定服务3");

            iCustomData = ICustomData.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            iCustomData = null;
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

    @OnClick({R.id.bind_service_button,
            R.id.unbind_service_button,
            R.id.get_data_button,
            R.id.bind_service2_button,
            R.id.unbind_service2_button,
            R.id.invoke_method_button2,
            R.id.bind_service3_button,
            R.id.unbind_service3_button,
            R.id.invoke_method_button3})
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
                    Toast.makeText(AIDLActivity.this, "请先绑定服务1", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bind_service2_button:

                // 设置Intent目标是com.jun.app1包的RemoteService2
                Intent intent2 = new Intent("com.jun.app1.RemoteService2");
                intent2.setPackage("com.jun.app1");

                bindService(intent2, serviceConnection2, BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service2_button:

                if (serviceConnection2 != null) {

                    unbindService(serviceConnection2);

                    iBaseData = null;
                }
                break;
            case R.id.invoke_method_button2:

                if (iBaseData != null) {

                    ArrayList<String> stringList = new ArrayList<>();
                    stringList.add("List1");
                    stringList.add("List2");
                    stringList.add("List3");
                    try {
                        iBaseData.showList(stringList);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(AIDLActivity.this, "请先绑定服务2", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bind_service3_button:

                // 设置Intent目标是com.jun.app1包的RemoteService3
                Intent intent3 = new Intent("com.jun.app1.RemoteService3");
                intent3.setPackage("com.jun.app1");

                bindService(intent3, serviceConnection3, BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service3_button:

                if (serviceConnection3 != null) {

                    unbindService(serviceConnection3);

                    iCustomData = null;
                }
                break;
            case R.id.invoke_method_button3:

                if (iCustomData != null) {

                    try {
                        List<Person> persons = iCustomData.add(new Person("Tom", 18));
                        Logger.i(persons.toString());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(AIDLActivity.this, "请先绑定服务3", Toast.LENGTH_SHORT).show();
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
