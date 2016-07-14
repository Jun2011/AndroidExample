package com.jun.androidexample.recyclerview;

import android.content.Context;
import android.content.Intent;

import com.jun.androidexample.BaseActivity;
import com.jun.androidexample.R;

/**
 * RecyclerView
 * 1、Item分为头部、普通、足部
 * 2、Item的点击事件
 * 3、分割线
 * 4、动画
 * 5、排列方式
 */
public class RecyclerViewActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_recycler_view;
    }

    // 启动当前Activity
    public static void launch(Context context) {
        Intent intent = new Intent(context, RecyclerViewActivity.class);
        context.startActivity(intent);
    }
}
