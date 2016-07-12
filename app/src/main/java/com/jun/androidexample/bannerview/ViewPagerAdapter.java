package com.jun.androidexample.bannerview;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jun.androidexample.R;

import java.util.List;

/**
 * Created by Jun on 2016/7/12.
 */
class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private List<String> imageUrlList;

    public ViewPagerAdapter(Context context, List<String> imageUrlList) {

        this.context = context;
        this.imageUrlList = imageUrlList;
    }

    @Override
    public int getCount() {

        return imageUrlList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_banner_page, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.banner_image_view);
        Glide.with(context).load(imageUrlList.get(position)).into(imageView);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View) object);
    }
}
