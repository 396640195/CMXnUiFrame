package com.xn.uiframe.layout;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.xn.uiframe.PowerfulContainerLayout;
import com.xn.uiframe.R;
import com.xn.uiframe.exception.UIFrameLayoutAlreadyExistException;
import com.xn.uiframe.interfaces.ICompanionViewManager;
import com.xn.uiframe.interfaces.IContainerManager;
import com.xn.uiframe.interfaces.ILayoutManager;
import com.xn.uiframe.interfaces.IPullRefreshBehavior;
import com.xn.uiframe.refreshlayout.OnRefreshListener;
import com.xn.uiframe.widget.UIFrameRefreshViewLayout;

import java.util.List;

/**
 * <p>
 * 定义一个基本视图布局管理器 CenterLayoutManager.
 * 该布局主要是定义一个层级，负责展示一个界面的主要内容.
 * 这个布局可能有上下拉刷新，列表ListView,GridView等占用屏幕大部分空间，主要用来展示界面的主要业务内容;
 * 所以该部局依赖于Header,Top,Bottom的布局在屏幕中占用的空间，来计算自已占有的空间.
 * <p>
 * <br>
 * 基本视图是指组成界面的各个基本元素的布局,在这个框架中主要定义了几个如下几个基本视图:
 * 1.HeaderLayoutManager
 * 2.TopLayoutManager
 * 3.CenterLayoutManager,CenterMaskLayoutManager (用来在中间视图之上显示无数据，异常信息的层)
 * 4.BottomLayoutManager
 * 基它全屏类型的视图包括: Dialog,LoadView,ErrorView,ExtraView(备用全屏视图)
 * 这几个全屏视图都通过 FullScreenLayoutManager 来实现，只需要给定它的类型参数指定它属于哪个视图类型;
 * <p>
 * Created by 陈真 on 2017/6/13.
 * Copyright © 2015 深圳市小牛在线互联网信息咨询有限公司 股东公司：深圳市小牛互联网金融服务有限公司 版权所有 备案号：粤ICP备14079927号  ICP证粤B2-20160194
 * </p>
 */

