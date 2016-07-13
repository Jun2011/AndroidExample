package com.jun.androidexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import butterknife.ButterKnife;

/**
 * 基础Activity
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 要求窗口无标题栏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        // 设置布局
        setContentView(getLayoutId());

        ButterKnife.bind(this);
    }

    // 获取布局Id
    public abstract int getLayoutId();
}
