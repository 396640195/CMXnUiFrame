package com.xn.uiframe.demo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xn068074 on 2017/6/19.
 */

public class TabViewHolder implements View.OnClickListener {
    private List<ItemTabView> tabViews;
    private OnTabSelectListener mOnTabSelectListener;
    public TabViewHolder(LinearLayout container, Context context,OnTabSelectListener onTabSelectListener) {

        this.mOnTabSelectListener = onTabSelectListener;
        TextView invert = (TextView) container.findViewById(R.id.invert);
        ItemTabView invertTab = new ItemTabView(invert, getDrawable(invert, R.mipmap.icon_account_default, context), getDrawable(invert, R.mipmap.icon_account_focus, context));
        invertTab.setSelected(false);
        invert.setOnClickListener(this);

        TextView home = (TextView) container.findViewById(R.id.home);
        ItemTabView homeTab = new ItemTabView(home, getDrawable(invert, R.mipmap.icon_home_default, context), getDrawable(invert, R.mipmap.icon_home_focus, context));
        homeTab.setSelected(true);
        home.setOnClickListener(this);

        TextView set = (TextView) container.findViewById(R.id.set);
        ItemTabView setTab = new ItemTabView(set, getDrawable(invert, R.mipmap.icon_account_default, context), getDrawable(invert, R.mipmap.icon_account_focus, context));
        setTab.setSelected(false);
        set.setOnClickListener(this);

        tabViews = new ArrayList<>();
        tabViews.add(invertTab);
        tabViews.add(homeTab);
        tabViews.add(setTab);


    }

    private Drawable getDrawable(View view, int res, Context context) {
        Drawable d = context.getResources().getDrawable(res);
        d.setBounds(0,0,d.getMinimumWidth(),d.getMinimumHeight());
        return  d;
    }

    @Override
    public void onClick(View v) {
        int i=0;
        for (ItemTabView tabView : tabViews) {
            if (tabView.mTabView == v) {
                tabView.setSelected(true);
                mOnTabSelectListener.onTabSelected(++i);
            } else {
                tabView.setSelected(false);
                i++;
            }
        }

    }

    public interface OnTabSelectListener{
        void onTabSelected(int index);
    }
}
