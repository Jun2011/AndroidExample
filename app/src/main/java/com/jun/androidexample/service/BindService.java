package com.jun.androidexample.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.orhanobut.logger.Logger;

/**
 * 以Bind方式启动的Service
 */
public class BindService extends Service {

    public class MyBinder extends Binder {

        public void display() {

            Logger.i("display()");
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        Logger.i("onBind()");

        MyBinder myBinder = new MyBinder();

        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {

        Logger.i("onUnbind()");

        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Logger.i("onCreate()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Logger.i("onDestroy()");
    }
}
