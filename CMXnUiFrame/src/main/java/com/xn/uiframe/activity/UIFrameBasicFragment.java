package com.xn.uiframe.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xn.uiframe.BaseViewContainer;
import com.xn.uiframe.animation.Easing;
import com.xn.uiframe.interfaces.IBaseViewContainer;
import com.xn.uiframe.interfaces.IBasicViewAdapter;
import com.xn.uiframe.interfaces.IContainerManager;
import com.xn.uiframe.interfaces.IViewCommonBehavior;
import com.xn.uiframe.ViewElement;
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
 * Created by 陈真 on 2017/6/13.
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

    @Override
    public void setElementViewVisible(ViewElement elementCategory, boolean visible) {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.setElementViewVisible(elementCategory, visible);
        }
    }

    @Override
    public boolean isElementViewVisible(ViewElement element) {
        if (mBaseViewContainer != null) {
            return mBaseViewContainer.isElementViewVisible(element);
        }
        return false;
    }

    @Override
    public void animateY(ViewElement elementCategory, long duration) {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.animateY(elementCategory,duration);
        }
    }

    @Override
    public void animateX(ViewElement elementCategory, long duration) {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.animateX(elementCategory,duration);
        }
    }

    @Override
    public void animateXY(ViewElement elementCategory, long xDuration, long yDuration) {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.animateXY(elementCategory,xDuration,yDuration);
        }
    }

    @Override
    public void animateY(ViewElement elementCategory, Easing.EasingAnimation easing, long duration) {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.animateY(elementCategory,easing,duration);
        }
    }

    @Override
    public void animateX(ViewElement elementCategory, Easing.EasingAnimation easing, long duration) {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.animateX(elementCategory,easing,duration);
        }
    }

    @Override
    public void animateXY(ViewElement elementCategory, Easing.EasingAnimation easing, long xDuration, long yDuration) {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.animateXY(elementCategory,easing,xDuration,yDuration);
        }
    }
}
