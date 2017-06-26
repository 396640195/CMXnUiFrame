package com.xiaoniu.uiframe.demo;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

/**
 * Created by xn068074 on 2017/6/19.
 */

public class ItemTabView {
    TextView mTabView;
    boolean isSelected;
    Drawable mDefaultDrawable;
    Drawable mSelectedDrawable;

    public ItemTabView(TextView mTabView, Drawable mDefaultDrawable, Drawable mSelectedDrawable) {
        this.mTabView = mTabView;
        this.mDefaultDrawable = mDefaultDrawable;
        this.mSelectedDrawable = mSelectedDrawable;
    }

    public void setSelected(boolean selected){
        isSelected = selected;
        mTabView.setCompoundDrawables(null,isSelected ? mSelectedDrawable : mDefaultDrawable,null,null);
    }
}
