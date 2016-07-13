package com.jun.androidexample.imageslider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.jun.androidexample.R;

/**
 * Created by Jun on 2016/7/13.
 */
public class CustomSliderView extends BaseSliderView {

    protected CustomSliderView(Context context) {
        super(context);
    }

    @Override
    public View getView() {

        View v = LayoutInflater.from(getContext()).inflate(R.layout.custom_slider_view,null);
        ImageView target = (ImageView)v.findViewById(R.id.daimajia_slider_image);
        TextView description = (TextView)v.findViewById(R.id.description);
        description.setText(getDescription());
        bindEventAndShow(v, target);
        return v;
    }
}
