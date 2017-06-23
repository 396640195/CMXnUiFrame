package com.xn.uiframe.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xn.uiframe.BaseViewContainer;
import com.xn.uiframe.ElementView;
import com.xn.uiframe.animation.Easing;
import com.xn.uiframe.interfaces.IBaseViewContainer;
import com.xn.uiframe.interfaces.IBasicViewAdapter;
import com.xn.uiframe.interfaces.IContainerManager;
import com.xn.uiframe.interfaces.IViewCommonBehavior;
import com.xn.uiframe.layout.BottomLayoutManager;
import com.xn.uiframe.layout.CenterLayoutManager;
import com.xn.uiframe.layout.CenterMaskLayoutManager;
import com.xn.uiframe.layout.DialogLayoutManager;
import com.xn.uiframe.layout.FullScreenLayoutManager;
import com.xn.uiframe.layout.HeaderLayoutManager;
import com.xn.uiframe.layout.TopLayoutManager;
import com.xn.uiframe.refreshlayout.OnRefreshListener;
import com.xn.uiframe.utils.EventBusProxy;

/**
 * <p>
 * 项目界面使用的通用基类UIFrameBasicFragment，可以定制需要的界面元素，包括:Header,Top,Center,Dialog,FullScreenView
 * 并且能对这些视图进行显示，隐藏控制。 同时封装了对EventBus注册和反注册,及Bundle的恢复处理逻辑;
 * Created by 陈真 on 2017/6/13.
 * Copyright © 2015 深圳市小牛在线互联网信息咨询有限公司 股东公司：深圳市小牛互联网金融服务有限公司 版权所有 备案号：粤ICP备14079927号  ICP证粤B2-20160194
 * </p>
 */

public abstract class UIFrameBasicFragment extends Fragment implements
        IBasicViewAdapter,
        IViewCommonBehavior,
        OnRefreshListener,
        HeaderLayoutManager.OnHeaderViewClickListener {

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
        /**设置下拉刷新事件**/
        this.setOnRefreshListener(this);
        /**设置头部视图点击事件**/
        this.setOnHeaderClickLister(this);
        return view;
    }

    @Override
    public void addCompanionScrollableHeader(CenterLayoutManager container) {

    }

    @Override
    public void addCompanionScrollableFooter(CenterLayoutManager container) {

    }

    @Override
    public void onCompanionViewAddFinished(CenterLayoutManager container) {

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
    public CenterMaskLayoutManager addCenterMaskView(IContainerManager container) {
        return null;
    }

    @Override
    public DialogLayoutManager addDialogView(IContainerManager container) {
        return null;
    }

    @Override
    public FullScreenLayoutManager addExtraFullScreenView(IContainerManager container) {
        return null;
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
    public void setElementViewVisible(ElementView elementCategory, boolean visible) {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.setElementViewVisible(elementCategory, visible);
        }
    }

    @Override
    public boolean isElementViewVisible(ElementView element) {
        if (mBaseViewContainer != null) {
            return mBaseViewContainer.isElementViewVisible(element);
        }
        return false;
    }

    @Override
    public void animateY(ElementView elementCategory, long duration) {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.animateY(elementCategory, duration);
        }
    }

    @Override
    public void animateX(ElementView elementCategory, long duration) {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.animateX(elementCategory, duration);
        }
    }

    @Override
    public void animateXY(ElementView elementCategory, long xDuration, long yDuration) {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.animateXY(elementCategory, xDuration, yDuration);
        }
    }

    @Override
    public void animateY(ElementView elementCategory, Easing.EasingAnimation easing, long duration) {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.animateY(elementCategory, easing, duration);
        }
    }

    @Override
    public void animateX(ElementView elementCategory, Easing.EasingAnimation easing, long duration) {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.animateX(elementCategory, easing, duration);
        }
    }

    @Override
    public void animateXY(ElementView elementCategory, Easing.EasingAnimation easing, long xDuration, long yDuration) {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.animateXY(elementCategory, easing, xDuration, yDuration);
        }
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void setOnRefreshListener(OnRefreshListener listener) {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.setOnRefreshListener(listener);
        }
    }

    @Override
    public void stopRefresh(boolean isSuccess) {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.stopRefresh(isSuccess);
        }
    }

    @Override
    public void stopLoadMore(boolean isSuccess) {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.stopLoadMore(isSuccess);
        }
    }

    @Override
    public void enableRefresh(boolean enable) {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.enableRefresh(enable);
        }
    }

    @Override
    public void enableLoadMore(boolean enable) {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.enableLoadMore(enable);
        }
    }

    @Override
    public void onAllViewConstructed() {

    }


    @Override
    public void setOnHeaderClickLister(HeaderLayoutManager.OnHeaderViewClickListener lister) {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.setOnHeaderClickLister(lister);
        }
    }

    @Override
    public void onLeftHeaderClicked() {

    }

    @Override
    public void onRightHeaderClicked() {

    }


    @Override
    public TextView setHeaderLeftText(String content) {
        if(mBaseViewContainer != null){
            return  mBaseViewContainer.setHeaderLeftText(content);
        }
        return null;
    }

    @Override
    public TextView setHeaderCenterText(String content) {
        if(mBaseViewContainer != null){
            return  mBaseViewContainer.setHeaderCenterText(content);
        }
        return null;
    }

    @Override
    public TextView setHeaderRightText(String content) {
        if(mBaseViewContainer != null){
            return  mBaseViewContainer.setHeaderRightText(content);
        }
        return null;
    }

    @Override
    public TextView setHeaderLeftDrawable(Drawable drawable) {
        if (mBaseViewContainer != null) {
            return mBaseViewContainer.setHeaderLeftDrawable(drawable);
        }
        return null;
    }

    @Override
    public TextView setHeaderRightDrawable(Drawable drawable) {
        if (mBaseViewContainer != null) {
            return mBaseViewContainer.setHeaderRightDrawable(drawable);
        }
        return null;
    }
}
