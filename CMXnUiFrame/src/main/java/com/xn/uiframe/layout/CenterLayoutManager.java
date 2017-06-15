package com.xn.uiframe.layout;

import android.view.View;
import android.view.ViewGroup;

import com.xn.uiframe.exception.UIFrameLayoutAlreadyExistException;
import com.xn.uiframe.interfaces.IContainerManager;
import com.xn.uiframe.interfaces.ILayoutManager;

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
 * 3.CenterLayoutManager
 * 4.BottomLayoutManager
 * 基它全屏类型的视图包括: Dialog,LoadView,ErrorView,ExtraView(备用全屏视图)
 * 这几个全屏视图都通过 FullScreenLayoutManager 来实现，只需要给定它的类型参数指定它属于哪个视图类型;
 * <p>
 * Created by 陈真 on 2017/6/13.
 * Copyright © 2015 深圳市小牛在线互联网信息咨询有限公司 股东公司：深圳市小牛互联网金融服务有限公司 版权所有 备案号：粤ICP备14079927号  ICP证粤B2-20160194
 * </p>
 */

public class CenterLayoutManager extends AbstractLayoutManager {
    public CenterLayoutManager(IContainerManager mContainerManager) {
        super(mContainerManager);
        this.mLayer = Layer.LAYER_BASIC_CENTER_PART;
    }

    @Override
    public void onLayout(int left, int top, int right, int bottom) {
        /**如果不可见，则对该布局不进行处理;**/
        if (getVisibility() != View.VISIBLE) {
            return;
        }
        /**计算Center上下被占用的空间高度**/
        int upTopMargin = 0;
        List<ILayoutManager<View, ILayoutManager>> managers = mContainerManager.layoutManagers();
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
        mView.layout(left + leftMargin, topPosition, right - rightMargin, topPosition + mView.getMeasuredHeight());
        System.out.println("width="+getMeasuredWidth()+",height="+getMeasuredHeight());
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /**
         * 当View处于{@link View.VISIBLE} 才测量它的宽高;
         */
        if (getVisibility() != View.VISIBLE) {
            return;
        }
        //获得基本视图所占高度
        int basicLayoutHeights = 0;
        List<ILayoutManager<View, ILayoutManager>> layoutManagers = mContainerManager.layoutManagers();
        for (ILayoutManager<View, ILayoutManager> layoutManager : layoutManagers) {
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

        int basicWidthSpec = View.MeasureSpec.makeMeasureSpec((int) (basicWidth * mViewAnimator.getPhaseX()), View.MeasureSpec.EXACTLY);
        int basicHeightSpec = View.MeasureSpec.makeMeasureSpec((int) (basicHeight * mViewAnimator.getPhaseY()), View.MeasureSpec.EXACTLY);

        //测量当前布局的高宽
        mContainerManager.measureChild(mView, basicWidthSpec, basicHeightSpec);
    }

    /**
     * 根据给定的布局文件，在容器中添加一个视图，并返回当前这个视图对象;
     * 如果容器中已经存在该类型的视图，则不充许再次添加.
     *
     * @param containerLayout 当前界面的顶层容器
     * @param layout          需要添加的布局文件
     * @return 布局文件加载后的视图布局Manager对象
     */
    public static CenterLayoutManager buildLayout(IContainerManager containerLayout, int layout) {
        CenterLayoutManager center = new CenterLayoutManager(containerLayout);
        if (containerLayout.contains(center)) {
            throw new UIFrameLayoutAlreadyExistException("Center视图已经添加到容器当中了，该视图不能重复添加.");
        } else {
            center.addLayout(layout);
            containerLayout.addLayoutManager(center);
        }
        return center;
    }
}
