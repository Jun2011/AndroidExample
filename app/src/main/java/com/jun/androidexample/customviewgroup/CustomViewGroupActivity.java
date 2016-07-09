package com.jun.androidexample.customviewgroup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jun.androidexample.R;

/**
 * Created by Jun on 2016/7/9.
 */
public class CustomViewGroupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customviewgroup);
    }

    // 启动当前Activity
    public static void launch(Context context) {
        Intent intent = new Intent(context, CustomViewGroupActivity.class);
        context.startActivity(intent);
    }
}
