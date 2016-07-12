package com.jun.androidexample.bannerview;

import android.content.Context;

/**
 * 转换工具类
 */
public class ConvertUtil {

    /**
     * dp转换成px
     *
     * @param context 上下文
     * @param value   多少dp
     * @return 返回转换后等于多少px
     */
    public static int dp2px(Context context, float value) {

        // 获取屏幕的密度：density = densityDpi/160
        final float scale = context.getResources().getDisplayMetrics().density;
        // dp转换成px，value*density
        // + 0.5f是为了四舍五入转换成int类型
        return (int) (value * scale + 0.5f);
    }

    public static int px2dp(Context context, float value) {

        final float scale = context.getResources().getDisplayMetrics().density;
        // px转换成dp，value/density
        return (int) (value / scale + 0.5f);
    }
}
