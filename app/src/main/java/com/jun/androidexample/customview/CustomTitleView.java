package com.jun.androidexample.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.jun.androidexample.R;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 自定义View
 */
public class CustomTitleView extends View {

    // 声明自定义属性
    private String mCustomTitleText;
    private float mCustomTitleTextSize;
    private int mCustomTitleTextColor;

    private Paint mPaint;
    private Rect mBound;

    public CustomTitleView(Context context) {
        this(context, null);
    }

    public CustomTitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 获取自定义样式属性
        TypedArray a = context.getTheme()
                .obtainStyledAttributes(attrs, R.styleable.CustomTitleView, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.CustomTitleView_customTitleText:
                    mCustomTitleText = a.getString(attr);
                    break;
                // 设置默认字体大小为16sp，TypeValue也可以把sp转化为px
                case R.styleable.CustomTitleView_customTitleTextSize:
                    mCustomTitleTextSize = a.getDimensionPixelSize(attr,
                            (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_SP,
                                    16,
                                    getResources().getDisplayMetrics()));
                    break;
                // 设置默认字体颜色为黑色
                case R.styleable.CustomTitleView_customTitleTextColor:
                    mCustomTitleTextColor = a.getColor(attr, Color.BLACK);
                    break;
            }
        }
        // 最后记得释放资源
        a.recycle();

        // 初始化Paint和Rect
        mPaint = new Paint();
        mBound = new Rect();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        // EXACTLY表示视图的宽度就是layout_width设置确切的值
        // 或match_parent
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            mPaint.setTextSize(mCustomTitleTextSize);
            mPaint.getTextBounds(mCustomTitleText, 0, mCustomTitleText.length(), mBound);
            float textWidth = mBound.width();
            int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            width = desired;
        }

        // EXACTLY表示视图的宽度就是layout_height设置确切的值
        // 或match_parent
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            mPaint.setTextSize(mCustomTitleTextSize);
            mPaint.getTextBounds(mCustomTitleText, 0, mCustomTitleText.length(), mBound);
            float textHeight = mBound.height();
            int desired = (int) (getPaddingTop() + textHeight + getPaddingBottom());
            height = desired;
        }

        // 设置测量的宽高
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制一个蓝色矩形的背景
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        // 设置字体颜色
        mPaint.setColor(mCustomTitleTextColor);
        // 设置字体大小
        mPaint.setTextSize(mCustomTitleTextSize);
        // 获取文字的边界
        mPaint.getTextBounds(mCustomTitleText, 0, mCustomTitleText.length(), mBound);
        // 绘制文字
        canvas.drawText(mCustomTitleText,
                getWidth() / 2 - mBound.width() / 2,
                getHeight() / 2 + mBound.height() / 2,
                mPaint);
    }

    // 触摸事件处理
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 随机数Text
                mCustomTitleText = randomText();
                // 重绘
                invalidate();
                break;
        }
        return true;
    }

    // 随机数Text
    private String randomText() {

        // 随机数
        Random random = new Random();
        // 要求数字不能相同
        Set<Integer> set = new HashSet<>();

        // 循环4次
        while (set.size() < 4) {
            // [0,10)中的随机数
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }

        // 将数字串连在一起
        StringBuffer sb = new StringBuffer();
        for (Integer i : set) {
            sb.append("" + i);
        }

        return sb.toString();
    }
}
