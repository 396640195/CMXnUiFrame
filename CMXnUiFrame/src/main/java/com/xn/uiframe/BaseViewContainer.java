package com.xn.uiframe;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.xn.uiframe.animation.Easing;
import com.xn.uiframe.interfaces.IBaseViewContainer;
import com.xn.uiframe.interfaces.IBasicViewAdapter;
import com.xn.uiframe.interfaces.ILayoutManager;
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
    public void setElementViewVisible(ViewElement elementCategory, boolean visible) {
        ILayoutManager layoutManager = elementCategoryTypeToLayoutManager(elementCategory);
        if (layoutManager != null) {
            layoutManager.setVisible(visible ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public boolean isElementViewVisible(ViewElement elementCategory) {
        ILayoutManager layoutManager = elementCategoryTypeToLayoutManager(elementCategory);
        return layoutManager == null ? false : layoutManager.getVisibility() == View.VISIBLE ? true : false;
    }

    @Override
    public void animateY(ViewElement elementCategory, long duration) {
        ILayoutManager layoutManager = elementCategoryTypeToLayoutManager(elementCategory);
        if (layoutManager != null) {
            layoutManager.animateY(duration);
        }
    }

    @Override
    public void animateX(ViewElement elementCategory, long duration) {
        ILayoutManager layoutManager = elementCategoryTypeToLayoutManager(elementCategory);
        if (layoutManager != null) {
            layoutManager.animateX(duration);
        }
    }

    @Override
    public void animateXY(ViewElement elementCategory, long xDuration, long yDuration) {
        ILayoutManager layoutManager = elementCategoryTypeToLayoutManager(elementCategory);
        if (layoutManager != null) {
            layoutManager.animateXY(xDuration, yDuration);
        }
    }

    @Override
    public void animateY(ViewElement elementCategory, Easing.EasingAnimation easing, long duration) {
        ILayoutManager layoutManager = elementCategoryTypeToLayoutManager(elementCategory);
        if (layoutManager != null) {
            layoutManager.animateY(easing, duration);
        }
    }

    @Override
    public void animateX(ViewElement elementCategory, Easing.EasingAnimation easing, long duration) {
        ILayoutManager layoutManager = elementCategoryTypeToLayoutManager(elementCategory);
        if (layoutManager != null) {
            layoutManager.animateX(easing, duration);
        }
    }

    @Override
    public void animateXY(ViewElement elementCategory, Easing.EasingAnimation easing, long xDuration, long yDuration) {
        ILayoutManager layoutManager = elementCategoryTypeToLayoutManager(elementCategory);
        if (layoutManager != null) {
            layoutManager.animateXY(easing, xDuration, yDuration);
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

    private ILayoutManager elementCategoryTypeToLayoutManager(ViewElement category) {

        if (category.ordinal() == ViewElement.HeaderView.ordinal()) {
            return mHeaderLayoutManager;
        } else if (category.ordinal() == ViewElement.TopView.ordinal()) {
            return mTopLayoutManager;
        } else if (category.ordinal() == ViewElement.CenterView.ordinal()) {
            return mCenterLayoutManager;
        } else if (category.ordinal() == ViewElement.BottomView.ordinal()) {
            return mBottomLayoutManager;
        } else if (category.ordinal() == ViewElement.DialogView.ordinal()) {
            return mDialogViewManager;
        } else if (category.ordinal() == ViewElement.ErrorView.ordinal()) {
            return mErrorViewManager;
        } else if (category.ordinal() == ViewElement.ExtraView.ordinal()) {
            return mExtraFullViewManager;
        } else if (category.ordinal() == ViewElement.LoadView.ordinal()) {
            return mLoadViewManager;
        }
        return null;
    }
}
