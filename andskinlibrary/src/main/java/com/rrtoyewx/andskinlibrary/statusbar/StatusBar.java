package com.rrtoyewx.andskinlibrary.statusbar;

import android.view.Window;

import com.rrtoyewx.andskinlibrary.base.BaseSkinActivity;
import com.rrtoyewx.andskinlibrary.constant.ConfigConstants;
import com.rrtoyewx.andskinlibrary.interfaces.IChangeSkin;
import com.rrtoyewx.andskinlibrary.manager.ResourceManager;
import com.rrtoyewx.andskinlibrary.resource.Resource;
import com.rrtoyewx.andskinlibrary.util.SkinL;

/**
 * Created by Rrtoyewx on 2016/11/17.
 * StatusBar
 */

public abstract class StatusBar implements IChangeSkin {
    protected BaseSkinActivity mBaseSkinActivity;
    protected Window mWindow;
    protected String mStatusBarColorName = ConfigConstants.NAME_STATUS_BAR_COLOR;
    protected int mFindColor;

    StatusBar(BaseSkinActivity baseSkinActivity) {
        this.mBaseSkinActivity = baseSkinActivity;
        this.mWindow = mBaseSkinActivity.getWindow();
        initEnvironment();
    }

    abstract void initEnvironment();

    void setStatusBarColorName(String colourColorName) {
        mStatusBarColorName = colourColorName;
    }

    final int getColor() {
        return ResourceManager.getDefault().getDataResource().getColorByName(mStatusBarColorName);
    }

    final void resetFindColor(){
        mFindColor = Resource.VALUE_ERROR_COLOR;
    }
}
