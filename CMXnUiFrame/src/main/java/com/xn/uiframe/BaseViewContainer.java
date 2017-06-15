package com.xn.uiframe;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.xn.uiframe.interfaces.IBaseViewContainer;
import com.xn.uiframe.interfaces.IBasicViewAdapter;
import com.xn.uiframe.interfaces.ILayoutManager;
import com.xn.uiframe.interfaces.ViewElementCategory;
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
    public void setElementViewVisible(ViewElementCategory elementCategory, boolean visible) {
        ILayoutManager layoutManager = elementCategoryTypeToLayoutManager(elementCategory);
        if (layoutManager != null) {
            layoutManager.setVisible(visible ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public boolean isElementViewVisible(ViewElementCategory elementCategory) {
        ILayoutManager layoutManager = elementCategoryTypeToLayoutManager(elementCategory);
        return layoutManager == null ? false : layoutManager.getVisibility() == View.VISIBLE ? true : false;
    }

    @Override
    public void animateY(ViewElementCategory elementCategory, long seconds) {
        ILayoutManager layoutManager = elementCategoryTypeToLayoutManager(elementCategory);
        if (layoutManager != null) {
            layoutManager.animateY(seconds);
        }
    }

    @Override
    public void animateX(ViewElementCategory elementCategory, long seconds) {
        ILayoutManager layoutManager = elementCategoryTypeToLayoutManager(elementCategory);
        if (layoutManager != null) {
            layoutManager.animateX(seconds);
        }
    }

    @Override
    public void animateXY(ViewElementCategory elementCategory, long seconds) {
        ILayoutManager layoutManager = elementCategoryTypeToLayoutManager(elementCategory);
        if (layoutManager != null) {
            layoutManager.animateXY(seconds);
        }
    }

    @Override
    public void setContainerBackgroundColor(int res) {
        if (mContainer != null) {
            mContainer.setBackgroundColor(res);
        }
    }

    @Override
    public void setContainerBackgroundResource(int res) {
        if (mContainer != null) {
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
        this.mContainer = (PowerfulContainerLayout) LayoutInflater.from(this.mContext).inflate(R.layout.ui_frame_container_layout, null, false);
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

    private ILayoutManager elementCategoryTypeToLayoutManager(ViewElementCategory category) {

        if (category.ordinal() == ViewElementCategory.HeaderView.ordinal()) {
            return mHeaderLayoutManager;
        } else if (category.ordinal() == ViewElementCategory.TopView.ordinal()) {
            return mTopLayoutManager;
        } else if (category.ordinal() == ViewElementCategory.CenterView.ordinal()) {
            return mCenterLayoutManager;
        } else if (category.ordinal() == ViewElementCategory.BottomView.ordinal()) {
            return mBottomLayoutManager;
        } else if (category.ordinal() == ViewElementCategory.DialogView.ordinal()) {
            return mDialogViewManager;
        } else if (category.ordinal() == ViewElementCategory.ErrorView.ordinal()) {
            return mErrorViewManager;
        } else if (category.ordinal() == ViewElementCategory.ExtraView.ordinal()) {
            return mExtraFullViewManager;
        } else if (category.ordinal() == ViewElementCategory.LoadView.ordinal()) {
            return mLoadViewManager;
        }
        return null;
    }
}
