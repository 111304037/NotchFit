package com.wcl.notchfit.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by wangchunlong on 2018/10/24.
 */

public class ActivityUtils {
    /**
     * 设置全屏显示
     * @param activity
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void setFullScreen(Activity activity){
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    /**
     * 沉浸式状态栏
     *
     * @param activity 需要设置的activity
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setTranslucent(Activity activity) {
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
    }
}
