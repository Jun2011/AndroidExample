package com.jun.androidexample.imageslider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.jun.androidexample.BaseActivity;
import com.jun.androidexample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jun on 2016/7/13.
 */
public class ImageSliderActivity extends BaseActivity {

    @BindView(R.id.slider)
    SliderLayout sliderShow;
    @BindView(R.id.custom_indicator)
    PagerIndicator customIndicator;

    @Override
    public int getLayoutId() {
        return R.layout.activity_image_slider;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        CustomSliderView customSliderView1 = new CustomSliderView(this);
        customSliderView1
                .description("房产证还没办下来？小心住着住着房子被查封了")
                .image("http://pic2.zhimg.com/ccba96a780750e78a3b5dca4f54d83dd.jpg");
        customSliderView1.setScaleType(BaseSliderView.ScaleType.CenterCrop);
        sliderShow.addSlider(customSliderView1);

        CustomSliderView customSliderView2 = new CustomSliderView(this);
        customSliderView2
                .description("普通人跟职业运动员到底有多大差距？")
                .image("http://pic4.zhimg.com/5b2cc43fe4a06e4b83f0cc5f326fc4cb.jpg");
        customSliderView2.setScaleType(BaseSliderView.ScaleType.CenterCrop);
        sliderShow.addSlider(customSliderView2);

        CustomSliderView customSliderView3 = new CustomSliderView(this);
        customSliderView3
                .description("读读日报 24 小时热门 TOP 5 · 互联网与赌博行业异曲同工")
                .image("http://pic2.zhimg.com/30740acc3bce6c0f2aa11fa9270eec25.jpg");
        customSliderView3.setScaleType(BaseSliderView.ScaleType.CenterCrop);
        sliderShow.addSlider(customSliderView3);

        sliderShow.setDuration(5000);

        sliderShow.setCustomIndicator(customIndicator);
    }

    @Override
    protected void onStop() {
        sliderShow.stopAutoCycle();
        super.onStop();
    }

    // 启动当前Activity
    public static void launch(Context context) {
        Intent intent = new Intent(context, ImageSliderActivity.class);
        context.startActivity(intent);
    }
}
