package com.jun.app1.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.jun.app1.IDog;

/**
 * 远程服务
 */
public class RemoteService extends Service {

    IDog.Stub dogBinder = new IDog.Stub() {

        @Override
        public String getName() throws RemoteException {

            return "旺财";
        }

        @Override
        public int getAge() throws RemoteException {

            return 3;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return dogBinder;
    }
}
