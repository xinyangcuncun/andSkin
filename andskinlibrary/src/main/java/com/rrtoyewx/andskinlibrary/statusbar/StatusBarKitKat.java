package com.rrtoyewx.andskinlibrary.statusbar;

import android.annotation.TargetApi;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.rrtoyewx.andskinlibrary.base.BaseSkinActivity;
import com.rrtoyewx.andskinlibrary.resource.Resource;
import com.rrtoyewx.andskinlibrary.util.SkinL;

/**
 * Created by Rrtoyewx on 2016/11/17.
 * SDK.VERSION:19~20
 */
@TargetApi(19)
public class StatusBarKitKat extends StatusBar {
    private View mFakerView;

    StatusBarKitKat(BaseSkinActivity baseSkinActivity) {
        super(baseSkinActivity);
    }

    @Override
    void initEnvironment() {
        mWindow.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        int statusBarHeight = getStatusBarHeight();
        addViewToDecor(statusBarHeight);
        addMarginToContentView(statusBarHeight);

        ViewGroup contentView = (ViewGroup) mWindow.findViewById(Window.ID_ANDROID_CONTENT);
        View firstView = contentView.getChildAt(0);

    }

    @Override
    public boolean findResource() {
        resetFindColor();
        mFindColor = getColor();
        return mFindColor != Resource.VALUE_ERROR_COLOR;
    }

    @Override
    public void changeSkin() {
        if (mFakerView != null && mFindColor != Resource.VALUE_ERROR_COLOR) {
            mFakerView.setBackgroundColor(mFindColor);
        }
        resetFindColor();
    }

    private int getStatusBarHeight() {
        int result = 0;
        int resId = mBaseSkinActivity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            result = mBaseSkinActivity.getResources().getDimensionPixelOffset(resId);
        }
        return result;
    }

    private void addViewToDecor(int height) {
        if (mFakerView != null) {
            removeFakerView();
        }

        mFakerView = new View(mBaseSkinActivity);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
        mFakerView.setLayoutParams(layoutParams);

        ViewGroup decorView = (ViewGroup) mWindow.getDecorView();
        decorView.addView(mFakerView);
    }

    private void removeFakerView() {
        ViewGroup decorView = (ViewGroup) mWindow.getDecorView();
        decorView.removeView(mFakerView);
        mFakerView = null;
    }

    private void addMarginToContentView(int statusHeight) {
        ViewGroup contentView = (ViewGroup) mWindow.findViewById(Window.ID_ANDROID_CONTENT);
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) contentView.getLayoutParams();
        layoutParams.topMargin += statusHeight;
        contentView.setLayoutParams(layoutParams);
    }
}