public class CenterLayoutManager extends AbstractLayoutManager implements
        IPullRefreshBehavior,
        ICompanionViewManager {

    private int mCenterBuildType;

    private ListView mListView;

    /**
     * 普通类型，不带pull refresh功能
     */
    public static final int CENTER_TYPE_GENERAL = 0x101;
    /**
     * 带pull refresh功能，带list view
     */
    public static final int CENTER_TYPE_PULL_LIST_VIEW = 0x102;

    public CenterLayoutManager(IContainerManager mContainerManager) {
        super(mContainerManager);
        this.mLayer = Layer.LAYER_BASIC_CENTER_PART;
    }

    @Override
    public void onLayout(int left, int top, int right, int bottom) {
        for(View view : mViewCollections) {
            /**如果不可见，则对该布局不进行处理;**/
            if (view.getVisibility() != View.VISIBLE) {
                continue;
            }
            /**计算Center上下被占用的空间高度**/
            int upTopMargin = 0;
            List<ILayoutManager<ILayoutManager>> managers = mContainerManager.layoutManagers();
            for (ILayoutManager layoutManager : managers) {
                if (layoutManager.getLayer() <= Layer.LAYER_BASIC_TOP_PART && layoutManager.getVisibility() != View.GONE) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = layoutManager.getMarginLayoutParams();
                    upTopMargin += (marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + layoutManager.getMeasuredHeight());
                }
            }

            /**获得当前布局的Margin参数**/
            ViewGroup.MarginLayoutParams marginLayoutParams = getMarginLayoutParams();
            int leftMargin = marginLayoutParams.leftMargin;
            int rightMargin = marginLayoutParams.rightMargin;
            int topMargin = marginLayoutParams.topMargin;
            int topPosition = top + topMargin + upTopMargin;
            int measuredHeight = view.getMeasuredHeight();
            view.layout(left + leftMargin, topPosition, right - rightMargin, topPosition + measuredHeight);
        }
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        for(View view : mViewCollections) {
            /**
             * 当View处于{@link View.VISIBLE} 才测量它的宽高;
             */
            if (view.getVisibility() != View.VISIBLE) {
                continue;
            }
            //获得基本视图所占高度
            int basicLayoutHeights = 0;
            List<ILayoutManager<ILayoutManager>> layoutManagers = mContainerManager.layoutManagers();
            for (ILayoutManager<ILayoutManager> layoutManager : layoutManagers) {
                if (layoutManager.getLayer() < mLayer && layoutManager.getVisibility() != View.GONE) {
                    basicLayoutHeights += layoutManager.getMeasuredHeight();
                    ViewGroup.MarginLayoutParams marginLayoutParams = layoutManager.getMarginLayoutParams();
                    basicLayoutHeights += (marginLayoutParams.topMargin + marginLayoutParams.bottomMargin);
                }
            }
            //获得当前容器布局宽和高
            int containerWidth = View.MeasureSpec.getSize(widthMeasureSpec);
            int containerHeight = View.MeasureSpec.getSize(heightMeasureSpec);

            //获得当前布局的Margin参数
            ViewGroup.MarginLayoutParams marginLayoutParams = getMarginLayoutParams();
            int leftMargin = marginLayoutParams.leftMargin;
            int rightMargin = marginLayoutParams.rightMargin;
            int topMargin = marginLayoutParams.topMargin;
            int bottomMarin = marginLayoutParams.bottomMargin;

            //计算当前布局的测量基准数据
            int basicWidth = containerWidth - leftMargin - rightMargin;
            int basicHeight = containerHeight - topMargin - bottomMarin - basicLayoutHeights;

            int basicWidthSpec = View.MeasureSpec.makeMeasureSpec((int) (basicWidth * this.mUIFrameViewAnimator.getPhaseX()), View.MeasureSpec.EXACTLY);
            int basicHeightSpec = View.MeasureSpec.makeMeasureSpec((int) (basicHeight * this.mUIFrameViewAnimator.getPhaseY()), View.MeasureSpec.EXACTLY);

            //测量当前布局的高宽
            mContainerManager.measureChild(view, basicWidthSpec, basicHeightSpec);
        }
    }

    /**
     * 根据给定的布局文件，在容器中添加一个视图，并返回当前这个视图对象;
     * 如果容器中已经存在该类型的视图，则不充许再次添加.
     *
     * @param containerLayout 当前界面的顶层容器
     * @param layout          需要添加的布局文件
     * @return 布局文件加载后的视图布局Manager对象
     */
    public static CenterLayoutManager buildGeneralLayoutManager(IContainerManager containerLayout, @LayoutRes int layout) {
        CenterLayoutManager center = new CenterLayoutManager(containerLayout);
        if (containerLayout.contains(center)) {
            throw new UIFrameLayoutAlreadyExistException("Center视图已经添加到容器当中了，该视图不能重复添加.");
        } else {
            center.addLayout(layout);
            center.mCenterBuildType = CENTER_TYPE_GENERAL;
            containerLayout.addLayoutManager(center);
        }
        return center;
    }

    /**
     * 根据给定的布局文件，在容器中添加一个视图，并返回当前这个视图对象;
     * 如果容器中已经存在该类型的视图，则不充许再次添加.
     *
     * @param containerLayout 当前界面的顶层容器
     * @return 布局文件加载后的视图布局Manager对象
     */
    public static CenterLayoutManager buildPullRefreshLayoutWithListView(IContainerManager containerLayout) {
        CenterLayoutManager center = new CenterLayoutManager(containerLayout);
        if (containerLayout.contains(center)) {
            throw new UIFrameLayoutAlreadyExistException("Center视图已经添加到容器当中了，该视图不能重复添加.");
        } else {
            center.addLayout(R.layout.ui_frame_center_listview_layout);
            center.mListView = (ListView) center.getContentView().findViewById(R.id.ui_frame_center_list_view);
            center.mListView.setAdapter(new EmptyAdapter());
            center.mCenterBuildType = CENTER_TYPE_PULL_LIST_VIEW;

            containerLayout.addLayoutManager(center);
        }
        return center;
    }

    public void setOnRefreshListener(OnRefreshListener listener) {
        View view = getContentView();
        if (view instanceof UIFrameRefreshViewLayout) {
            UIFrameRefreshViewLayout wrapper = (UIFrameRefreshViewLayout) view;
            wrapper.setCanRefresh(true);
            wrapper.setCanLoad(true);
            wrapper.setOnRefreshListener(listener);
        }
    }

    @Override
    public void stopRefresh(boolean isSuccess) {
        View view = getContentView();
        if (view instanceof UIFrameRefreshViewLayout) {
            UIFrameRefreshViewLayout wrapper = (UIFrameRefreshViewLayout) view;
            wrapper.stopRefresh(isSuccess);
        }
    }

    @Override
    public void stopLoadMore(boolean isSuccess) {
        View view = getContentView();
        if (view instanceof UIFrameRefreshViewLayout) {
            UIFrameRefreshViewLayout wrapper = (UIFrameRefreshViewLayout) view;
            wrapper.stopLoadMore(isSuccess);
        }
    }

    @Override
    public void enableRefresh(boolean enable) {
        View view = getContentView();
        if (view instanceof UIFrameRefreshViewLayout) {
            UIFrameRefreshViewLayout wrapper = (UIFrameRefreshViewLayout) view;
            wrapper.setCanRefresh(enable);
        }
    }

    @Override
    public void enableLoadMore(boolean enable) {
        View view = getContentView();
        if (view instanceof UIFrameRefreshViewLayout) {
            UIFrameRefreshViewLayout wrapper = (UIFrameRefreshViewLayout) view;
            wrapper.setCanLoad(enable);
        }
    }

    @Override
    public View addCompanionScrollableHeader(@LayoutRes int layout) {

        PowerfulContainerLayout powerfulContainer = (PowerfulContainerLayout) mContainerManager;
        if (mListView != null) {
            View header = LayoutInflater.from(powerfulContainer.getContext()).inflate(layout, null, false);
            mListView.addHeaderView(header);
            return header;

        }
        return null;
    }

    @Override
    public View addCompanionScrollableFooter(@LayoutRes int layout) {
        View view = getContentView();
        PowerfulContainerLayout powerfulContainer = (PowerfulContainerLayout) mContainerManager;
        if (view instanceof UIFrameRefreshViewLayout) {
            View footer = LayoutInflater.from(powerfulContainer.getContext()).inflate(layout, null, false);
            mListView.addFooterView(footer);
            return footer;
        }
        return null;
    }

    public ListView getListView() {
        return mListView;
    }

    public int getCenterBuildType() {
        return mCenterBuildType;
    }


}

class EmptyAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}