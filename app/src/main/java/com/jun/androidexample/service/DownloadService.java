package com.jun.androidexample.service;

import android.app.IntentService;
import android.content.Intent;

import com.orhanobut.logger.Logger;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * 执行下载任务的Service
 */
public class DownloadService extends IntentService {

    // 注意：必须有这个无参构造函数并调用父类的有参构造函数，否则注册不了。
    public DownloadService() {
        // 设置此Service开启的工作线程名
        super("DownloadService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Logger.i("onHandleIntent()");

        String imageUrl = "http://pic1.zhimg.com/4cee77879f74e00e004873f1d17b179c.jpg";
        try {
            // 由网络传入的输入流
            InputStream is = new URL(imageUrl).openStream();
            BufferedInputStream bis = new BufferedInputStream(is);

            // 存储到文件的输出流
            OutputStream os = new FileOutputStream(new File(this.getFilesDir(),"image.jpg"));
            BufferedOutputStream bos = new BufferedOutputStream(os);

            byte[] bytes = new byte[1024];
            int len = -1;
            while ((len = bis.read(bytes)) != -1) {

                bos.write(bytes, 0, len);
            }

            bos.close();
            os.close();
            bis.close();
            is.close();

            Logger.i("Downloaded!");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
