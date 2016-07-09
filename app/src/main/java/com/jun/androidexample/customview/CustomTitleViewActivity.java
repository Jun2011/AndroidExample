package com.jun.androidexample.customview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jun.androidexample.R;

public class CustomTitleViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customtitleview);
    }

    // 启动当前Activity
    public static void launch(Context context) {
        Intent intent = new Intent(context, CustomTitleViewActivity.class);
        context.startActivity(intent);
    }
}
