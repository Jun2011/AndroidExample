package com.jun.androidexample.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.orhanobut.logger.Logger;

/**
 * 以Start方式启动的Service
 */
public class StartService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        // 此次以Start方式启动Service，所以返回null即可。
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Logger.i("onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Logger.i("onStartCommand()");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Logger.i("onDestroy()");
    }
}
