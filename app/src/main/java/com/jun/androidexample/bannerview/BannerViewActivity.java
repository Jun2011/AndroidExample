package com.jun.androidexample.bannerview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jun.androidexample.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jun on 2016/7/12.
 */
public class BannerViewActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private List<String> imageUrlList;
    private List<ImageView> imageViewList;
    private List<View> dotList;
    private List<String> titleList;
    private TextView title;
    private ViewPagerAdapter mViewPagerAdapter;
    private int oldPosition = 0;
    private int currentPosition = 0;
    private ScheduledExecutorService mScheduledExecutorService;

    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            mViewPager.setCurrentItem(currentPosition);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_view);

        mViewPager = (ViewPager) findViewById(R.id.view_pager);

        imageUrlList = new ArrayList<>();
        imageUrlList.add("http://pic4.zhimg.com/243c90710bf7cd9dcf01995bb14bb87f.jpg");
        imageUrlList.add("http://pic4.zhimg.com/28f2b15c5c2631764f086c7bdf403a53.jpg");
        imageUrlList.add("http://pic3.zhimg.com/fca7e8db521848555e814f499131cebe.jpg");
        imageUrlList.add("http://pic2.zhimg.com/a715c41ca31b2fa16e88fab687175d0d.jpg");
        imageUrlList.add("http://pic4.zhimg.com/7218f1db1007090d9c4c144762e144a3.jpg");

        imageViewList = new ArrayList<>();
        for (int i = 0; i < imageUrlList.size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(this).load(imageUrlList.get(i)).into(imageView);
            imageViewList.add(imageView);
        }

        dotList = new ArrayList<>();
        dotList.add(findViewById(R.id.dot_1));
        dotList.add(findViewById(R.id.dot_2));
        dotList.add(findViewById(R.id.dot_3));
        dotList.add(findViewById(R.id.dot_4));
        dotList.add(findViewById(R.id.dot_5));

        titleList = new ArrayList<>();
        titleList.add("历史第一大前锋，以他的方式，宣布了退役的消息");
        titleList.add("读读日报 24 小时热门 TOP 5 · 这个摄影师拍下的地标建筑和平日里不一样");
        titleList.add("原来大熊猫一直在用颜值拯救动物保护事业");
        titleList.add("不要晚上看这部高级喜剧，担心你看着看着就通宵了");
        titleList.add("职人介绍所第 24 期：舒克贝塔皮皮鲁要做中国的漫威");

        title = (TextView) findViewById(R.id.text_view);
        title.setText(titleList.get(0));

        mViewPagerAdapter = new ViewPagerAdapter(imageViewList);

        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                title.setText(titleList.get(position));

                currentPosition = position;
                dotList.get(currentPosition).setBackgroundResource(R.drawable.dot_focused);
                dotList.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
                oldPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        // 线程池
        mScheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 定时周期性执行任务
        mScheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {

                currentPosition = (currentPosition + 1) % imageViewList.size();

                // 直接发送空消息即可
                mHandler.sendEmptyMessage(0);
            }
        }, 5, 5, TimeUnit.SECONDS);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mScheduledExecutorService != null) {

            mScheduledExecutorService.shutdown();

            mScheduledExecutorService = null;
        }
    }

    // 启动当前Activity
    public static void launch(Context context) {
        Intent intent = new Intent(context, BannerViewActivity.class);
        context.startActivity(intent);
    }
}
