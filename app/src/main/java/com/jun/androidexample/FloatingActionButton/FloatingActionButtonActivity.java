package com.jun.androidexample.FloatingActionButton;

import android.content.Context;
import android.content.Intent;

import com.jun.androidexample.BaseActivity;
import com.jun.androidexample.R;

/**
 * FloatingActionButton浮动按钮
 */
public class FloatingActionButtonActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_floating_action_button;
    }

    // 启动当前Activity
    public static void launch(Context context) {
        Intent intent = new Intent(context, FloatingActionButtonActivity.class);
        context.startActivity(intent);
    }
}
