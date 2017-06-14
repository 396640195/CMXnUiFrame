package com.xn.uiframe;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

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

    public BaseViewContainer(Activity context, IBasicViewAdapter adapter) {
        this.mContext = context;
        this.mBasicViewAdapter = adapter;
    }

    @Override
    public void setLoadViewVisible(boolean visible) {
        if(mLoadViewManager != null){
            mLoadViewManager.setVisible(visible ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public boolean isLoadViewVisible() {
        return mLoadViewManager == null ? false : mLoadViewManager.getVisibility() == View.VISIBLE ? true : false;
    }

    @Override
    public void setDialogViewVisible(boolean visible) {
        if(mDialogViewManager != null){
            mDialogViewManager.setVisible(visible ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public boolean isDialogViewVisible() {
        return mDialogViewManager == null ? false : mDialogViewManager.getVisibility() == View.VISIBLE ? true : false;
    }

    @Override
    public void setErrorViewVisible(boolean visible) {
        if(mErrorViewManager != null){
            mErrorViewManager.setVisible(visible ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public boolean isErrorViewVisible() {
        return mErrorViewManager == null ? false : mErrorViewManager.getVisibility() == View.VISIBLE ? true : false;
    }

    @Override
    public void setCenterViewVisible(boolean visible) {
        if(mCenterLayoutManager != null){
            mCenterLayoutManager.setVisible(visible ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public boolean isCenterViewVisible() {
        return mCenterLayoutManager == null ? false : mCenterLayoutManager.getVisibility() == View.VISIBLE ? true : false;
    }

    @Override
    public void setHeaderViewVisible(boolean visible) {
        if(mHeaderLayoutManager != null){
            mHeaderLayoutManager.setVisible(visible ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public boolean isHeaderViewVisible() {
        return mHeaderLayoutManager == null ? false : mHeaderLayoutManager.getVisibility() == View.VISIBLE ? true : false;
    }

    @Override
    public void setTopViewVisible(boolean visible) {
        if(mTopLayoutManager != null){
            mTopLayoutManager.setVisible(visible ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public boolean isTopViewVisible() {
        return mTopLayoutManager == null ? false : mTopLayoutManager.getVisibility() == View.VISIBLE ? true : false;
    }

    @Override
    public void setExtraFullViewVisible(boolean visible) {
        if(mErrorViewManager != null){
            mErrorViewManager.setVisible(visible ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public boolean isExtraFullViewVisible() {
        return mExtraFullViewManager == null ? false : mExtraFullViewManager.getVisibility() == View.VISIBLE ? true : false;
    }

    @Override
    public void setBottomViewVisible(boolean visible) {
        if(mBottomLayoutManager != null){
            mBottomLayoutManager.setVisible(visible ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public boolean isBottomViewVisible() {
        return mBottomLayoutManager == null ? false : mBottomLayoutManager.getVisibility() == View.VISIBLE ? true : false;
    }

    @Override
    public void setContainerBackgroundColor(int res) {
        if(mContainer != null){
            mContainer.setBackgroundColor(res);
        }
    }

    @Override
    public void setContainerBackgroundResource(int res) {
        if(mContainer != null){
            mContainer.setBackgroundResource(res);
        }
    }

    @Override
    public TextView setHeaderLeftText(int resource) {
        if (mHeaderLayoutManager != null) {
            return this.mHeaderLayoutManager.setHeaderLeftText(resource);
        }
        return null;
    }

    @Override
    public TextView setHeaderLeftImage(int resource) {
        if (mHeaderLayoutManager != null) {
            return mHeaderLayoutManager.setHeaderLeftImage(resource);
        }
        return null;
    }

    @Override
    public TextView setHeaderCenterText(int resource) {
        if (mHeaderLayoutManager != null) {
            return mHeaderLayoutManager.setHeaderCenterText(resource);
        }
        return null;
    }

    @Override
    public TextView setHeaderRightText(int resource) {
        if (mHeaderLayoutManager != null) {
            return mHeaderLayoutManager.setHeaderRightText(resource);
        }
        return null;
    }

    @Override
    public TextView setHeaderRightImage(int resource) {
        if (mHeaderLayoutManager != null) {
            return mHeaderLayoutManager.setHeaderRightImage(resource);
        }
        return null;
    }

    @Override
    public View onCreateView() {
        mContainer = (PowerfulContainerLayout) LayoutInflater.from(this.mContext).inflate(R.layout.ui_frame_container_layout, null, false);
        this.mHeaderLayoutManager = mBasicViewAdapter.addHeaderView(mContainer);
        this.mTopLayoutManager = mBasicViewAdapter.addTopView(mContainer);
        this.mBottomLayoutManager = mBasicViewAdapter.addBottomView(mContainer);
        this.mCenterLayoutManager = mBasicViewAdapter.addCenterView(mContainer);
        this.mLoadViewManager = mBasicViewAdapter.addLoadingView(mContainer);
        this.mErrorViewManager = mBasicViewAdapter.addErrorView(mContainer);
        this.mExtraFullViewManager = mBasicViewAdapter.addExtraTopView(mContainer);
        this.mDialogViewManager = mBasicViewAdapter.addDialogView(mContainer);
        return mContainer;
    }
}
