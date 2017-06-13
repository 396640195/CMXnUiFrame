package com.xn.uiframe.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;

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

/**
 * Created by xn068074 on 2017/6/13.
 */

public abstract class UIFrameBasicActivity extends FragmentActivity implements IBasicViewAdapter, IViewCommonBehavior {

    protected IBaseViewContainer mBaseViewContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateView();
    }

    protected  void onCreateView(){
        mBaseViewContainer = new BaseViewContainer(this, this);
        this.setContentView(mBaseViewContainer.onCreateView());
    }

    @Override
    public HeaderLayoutManager addHeaderView(IContainerManager container) {
        return  null;
    }

    @Override
    public TopLayoutManager addTopView(IContainerManager container) {
        return  null;
    }

    @Override
    public BottomLayoutManager addBottomView(IContainerManager container) {
        return  null;
    }

    @Override
    public CenterLayoutManager addCenterView(IContainerManager container) {
        return  null;
    }

    @Override
    public FullScreenLayoutManager addDialogView(IContainerManager container) {
        return  null;
    }

    @Override
    public FullScreenLayoutManager addErrorView(IContainerManager container) {
        return  null;
    }

    @Override
    public FullScreenLayoutManager addLoadingView(IContainerManager container) {
        return  null;
    }

    @Override
    public FullScreenLayoutManager addExtraTopView(IContainerManager container) {
        return  null;
    }

    @Override
    final public void showLoadingView() {
        mBaseViewContainer.showLoadingView();
    }

    @Override
    final public void hideLoadingView() {
        mBaseViewContainer.hideLoadingView();
    }

    @Override
    final public void showDialogView() {
        mBaseViewContainer.showDialogView();
    }

    @Override
    final public void hideDialogView() {
        mBaseViewContainer.hideDialogView();
    }

    @Override
    final public void showErrorView() {
        mBaseViewContainer.showErrorView();
    }

    @Override
    final public void hideErrorView() {
        mBaseViewContainer.hideErrorView();
    }

    @Override
    final public void showCenterView() {
        mBaseViewContainer.showCenterView();
    }

    @Override
    final public void hideCenterView() {
        mBaseViewContainer.hideCenterView();
    }

    @Override
    final public void showHeaderView() {
        mBaseViewContainer.showHeaderView();
    }

    @Override
    final public void hideHeaderView() {
        mBaseViewContainer.hideHeaderView();
    }

    @Override
    final public void showTopView() {
        mBaseViewContainer.showTopView();
    }

    @Override
    final public void hideTopView() {
        mBaseViewContainer.hideTopView();
    }

    @Override
    final public void setHeaderLeftText(int resource) {
        mBaseViewContainer.setHeaderLeftText(resource);
    }

    @Override
    final public void setHeaderLeftImage(int resource) {
        mBaseViewContainer.setHeaderLeftImage(resource);
    }

    @Override
    final public void setHeaderCenterText(int resource) {
        mBaseViewContainer.setHeaderCenterText(resource);
    }

    @Override
    final public void setHeaderRightText(int resource) {
        mBaseViewContainer.setHeaderRightText(resource);
    }

    @Override
    final public void setHeaderRightImage(int resource) {
        mBaseViewContainer.setHeaderRightImage(resource);
    }
}
