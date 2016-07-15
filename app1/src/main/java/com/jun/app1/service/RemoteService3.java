package com.jun.app1.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.jun.app1.ICustomData;
import com.jun.app1.model.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jun on 2016/7/15.
 */
public class RemoteService3 extends Service {

    private ArrayList<Person> persons;

    ICustomData.Stub mBinder = new ICustomData.Stub() {

        @Override
        public List<Person> add(Person person) throws RemoteException {

            persons.add(person);

            return persons;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        persons = new ArrayList<>();

        return mBinder;
    }
}
