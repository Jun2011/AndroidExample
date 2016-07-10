package com.jun.androidexample.customviewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 自定义ViewGroup
 */
public class CustomImageViewGroup extends ViewGroup {

    public CustomImageViewGroup(Context context) {
        this(context, null);
    }

    public CustomImageViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomImageViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {

        // 直接使用系统提供的MarginLayoutParams
        return new MarginLayoutParams(getContext(), attrs);
    }

    // 计算所有childView的宽度和高度然后根据childView的计算结果设置自己的宽和高
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // 获得此ViewGroup上级容器为其推荐的宽和高以及计算模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        int width = 0;
        int height = 0;

        // 计算出所有的childView的宽和高
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        // childView的个数
        int cCount = getChildCount();

        int cWidth = 0;
        int cHeight = 0;
        MarginLayoutParams cParams = null;

        // 用于计算左边两个childView的高度
        int lHeight = 0;
        // 用于计算右边两个childView的高度，最终高度取二者之间大值
        int rHeight = 0;

        // 用于计算上边两个childView的宽度
        int tWidth = 0;
        // 用于计算下面两个childView的宽度，最终宽度取二者之间大值
        int bWidth = 0;

        // 根据childView计算的出的宽和高，以及设置的margin计算容器的宽和高，
        // 主要用于容器是warp_content时。
        for (int i = 0; i < cCount; i++) {

            View childView = getChildAt(i);
            cWidth = childView.getMeasuredWidth();
            cHeight = childView.getMeasuredHeight();
            cParams = (MarginLayoutParams) childView.getLayoutParams();

            // 上面两个childView的总宽度
            if (i == 0 || i == 1) {
                tWidth += cWidth + cParams.leftMargin + cParams.rightMargin;
            }

            // 下面两个childView的总宽度
            if (i == 2 || i == 3) {
                bWidth += cWidth + cParams.leftMargin + cParams.rightMargin;
            }

            // 左边两个childView的总高度
            if (i == 0 || i == 2) {
                lHeight += cHeight + cParams.topMargin + cParams.bottomMargin;
            }

            // 右边两个childView的总高度
            if (i == 1 || i == 3) {
                rHeight += cHeight + cParams.topMargin + cParams.bottomMargin;
            }
        }

        // 最大宽度
        width = Math.max(tWidth, bWidth);
        // 最大高度
        height = Math.max(lHeight, rHeight);

        // 如果是wrap_content设置为我们计算的值
        // 否则：直接设置为父容器计算的值
        setMeasuredDimension((widthMode == MeasureSpec.EXACTLY) ? sizeWidth : width,
                (heightMode == MeasureSpec.EXACTLY) ? sizeHeight : height);
    }

    // 对其所有childView进行定位（设置childView的绘制区域）
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int cCount = getChildCount();
        int cWidth = 0;
        int cHeight = 0;
        MarginLayoutParams cParams = null;

        // 遍历所有childView根据其宽和高，以及margin进行布局。
        for (int i = 0; i < cCount; i++) {

            View childView = getChildAt(i);
            cWidth = childView.getMeasuredWidth();
            cHeight = childView.getMeasuredHeight();
            cParams = (MarginLayoutParams) childView.getLayoutParams();

            int cl = 0, ct = 0, cr = 0, cb = 0;

            // 设置左和顶
            switch (i) {
                // 设置在左上角
                case 0:
                    cl = cParams.leftMargin;
                    ct = cParams.topMargin;
                    break;
                // 设置在右上角
                case 1:
                    cl = getWidth() - (cWidth + cParams.leftMargin + cParams.rightMargin);
                    ct = cParams.topMargin;
                    break;
                // 设置在左下角
                case 2:
                    cl = cParams.leftMargin;
                    ct = getHeight() - (cHeight + cParams.bottomMargin);
                    break;
                // 设置在右下角
                case 3:
                    cl = getWidth() - (cWidth + cParams.leftMargin + cParams.rightMargin);
                    ct = getHeight() - (cHeight + cParams.bottomMargin);
                    break;
            }

            // 设置右和底
            cr = cl + cWidth;
            cb = ct + cHeight;

            // 对childView进行定位
            childView.layout(cl, ct, cr, cb);
        }
    }
}
