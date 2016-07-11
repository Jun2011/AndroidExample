package com.jun.androidexample.qualifier;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jun.androidexample.R;

/**
 * 适配不同分辨率的屏幕
 */
public class ResolutionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resolution);
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, ResolutionActivity.class);
        context.startActivity(intent);
    }
}
