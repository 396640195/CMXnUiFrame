package com.xn.uiframe;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.xn.uiframe.animation.Easing;
import com.xn.uiframe.interfaces.IBaseViewContainer;
import com.xn.uiframe.interfaces.IBasicViewAdapter;
import com.xn.uiframe.interfaces.ICompanionViewManager;
import com.xn.uiframe.interfaces.ILayoutManager;
import com.xn.uiframe.layout.BottomLayoutManager;
import com.xn.uiframe.layout.CenterLayoutManager;
import com.xn.uiframe.layout.CenterMaskLayoutManager;
import com.xn.uiframe.layout.DialogLayoutManager;
import com.xn.uiframe.layout.FullScreenLayoutManager;
import com.xn.uiframe.layout.HeaderLayoutManager;
import com.xn.uiframe.layout.TopLayoutManager;
import com.xn.uiframe.refreshlayout.OnRefreshListener;

/**
 *  UIFrame视图容器类，封装了UI基本操作，
 * Created by 陈真 on 2017/6/13.
 */

public class BaseViewContainer implements IBaseViewContainer,ICompanionViewManager {
    private PowerfulContainerLayout mContainer;
    private IBasicViewAdapter mBasicViewAdapter;
    private Activity mContext;

    private TopLayoutManager mTopLayoutManager;
    private BottomLayoutManager mBottomLayoutManager;
    private CenterLayoutManager mCenterLayoutManager;
    private CenterMaskLayoutManager mCenterMaskLayoutManager;
    private HeaderLayoutManager mHeaderLayoutManager;
    private DialogLayoutManager mDialogLayoutManager;
    private FullScreenLayoutManager mFullScreenLayoutManager;

    public BaseViewContainer(Activity context, IBasicViewAdapter adapter) {
        this.mContext = context;
        this.mBasicViewAdapter = adapter;
    }

