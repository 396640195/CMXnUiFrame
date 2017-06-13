package com.xn.uiframe;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;

import com.xn.uiframe.interfaces.IBaseViewContainer;
import com.xn.uiframe.interfaces.IBasicViewAdapter;
import com.xn.uiframe.layout.BottomLayoutManager;
import com.xn.uiframe.layout.CenterLayoutManager;
import com.xn.uiframe.layout.FullScreenLayoutManager;
import com.xn.uiframe.layout.HeaderLayoutManager;
import com.xn.uiframe.layout.TopLayoutManager;

/**
 * Created by xn068074 on 2017/6/13.
 */

public class BaseViewContainer implements IBaseViewContainer {
    private PowerfulContainerLayout mContainer;
    private IBasicViewAdapter mBasicViewAdapter;
    private Activity mContext;

    private TopLayoutManager mTopLayoutManager;
    private BottomLayoutManager mBottomLayoutManager;
    private CenterLayoutManager mCenterLayoutManager;
    private HeaderLayoutManager mHeaderLayoutManager;
    private FullScreenLayoutManager mLoadViewManager;
    private FullScreenLayoutManager mErrorViewManager;
    private FullScreenLayoutManager mDialogViewManager;
    private FullScreenLayoutManager mExtraFullViewManager;

    public BaseViewContainer(Activity context,IBasicViewAdapter adapter){
        this.mContext = context;
        this.mBasicViewAdapter =adapter;
    }
    @Override
    public void showLoadingView() {
        if(mLoadViewManager != null) {
            this.mLoadViewManager.setVisible(View.VISIBLE);
        }
    }

    @Override
    public void hideLoadingView() {
        if(mLoadViewManager != null) {
            this.mLoadViewManager.setVisible(View.GONE);
        }
    }

    @Override
    public void showDialogView() {
        if(mDialogViewManager != null) {
            this.mDialogViewManager.setVisible(View.VISIBLE);
        }
    }

    @Override
    public void hideDialogView() {
        if(mDialogViewManager != null) {
            this.mDialogViewManager.setVisible(View.GONE);
        }
    }

    @Override
    public void showErrorView() {
        if(mErrorViewManager != null) {
            this.mErrorViewManager.setVisible(View.VISIBLE);
        }
    }

    @Override
    public void hideErrorView() {
        if(mErrorViewManager != null) {
            this.mErrorViewManager.setVisible(View.GONE);
        }
    }

    @Override
    public void showCenterView() {
        if(mCenterLayoutManager != null) {
            this.mCenterLayoutManager.setVisible(View.VISIBLE);
        }
    }

    @Override
    public void hideCenterView() {
        if(mCenterLayoutManager != null) {
            this.mCenterLayoutManager.setVisible(View.GONE);
        }
    }

    @Override
    public void showHeaderView() {
        if(mHeaderLayoutManager != null) {
            this.mHeaderLayoutManager.setVisible(View.VISIBLE);
        }
    }

    @Override
    public void hideHeaderView() {
        if(mHeaderLayoutManager != null) {
            this.mHeaderLayoutManager.setVisible(View.GONE);
        }
    }

    @Override
    public void showTopView() {
        if(mTopLayoutManager != null) {
            this.mTopLayoutManager.setVisible(View.VISIBLE);
        }
    }

    @Override
    public void hideTopView() {
        if(mTopLayoutManager != null) {
            this.mTopLayoutManager.setVisible(View.GONE);
        }
    }

    @Override
    public void setHeaderLeftText(int resource) {
        if(mTopLayoutManager != null) {
            this.mTopLayoutManager.setVisible(View.VISIBLE);
        }
    }

    @Override
    public void setHeaderLeftImage(int resource) {

    }

    @Override
    public void setHeaderCenterText(int resource) {

    }

    @Override
    public void setHeaderRightText(int resource) {

    }

    @Override
    public void setHeaderRightImage(int resource) {

    }

    @Override
    public View onCreateView() {
        mContainer =(PowerfulContainerLayout)LayoutInflater.from(this.mContext).inflate(R.layout.ui_frame_container_layout,null,false);
        this.mHeaderLayoutManager = mBasicViewAdapter.addHeaderView(mContainer);
        this.mTopLayoutManager = mBasicViewAdapter.addTopView(mContainer);
        this.mBottomLayoutManager = mBasicViewAdapter.addBottomView(mContainer);
        this.mCenterLayoutManager = mBasicViewAdapter.addCenterView(mContainer);
        this.mLoadViewManager = mBasicViewAdapter.addLoadingView(mContainer);
        this.mErrorViewManager = mBasicViewAdapter.addErrorView(mContainer);
        this.mExtraFullViewManager = mBasicViewAdapter.addExtraTopView(mContainer);
        this.mDialogViewManager = mBasicViewAdapter.addDialogView(mContainer);
        return  mContainer;
    }
}
