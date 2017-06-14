package com.xn.uiframe.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
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
 * 项目界面使用的通用基类FragmentActivity，可以定制需要的界面元素，包括:Header,Top,Center,Dialog,LoadView,ErrorView,ExtraView.
 * 并且能对这些视图进行显示，隐藏控制。 同时封装了对EventBus注册和反注册,及Bundle的恢复处理逻辑;
 * Created by xn068074 on 2017/6/13.
 * Copyright © 2015 深圳市小牛在线互联网信息咨询有限公司 股东公司：深圳市小牛互联网金融服务有限公司 版权所有 备案号：粤ICP备14079927号  ICP证粤B2-20160194
 * </p>
 */

public abstract class UIFrameBasicActivity extends FragmentActivity implements IBasicViewAdapter, IViewCommonBehavior {

    protected IBaseViewContainer mBaseViewContainer;
    private static final String SAVED_BUNDLE_KEY = "SAVED_BUNDLE_KEY";
    private Bundle mSavedInstanceState;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**初始化Bundle数据**/
        this.initBundle(savedInstanceState);
        /**创建视图,如果为空则忽略**/
        this.onCreateView();
        /**如果需要使用EventBus，则注册.**/
        if (this.isNeedEventBus()) {
            EventBusProxy.register(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.isNeedEventBus()) {
            EventBusProxy.unregister(this);
        }
    }

    /**
     * 封装bundle解决系统内部不足回收activity时getIntent数据为null
     * 兼容正常启动activity getIntent有数据的情况
     *
     * @param savedInstanceState
     */
    private void initBundle(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            mSavedInstanceState = getIntent().getExtras();
        } else {
            mSavedInstanceState = savedInstanceState.getBundle(SAVED_BUNDLE_KEY);
        }
        //防止空赋值
        if (mSavedInstanceState == null) {
            mSavedInstanceState = new Bundle();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBundle(SAVED_BUNDLE_KEY, mSavedInstanceState);
        super.onSaveInstanceState(outState);
    }

    protected void onCreateView() {
        mBaseViewContainer = new BaseViewContainer(this, this);
        View view = mBaseViewContainer.onCreateView();
        if (view != null) {
            this.setContentView(view);
        }
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
    final public void showLoadingView() {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.showLoadingView();
        }
    }

    @Override
    final public void hideLoadingView() {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.hideLoadingView();
        }
    }

    @Override
    final public void showDialogView() {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.showDialogView();
        }
    }

    @Override
    final public void hideDialogView() {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.hideDialogView();
        }
    }

    @Override
    final public void showErrorView() {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.showErrorView();
        }
    }

    @Override
    final public void hideErrorView() {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.hideErrorView();
        }
    }

    @Override
    final public void showCenterView() {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.showCenterView();
        }
    }

    @Override
    final public void hideCenterView() {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.hideCenterView();
        }
    }

    @Override
    final public void showHeaderView() {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.showHeaderView();
        }
    }

    @Override
    final public void hideHeaderView() {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.hideHeaderView();
        }
    }

    @Override
    final public void showTopView() {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.showTopView();
        }
    }

    @Override
    final public void hideTopView() {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.hideTopView();
        }
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
    public void showExtraFullView() {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.showExtraFullView();
        }
    }

    @Override
    public void hideExtraFullView() {
        if (mBaseViewContainer != null) {
            mBaseViewContainer.hideExtraFullView();
        }
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
