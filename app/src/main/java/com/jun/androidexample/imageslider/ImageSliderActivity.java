package com.jun.androidexample.imageslider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.jun.androidexample.BaseActivity;
import com.jun.androidexample.R;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 图片轮播
 * 1、自动无限循环播放
 * 2、手势滑动时停止自动播放避免冲突
 * 3、直接使用Picasso网络加载图片
 */
public class ImageSliderActivity extends BaseActivity {

    @BindView(R.id.slider)
    SliderLayout sliderShow;
    @BindView(R.id.custom_indicator)
    PagerIndicator customIndicator;

    CustomSliderView
            customSliderView1,
            customSliderView2,
            customSliderView3;

    @Override
    public int getLayoutId() {
        return R.layout.activity_image_slider;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        // 初始化SliderView
        initSliderView();

        // 初始化SliderLayout
        initSliderLayout();
    }

    private void initSliderLayout() {

        // 向SliderLayout中添加SliderView
        sliderShow.addSlider(customSliderView1);
        sliderShow.addSlider(customSliderView2);
        sliderShow.addSlider(customSliderView3);

        // 设置循环播放的间隔时间
        sliderShow.setDuration(5000);

        // 设置指示器
        sliderShow.setCustomIndicator(customIndicator);

        // 添加页面变动的监听器
        sliderShow.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Logger.d("onPageScrolled()");
            }

            @Override
            public void onPageSelected(int position) {
                Logger.d("onPageSelected()");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Logger.d("onPageScrollStateChanged()");
            }
        });
    }

    private void initSliderView() {

        // 实例化
        customSliderView1 = new CustomSliderView(this);
        customSliderView2 = new CustomSliderView(this);
        customSliderView3 = new CustomSliderView(this);

        // 设置描述、图片、ImageView的scaleType、点击监听
        customSliderView1
                .description("房产证还没办下来？小心住着住着房子被查封了")
                .image("http://pic2.zhimg.com/ccba96a780750e78a3b5dca4f54d83dd.jpg")
                .setScaleType(BaseSliderView.ScaleType.CenterCrop)
                .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                    @Override
                    public void onSliderClick(BaseSliderView slider) {
                        Toast.makeText(ImageSliderActivity.this, "第1张", Toast.LENGTH_SHORT).show();
                    }
                });

        customSliderView2
                .description("普通人跟职业运动员到底有多大差距？")
                .image("http://pic4.zhimg.com/5b2cc43fe4a06e4b83f0cc5f326fc4cb.jpg")
                .setScaleType(BaseSliderView.ScaleType.CenterCrop)
                .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                    @Override
                    public void onSliderClick(BaseSliderView slider) {
                        Toast.makeText(ImageSliderActivity.this, "第2张", Toast.LENGTH_SHORT).show();
                    }
                });

        customSliderView3
                .description("读读日报 24 小时热门 TOP 5 · 互联网与赌博行业异曲同工")
                .image("http://pic2.zhimg.com/30740acc3bce6c0f2aa11fa9270eec25.jpg")
                .setScaleType(BaseSliderView.ScaleType.CenterCrop)
                .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                    @Override
                    public void onSliderClick(BaseSliderView slider) {
                        Toast.makeText(ImageSliderActivity.this, "第3张", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onStop() {

        // 停止自动循环播放防止内存泄漏
        sliderShow.stopAutoCycle();
        super.onStop();
    }

    // 启动当前Activity
    public static void launch(Context context) {
        Intent intent = new Intent(context, ImageSliderActivity.class);
        context.startActivity(intent);
    }
}
