package com.jun.androidexample.bannerview;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Jun on 2016/7/12.
 */
class ViewPagerAdapter extends PagerAdapter {

    private List<ImageView> imageViewList;

    public ViewPagerAdapter(List<ImageView> imageViewList) {

        this.imageViewList = imageViewList;
    }

    @Override
    public int getCount() {

        return imageViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        container.addView(imageViewList.get(position));
        return imageViewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View) object);
    }
}
