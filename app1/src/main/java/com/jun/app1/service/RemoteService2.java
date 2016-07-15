package com.jun.app1.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.jun.app1.IBaseData;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * 远程服务2
 */
public class RemoteService2 extends Service {

    IBaseData.Stub dataBinder = new IBaseData.Stub() {

        @Override
        public void basicTypes(byte aByte, int anInt, long aLong,
                               boolean aBoolean,
                               float aFloat, double aDouble,
                               char aChar, String aString, CharSequence aCharSequence,
                               List<String> aList) throws RemoteException {
        }

        @Override
        public void showList(List<String> aList) throws RemoteException {

            for (String string :
                    aList) {
                Logger.i(string + "\n");
            }
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return dataBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        dataBinder = null;

        Logger.i("解除绑定2");
    }
}
