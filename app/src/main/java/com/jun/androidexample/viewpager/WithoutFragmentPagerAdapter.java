package com.jun.androidexample.viewpager;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Jun on 2016/7/12.
 */
public class WithoutFragmentPagerAdapter extends PagerAdapter {

    private List<View> viewList;

    public WithoutFragmentPagerAdapter(List<View> viewList) {
        this.viewList = viewList;
    }

    /**
     * 页数
     *
     * @return 返回List<View>中View的个数
     */
    @Override
    public int getCount() {
        return viewList.size();
    }

    /**
     * ???
     *
     * @param view
     * @param object
     * @return 直接返回view == object
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * 销毁Item
     *
     * @param container 容器
     * @param position  位置
     * @param object    对象
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        // 从容器中移除相应位置的View
        container.removeView(viewList.get(position));
    }

    /**
     * 实例化Item
     *
     * @param container 容器
     * @param position  位置
     * @return 返回view
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        // 将相应位置的View添加到容器中
        container.addView(viewList.get(position));
        // 返回此相应位置的View
        return viewList.get(position);
    }
}
