package com.xn.uiframe.layout;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xn.uiframe.PowerfulContainerLayout;
import com.xn.uiframe.exception.UIFrameLayoutAlreadyExistException;
import com.xn.uiframe.interfaces.IContainerManager;
import com.xn.uiframe.interfaces.ILayoutManager;

import java.util.List;

/**
 * <p>
 * 该布局主要是定义一个局部层级，用来在中间视图之上显示无数据，异常信息的一个特殊层
 * <p>
 * <br>
 * 基本视图是指组成界面的各个基本元素的布局,在这个框架中主要定义了几个如下几个基本视图:
 * 1.HeaderLayoutManager
 * 2.TopLayoutManager
 * 3.CenterLayoutManager,CenterMaskLayoutManager (用来在中间视图之上显示无数据，异常信息的层)
 * 4.BottomLayoutManager
 * 基它全屏类型的视图包括: DialogLayoutManager,FullScreenLayoutManager
 * <p>
 *     <p>使用方法</p>
 *     <code>
         private View mMask01, mMask02;
         @Override
         public CenterMaskLayoutManager addCenterMaskView(IContainerManager container)
         {

             CenterMaskLayoutManager clt = CenterMaskLayoutManager.buildLayoutManager(container);
             mMask01 = clt.addLayout(R.layout.layout_center_mask01);
             mMask02 = clt.addLayout(R.layout.layout_center_mask02);

             //只有一个mask视图可以用这个方法
             //clt.setVisibility(View.GONE);
             mMask01.setVisibility(View.GONE);
             mMask02.setVisibility(View.GONE);

             mMask01.setOnClickListener(this);
             mMask02.setOnClickListener(this);

             return clt;
         }
 </code>
 * Created by 陈真 on 2017/6/13.
 * Copyright © 2015 深圳市小牛在线互联网信息咨询有限公司 股东公司：深圳市小牛互联网金融服务有限公司 版权所有 备案号：粤ICP备14079927号  ICP证粤B2-20160194
 * </p>
 */

public class CenterMaskLayoutManager extends AbstractLayoutManager{

    public CenterMaskLayoutManager(IContainerManager mContainerManager) {
        super(mContainerManager);
        this.mLayer = Layer.LAYER_PART_OF_BASIC_CENTER_MASK;
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
                if (layoutManager.getLayer() <= Layer.LAYER_PART_OF_BASIC_TOP && layoutManager.getVisibility() != View.GONE) {
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
                if (layoutManager.getLayer() < Layer.LAYER_PART_OF_BASIC_CENTER && layoutManager.getVisibility() != View.GONE) {
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
     * @return 布局文件加载后的视图布局Manager对象
     */
    public static CenterMaskLayoutManager buildLayoutManager(IContainerManager containerLayout) {
        CenterMaskLayoutManager center = new CenterMaskLayoutManager(containerLayout);
        if (containerLayout.contains(center)) {
            throw new UIFrameLayoutAlreadyExistException("CenterMask视图已经添加到容器当中了，该视图不能重复添加.");
        }
        return center;
    }

    @Override
    public View addLayout(@LayoutRes int layout) {
        PowerfulContainerLayout powerfulContainer = (PowerfulContainerLayout) mContainerManager;
        View  view = LayoutInflater.from(powerfulContainer.getContext()).inflate(layout, powerfulContainer, false);
        mViewCollections.add(view);
        return view;
    }
}