    @Override
    public void setElementViewVisible(ElementView elementCategory, boolean visible) {
        ILayoutManager layoutManager = elementCategoryTypeToLayoutManager(elementCategory);
        if (layoutManager != null) {
            layoutManager.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public boolean isElementViewVisible(ElementView elementCategory) {
        ILayoutManager layoutManager = elementCategoryTypeToLayoutManager(elementCategory);
        return layoutManager == null ? false : layoutManager.getVisibility() == View.VISIBLE ? true : false;
    }

    @Override
    public void animateY(ElementView elementCategory, long duration) {
        ILayoutManager layoutManager = elementCategoryTypeToLayoutManager(elementCategory);
        if (layoutManager != null) {
            layoutManager.animateY(duration);
        }
    }

    @Override
    public void animateX(ElementView elementCategory, long duration) {
        ILayoutManager layoutManager = elementCategoryTypeToLayoutManager(elementCategory);
        if (layoutManager != null) {
            layoutManager.animateX(duration);
        }
    }

    @Override
    public void animateXY(ElementView elementCategory, long xDuration, long yDuration) {
        ILayoutManager layoutManager = elementCategoryTypeToLayoutManager(elementCategory);
        if (layoutManager != null) {
            layoutManager.animateXY(xDuration, yDuration);
        }
    }

    @Override
    public void animateY(ElementView elementCategory, Easing.EasingAnimation easing, long duration) {
        ILayoutManager layoutManager = elementCategoryTypeToLayoutManager(elementCategory);
        if (layoutManager != null) {
            layoutManager.animateY(easing, duration);
        }
    }

    @Override
    public void animateX(ElementView elementCategory, Easing.EasingAnimation easing, long duration) {
        ILayoutManager layoutManager = elementCategoryTypeToLayoutManager(elementCategory);
        if (layoutManager != null) {
            layoutManager.animateX(easing, duration);
        }
    }

    @Override
    public void animateXY(ElementView elementCategory, Easing.EasingAnimation easing, long xDuration, long yDuration) {
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
    public void setOnHeaderClickLister(HeaderLayoutManager.OnHeaderViewClickListener lister) {
        if(mHeaderLayoutManager != null) {
            this.mHeaderLayoutManager.setOnHeaderClickLister(lister);
        }
    }

    @Override
    public View onCreateView() {
        this.mContainer = (PowerfulContainerLayout) LayoutInflater.from(this.mContext).inflate(R.layout.ui_frame_container_layout, null, false);
        this.mHeaderLayoutManager = mBasicViewAdapter.addHeaderView(mContainer);
        this.mTopLayoutManager = mBasicViewAdapter.addTopView(mContainer);
        this.mBottomLayoutManager = mBasicViewAdapter.addBottomView(mContainer);

        this.mCenterLayoutManager = mBasicViewAdapter.addCenterView(mContainer);
        if(mCenterLayoutManager.getCenterBuildType() == CenterLayoutManager.CENTER_TYPE_PULL_LIST_VIEW) {
            this.mBasicViewAdapter.addCompanionScrollableHeader(this.mCenterLayoutManager);
            this.mBasicViewAdapter.addCompanionScrollableFooter(this.mCenterLayoutManager);
            this.mBasicViewAdapter.onCompanionViewAddFinished(this.mCenterLayoutManager);
        }

        this.mCenterMaskLayoutManager = mBasicViewAdapter.addCenterMaskView(mContainer);
        this.mContainer.addLayoutManager(this.mCenterMaskLayoutManager);

        this.mFullScreenLayoutManager = mBasicViewAdapter.addExtraFullScreenView(mContainer);
        this.mContainer.addLayoutManager(this.mFullScreenLayoutManager);

        this.mDialogLayoutManager = mBasicViewAdapter.addDialogView(mContainer);
        this.mContainer.addLayoutManager(this.mDialogLayoutManager);

        this.mBasicViewAdapter.onAllViewConstructed();

        return mContainer;
    }

    private ILayoutManager elementCategoryTypeToLayoutManager(ElementView category) {

        if (category  == ElementView.HeaderView ) {
            return mHeaderLayoutManager;
        } else if (category  == ElementView.TopView ) {
            return mTopLayoutManager;
        } else if (category  == ElementView.CenterView ){
            return mCenterLayoutManager;
        } else if(category == ElementView.CenterMaskView){
            return mCenterMaskLayoutManager;
        }else if (category == ElementView.BottomView ) {
            return mBottomLayoutManager;
        } else if (category == ElementView.DialogView ) {
            return mDialogLayoutManager;
        } else if (category == ElementView.FullScreenView ) {
            return mFullScreenLayoutManager;
        }
        return null;
    }

    @Override
    public void setOnRefreshListener(OnRefreshListener listener) {
        if (mCenterLayoutManager != null) {
            this.mCenterLayoutManager.setOnRefreshListener(listener);
        }
    }

    @Override
    public void stopRefresh(boolean isSuccess) {
        if (mCenterLayoutManager != null) {
            this.mCenterLayoutManager.stopRefresh(isSuccess);
        }
    }

    @Override
    public void stopLoadMore(boolean isSuccess) {
        if (mCenterLayoutManager != null) {
            this.mCenterLayoutManager.stopLoadMore(isSuccess);
        }
    }

    @Override
    public void enableRefresh(boolean enable) {
        if (mCenterLayoutManager != null) {
            this.mCenterLayoutManager.enableRefresh(enable);
        }
    }

    @Override
    public void enableLoadMore(boolean enable) {
        if (mCenterLayoutManager != null) {
            this.mCenterLayoutManager.enableLoadMore(enable);
        }
    }

    @Override
    public View addCompanionScrollableHeader(@LayoutRes int layout) {
        if (mCenterLayoutManager != null) {
            return this.mCenterLayoutManager.addCompanionScrollableHeader(layout);
        }
        return null;
    }

    @Override
    public View addCompanionScrollableFooter(@LayoutRes int layout) {
        if (mCenterLayoutManager != null) {
            return this.mCenterLayoutManager.addCompanionScrollableFooter(layout);
        }
        return null;
    }

    @Override
    public TextView setHeaderLeftText(String content) {
        if (mHeaderLayoutManager != null) {
           return mHeaderLayoutManager.setHeaderLeftText(content);
        }
        return null;
    }

    @Override
    public TextView setHeaderRightText(String content) {
        if (mHeaderLayoutManager != null) {
            return mHeaderLayoutManager.setHeaderRightText(content);
        }
        return null;
    }

    @Override
    public TextView setHeaderCenterText(String content) {
        if (mHeaderLayoutManager != null) {
            return mHeaderLayoutManager.setHeaderCenterText(content);
        }
        return null;
    }

    @Override
    public TextView setHeaderLeftDrawable(Drawable drawable) {
        if (mHeaderLayoutManager != null) {
            return mHeaderLayoutManager.setHeaderLeftDrawable(drawable);
        }
        return null;
    }

    @Override
    public TextView setHeaderRightDrawable(Drawable drawable) {
        if (mHeaderLayoutManager != null) {
            return mHeaderLayoutManager.setHeaderRightDrawable(drawable);
        }
        return null;
    }
}
