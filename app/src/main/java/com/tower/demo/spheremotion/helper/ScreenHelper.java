package com.tower.demo.spheremotion.helper;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * WindowHelper
 *
 * @author zpsong-tower <pingzisong2012@gmail.com>
 * @since 2021/9/6 21:10
 */
public class ScreenHelper {
    private static final int ILLEGAL_VALUE = -1;

    /**
     * 获取屏幕宽度
     *
     * @param context Context
     * @return 屏幕宽度像素值
     */
    public static int getScreenWidth(Context context) {
        if (context == null || context.getResources() == null) {
            return ILLEGAL_VALUE;
        }
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        if (displayMetrics == null) {
            return ILLEGAL_VALUE;
        }
        return displayMetrics.widthPixels;
    }

    /**
     * 获取屏幕高度
     *
     * @param context Context
     * @return 屏幕高度像素值
     */
    public static int getScreenHeight(Context context) {
        if (context == null || context.getResources() == null) {
            return ILLEGAL_VALUE;
        }
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        if (displayMetrics == null) {
            return ILLEGAL_VALUE;
        }
        return displayMetrics.heightPixels;
    }
}
