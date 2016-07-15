package com.jun.androidexample.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;

import com.orhanobut.logger.Logger;

/**
 * MessengerService
 */
public class MessengerService extends Service {

    public static final int MSG_SAY_HELLO = 1;

    private Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case MSG_SAY_HELLO:

                    Logger.i("Service say hello!");
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    };

    private final Messenger messenger = new Messenger(myHandler);

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return messenger.getBinder();
    }
}
