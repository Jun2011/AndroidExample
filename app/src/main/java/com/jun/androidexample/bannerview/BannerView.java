package com.jun.androidexample.bannerview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
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
 * 图片轮播
 */
public class BannerView extends FrameLayout {

    private List<String> imageUrlList;
    private List<String> titleList;

    private List<ImageView> imageViewList;
    private TextView title;
    private List<View> dotList;

    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;

    private int oldPosition = 0;
    private int currentPosition = 0;

    private ScheduledExecutorService mScheduledExecutorService;

    private boolean autoPlay = true;

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            mViewPager.setCurrentItem(currentPosition);
        }
    };

    public BannerView(Context context) {
        this(context, null);
    }

    public BannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 初始化数据
        initData();

        // 初始化控件
        initView(context);

        if (autoPlay) {
            // 开始自动播放
            startPlay();
        }
    }

    // 停止自动播放后释放资源
    public void stopPlay() {

        if (mScheduledExecutorService != null) {

            mScheduledExecutorService.shutdown();

            mScheduledExecutorService = null;
        }
    }

    private void startPlay() {

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

    private void initView(Context context) {

        // 载入布局
        LayoutInflater.from(context).inflate(R.layout.banner_view, this, true);

        imageViewList = new ArrayList<>();
        for (int i = 0; i < imageUrlList.size(); i++) {
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(context).load(imageUrlList.get(i)).into(imageView);
            imageViewList.add(imageView);
        }

        title = (TextView) findViewById(R.id.text_view);
        title.setText(titleList.get(0));

        dotList = new ArrayList<>();
        dotList.add(findViewById(R.id.dot_1));
        dotList.add(findViewById(R.id.dot_2));
        dotList.add(findViewById(R.id.dot_3));
        dotList.add(findViewById(R.id.dot_4));
        dotList.add(findViewById(R.id.dot_5));

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
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

                switch (state) {
                    // 闲置中
                    case ViewPager.SCROLL_STATE_IDLE:

                        break;
                    // 拖动中
                    case ViewPager.SCROLL_STATE_DRAGGING:

                        break;
                    // 设置中
                    case ViewPager.SCROLL_STATE_SETTLING:

                        break;
                }
            }
        });
    }

    // 初始化数据
    private void initData() {

        imageUrlList = new ArrayList<>();
        imageUrlList.add("http://pic4.zhimg.com/243c90710bf7cd9dcf01995bb14bb87f.jpg");
        imageUrlList.add("http://pic4.zhimg.com/28f2b15c5c2631764f086c7bdf403a53.jpg");
        imageUrlList.add("http://pic3.zhimg.com/fca7e8db521848555e814f499131cebe.jpg");
        imageUrlList.add("http://pic2.zhimg.com/a715c41ca31b2fa16e88fab687175d0d.jpg");
        imageUrlList.add("http://pic4.zhimg.com/7218f1db1007090d9c4c144762e144a3.jpg");

        titleList = new ArrayList<>();
        titleList.add("历史第一大前锋，以他的方式，宣布了退役的消息");
        titleList.add("读读日报 24 小时热门 TOP 5 · 这个摄影师拍下的地标建筑和平日里不一样");
        titleList.add("原来大熊猫一直在用颜值拯救动物保护事业");
        titleList.add("不要晚上看这部高级喜剧，担心你看着看着就通宵了");
        titleList.add("职人介绍所第 24 期：舒克贝塔皮皮鲁要做中国的漫威");
    }
}
