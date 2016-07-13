package com.jun.androidexample.bannerview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.jun.androidexample.R;

/**
 * Created by Jun on 2016/7/12.
 */
public class BannerViewActivity extends AppCompatActivity {

    private BannerView mBannerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_banner_view);

        mBannerView = (BannerView) findViewById(R.id.banner_view);
    }

    @Override
    protected void onStop() {
        super.onStop();

        mBannerView.stopPlay();
    }

    // 启动当前Activity
    public static void launch(Context context) {
        Intent intent = new Intent(context, BannerViewActivity.class);
        context.startActivity(intent);
    }
}
