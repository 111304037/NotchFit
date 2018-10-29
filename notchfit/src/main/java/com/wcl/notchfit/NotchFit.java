package com.wcl.notchfit;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.wcl.notchfit.args.NotchProperty;
import com.wcl.notchfit.args.NotchScreenType;
import com.wcl.notchfit.core.NotchFactory;
import com.wcl.notchfit.core.OnNotchCallBack;
import com.wcl.notchfit.utils.ActivityUtils;

/**
 * 刘海屏适配
 * Created by wangchunlong on 2018/10/24.
 */

public class NotchFit {
    /**
     * 设定Activity窗口显示模式{@link NotchScreenType}，使用并延伸布局至刘海区域
     * @param activity
     * @param notchScreenType Activity显示模式
     */
    public static void fit(Activity activity, NotchScreenType notchScreenType){
        fit(activity, notchScreenType, null);
    }
    /**
     * 通过设定Activity活动窗口显示样式，使用并延伸布局至刘海区域
     * ，便可通过回调函数获取刘海参数用来给布局做适配
     * @param activity 需要适配的活动窗口页
     * @param notchScreenType 适配刘海区域的屏幕显示样式。
     * <p>{@link NotchScreenType#FULL_SCREEN}:全屏刘海;
     * <p>{@link NotchScreenType#TRANSLUCENT}:沉浸式刘海
     * <p>{@link NotchScreenType#CUSTOM}:用户自定义Activity显示样式
     * @param onNotchCallBack 刘海参数获取回调接口
     */
    public static void fit(Activity activity, NotchScreenType notchScreenType, OnNotchCallBack onNotchCallBack){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return;
        }

        if(notchScreenType != NotchScreenType.CUSTOM) {
            NotchFactory.getInstance().getNotch().applyNotch(activity, true);
        }

        if(notchScreenType == NotchScreenType.FULL_SCREEN){
            ActivityUtils.setFullScreen(activity);
        }
        else if(notchScreenType == NotchScreenType.TRANSLUCENT){
            ActivityUtils.setTranslucent(activity);
        }

        NotchFactory.getInstance().getNotch().obtainNotch(activity, onNotchCallBack);
    }

    /**
     * 对有延伸到刘海区域的布局活动窗口，可使窗口布局不使用刘海区域，处理刘海区域用黑条填充
     * @param activity 需要刘海适配的活动页
     */
    public static void fitUnUse(final Activity activity){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return;
        }
        NotchFactory.getInstance().getNotch().applyNotch(activity, true);
        NotchFactory.getInstance().getNotch().obtainNotch(activity, new OnNotchCallBack() {
            @Override
            public void onNotchReady(NotchProperty notchProperty) {
                if(notchProperty.isNotchEnable() && notchProperty.getNotchHeight() != 0){
                    ViewGroup windowRootView = (ViewGroup) activity.getWindow().getDecorView().getRootView();
                    int childCount = windowRootView.getChildCount();
                    for (int index = 0; index < childCount; index++) {
                        View childView = windowRootView.getChildAt(index);

                        View notchView = new View(windowRootView.getContext());
                        notchView.setBackgroundColor(Color.BLACK);
                        FrameLayout.LayoutParams notchViewLayoutParams =
                                new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, notchProperty.getNotchHeight());
                        notchViewLayoutParams.gravity = Gravity.TOP;

                        ViewGroup.MarginLayoutParams contentLayoutParams = (ViewGroup.MarginLayoutParams) childView.getLayoutParams();
                        contentLayoutParams.topMargin = notchProperty.getNotchHeight();
                        windowRootView.addView(notchView, notchViewLayoutParams);
                    }
                }
            }
        });
    }

}
