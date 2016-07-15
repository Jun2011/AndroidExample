package com.jun.androidexample.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;

import com.jun.androidexample.R;
import com.jun.androidexample.activity.ForegroundActivity;
import com.orhanobut.logger.Logger;

/**
 * 前台服务
 */
public class ForegroundService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Logger.i("onCreate()");

        NotificationCompat.Builder builder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.image_test)
                        .setContentTitle("Foreground Service")
                        .setContentText("Foreground Service Started.");
        Intent intent = new Intent(this, ForegroundActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(pendingIntent);

        Notification notification = builder.build();

        // 把当前服务设定为前台服务，并指定显示的通知。
        // 这里的id参数不能为0
        startForeground(1, notification);
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

        // 在服务销毁的时候，使当前服务推出前台，并销毁显示的通知。
        stopForeground(false);
    }
}
