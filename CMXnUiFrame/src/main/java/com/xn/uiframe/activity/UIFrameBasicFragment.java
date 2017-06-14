package com.xn.uiframe.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xn.uiframe.BaseViewContainer;
import com.xn.uiframe.interfaces.IBaseViewContainer;
import com.xn.uiframe.interfaces.IBasicViewAdapter;
import com.xn.uiframe.interfaces.IContainerManager;
import com.xn.uiframe.interfaces.IViewCommonBehavior;
import com.xn.uiframe.layout.BottomLayoutManager;
import com.xn.uiframe.layout.CenterLayoutManager;
import com.xn.uiframe.layout.FullScreenLayoutManager;
import com.xn.uiframe.layout.HeaderLayoutManager;
import com.xn.uiframe.layout.TopLayoutManager;
import com.xn.uiframe.utils.EventBusProxy;

/**
 * <p>
 * 项目界面使用的通用基类Fragment，可以定制需要的界面元素，包括:Header,Top,Center,Dialog,LoadView,ErrorView,ExtraView.
 * 并且能对这些视图进行显示，隐藏控制。 同时封装了对EventBus注册和反注册,及Bundle的恢复处理逻辑;
 * Created by xn068074 on 2017/6/13.
 * Copyright © 2015 深圳市小牛在线互联网信息咨询有限公司 股东公司：深圳市小牛互联网金融服务有限公司 版权所有 备案号：粤ICP备14079927号  ICP证粤B2-20160194
 * </p>
 */

public class UIFrameBasicFragment extends Fragment implements IBasicViewAdapter, IViewCommonBehavior {

    protected IBaseViewContainer mBaseViewContainer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.isNeedEventBus()) {
            EventBusProxy.register(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.isNeedEventBus()) {
            EventBusProxy.unregister(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBaseViewContainer = new BaseViewContainer(getActivity(), this);
        View view = mBaseViewContainer.onCreateView();
        return view;
    }

    @Override
    public HeaderLayoutManager addHeaderView(IContainerManager container) {
        return null;
    }

    @Override
    public TopLayoutManager addTopView(IContainerManager container) {
        return null;
    }

    @Override
    public BottomLayoutManager addBottomView(IContainerManager container) {
        return null;
    }

    @Override
    public CenterLayoutManager addCenterView(IContainerManager container) {
        return null;
    }

    @Override
    public FullScreenLayoutManager addDialogView(IContainerManager container) {
        return null;
    }

    @Override
    public FullScreenLayoutManager addErrorView(IContainerManager container) {
        return null;
    }

    @Override
    public FullScreenLayoutManager addLoadingView(IContainerManager container) {
        return null;
    }

    @Override
    public FullScreenLayoutManager addExtraTopView(IContainerManager container) {
        return null;
    }
    @Override
    public void setLoadViewVisible(boolean visible) {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.setLoadViewVisible(visible);
        }
    }

    @Override
    public boolean isLoadViewVisible() {
        return mBaseViewContainer == null ? false : mBaseViewContainer.isLoadViewVisible();
    }

    @Override
    public void setDialogViewVisible(boolean visible) {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.setDialogViewVisible(visible);
        }
    }

    @Override
    public boolean isDialogViewVisible() {
        return mBaseViewContainer == null ? false : mBaseViewContainer.isDialogViewVisible();
    }

    @Override
    public void setErrorViewVisible(boolean visible) {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.setErrorViewVisible(visible);
        }
    }

    @Override
    public boolean isErrorViewVisible() {
        return mBaseViewContainer == null ? false : mBaseViewContainer.isErrorViewVisible();
    }

    @Override
    public void setCenterViewVisible(boolean visible) {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.setCenterViewVisible(visible);
        }
    }

    @Override
    public boolean isCenterViewVisible() {
        return mBaseViewContainer == null ? false : mBaseViewContainer.isCenterViewVisible();
    }

    @Override
    public void setHeaderViewVisible(boolean visible) {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.setHeaderViewVisible(visible);
        }
    }

    @Override
    public boolean isHeaderViewVisible() {
        return mBaseViewContainer == null ? false : mBaseViewContainer.isHeaderViewVisible();
    }

    @Override
    public void setTopViewVisible(boolean visible) {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.setTopViewVisible(visible);
        }
    }

    @Override
    public boolean isTopViewVisible() {
        return mBaseViewContainer == null ? false : mBaseViewContainer.isTopViewVisible();
    }

    @Override
    public void setExtraFullViewVisible(boolean visible) {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.setExtraFullViewVisible(visible);
        }
    }

    @Override
    public boolean isExtraFullViewVisible() {
        return mBaseViewContainer == null ? false : mBaseViewContainer.isExtraFullViewVisible();
    }

    @Override
    public void setBottomViewVisible(boolean visible) {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.setBottomViewVisible(visible);
        }
    }

    @Override
    public boolean isBottomViewVisible() {
        return mBaseViewContainer == null ? false : mBaseViewContainer.isBottomViewVisible();
    }

    @Override
    final public TextView setHeaderLeftText(int resource) {
        TextView textView = null;
        if (mBaseViewContainer != null) {
            textView = mBaseViewContainer.setHeaderLeftText(resource);
        }
        return textView;
    }

    @Override
    final public TextView setHeaderLeftImage(int resource) {
        TextView textView = null;
        if (mBaseViewContainer != null) {
            textView = mBaseViewContainer.setHeaderLeftImage(resource);
        }
        return textView;
    }

    @Override
    final public TextView setHeaderCenterText(int resource) {
        TextView textView = null;
        if (mBaseViewContainer != null) {
            textView = mBaseViewContainer.setHeaderCenterText(resource);
        }
        return textView;
    }

    @Override
    final public TextView setHeaderRightText(int resource) {
        TextView textView = null;
        if (mBaseViewContainer != null) {
            textView = mBaseViewContainer.setHeaderRightText(resource);
        }
        return textView;
    }

    @Override
    final public TextView setHeaderRightImage(int resource) {
        TextView textView = null;
        if (mBaseViewContainer != null) {
            textView = mBaseViewContainer.setHeaderRightImage(resource);
        }
        return textView;
    }


    @Override
    public void setContainerBackgroundColor(int res) {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.setContainerBackgroundColor(res);
        }
    }

    @Override
    public void setContainerBackgroundResource(int res) {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.setContainerBackgroundResource(res);
        }
    }

    @Override
    public boolean isNeedEventBus() {
        return false;
    }

    @Override
    public boolean isNeedPullRefresh() {
        return false;
    }
}
