package com.jun.androidexample.parcelable;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.orhanobut.logger.Logger;

/**
 * Created by Jun on 2016/7/7.
 */
public class Parcelable_2 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Student student = intent.getParcelableExtra("student");

        Logger.i("name: " + student.getName()
                + "\n"
                + "age: " + student.getAge());
    }

    // 启动当前Activity
    public static void launch(Context context,Student student) {
        Intent intent = new Intent(context, Parcelable_2.class);
        intent.putExtra("student", student);
        context.startActivity(intent);
    }
}
