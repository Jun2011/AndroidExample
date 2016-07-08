package com.jun.androidexample.serializable;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.orhanobut.logger.Logger;

/**
 * Created by Jun on 2016/7/7.
 */
public class Serializable_2 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Person person = (Person) intent.getSerializableExtra("person");

        Logger.i("name: " + person.getName()
                + "\n"
                + "age: " + person.getAge());
    }

    // 启动当前Activity
    public static void launch(Context context, Person person) {
        Intent intent = new Intent(context, Serializable_2.class);
        intent.putExtra("person", person);
        context.startActivity(intent);
    }
}
