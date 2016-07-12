package com.jun.androidexample.bannerview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.jun.androidexample.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jun on 2016/7/12.
 */
public class BannerViewActivity extends AppCompatActivity {

    private List<String> imageUrlList;
    private ViewPagerAdapter mViewPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_view);

        initData();

        initAdapter();

        initView();
    }

    private void initView() {

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(mViewPagerAdapter);
    }

    private void initAdapter() {

        mViewPagerAdapter = new ViewPagerAdapter(BannerViewActivity.this, imageUrlList);
    }

    private void initData() {

        imageUrlList = new ArrayList<>();

        imageUrlList = Arrays.asList("http://pic4.zhimg.com/243c90710bf7cd9dcf01995bb14bb87f.jpg"
                , "http://pic4.zhimg.com/28f2b15c5c2631764f086c7bdf403a53.jpg"
                , "http://pic3.zhimg.com/fca7e8db521848555e814f499131cebe.jpg"
                , "http://pic2.zhimg.com/a715c41ca31b2fa16e88fab687175d0d.jpg"
                , "http://pic4.zhimg.com/7218f1db1007090d9c4c144762e144a3.jpg");
    }

    // 启动当前Activity
    public static void launch(Context context) {
        Intent intent = new Intent(context, BannerViewActivity.class);
        context.startActivity(intent);
    }
}
