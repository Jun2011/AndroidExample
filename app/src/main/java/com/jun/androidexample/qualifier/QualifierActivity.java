package com.jun.androidexample.qualifier;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.jun.androidexample.R;

/**
 * 屏幕适配之限定符Activity
 */
public class QualifierActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // 不显示标题栏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qualifier);
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, QualifierActivity.class);
        context.startActivity(intent);
    }
}
