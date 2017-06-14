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
    public void showLoadingView() {
        if (mLoadViewManager != null) {
            this.mLoadViewManager.setVisible(View.VISIBLE);
        }
    }

    @Override
    public void hideLoadingView() {
        if (mLoadViewManager != null) {
            this.mLoadViewManager.setVisible(View.GONE);
        }
    }

    @Override
    public void showDialogView() {
        if (mDialogViewManager != null) {
            this.mDialogViewManager.setVisible(View.VISIBLE);
        }
    }

    @Override
    public void hideDialogView() {
        if (mDialogViewManager != null) {
            this.mDialogViewManager.setVisible(View.GONE);
        }
    }

    @Override
    public void showErrorView() {
        if (mErrorViewManager != null) {
            this.mErrorViewManager.setVisible(View.VISIBLE);
        }
    }

    @Override
    public void hideErrorView() {
        if (mErrorViewManager != null) {
            this.mErrorViewManager.setVisible(View.GONE);
        }
    }

    @Override
    public void showCenterView() {
        if (mCenterLayoutManager != null) {
            this.mCenterLayoutManager.setVisible(View.VISIBLE);
        }
    }

    @Override
    public void hideCenterView() {
        if (mCenterLayoutManager != null) {
            this.mCenterLayoutManager.setVisible(View.GONE);
        }
    }

    @Override
    public void showHeaderView() {
        if (mHeaderLayoutManager != null) {
            this.mHeaderLayoutManager.setVisible(View.VISIBLE);
        }
    }

    @Override
    public void hideHeaderView() {
        if (mHeaderLayoutManager != null) {
            this.mHeaderLayoutManager.setVisible(View.GONE);
        }
    }

    @Override
    public void showTopView() {
        if (mTopLayoutManager != null) {
            this.mTopLayoutManager.setVisible(View.VISIBLE);
        }
    }

    @Override
    public void hideTopView() {
        if (mTopLayoutManager != null) {
            this.mTopLayoutManager.setVisible(View.GONE);
        }
    }

    @Override
    public void showExtraFullView() {
        if (this.mExtraFullViewManager != null) {
            mExtraFullViewManager.setVisible(View.VISIBLE);
        }
    }

    @Override
    public void hideExtraFullView() {
        if (this.mExtraFullViewManager != null) {
            mExtraFullViewManager.setVisible(View.GONE);
        }
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
