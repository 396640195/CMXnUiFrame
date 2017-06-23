package com.xn.uiframe.layout;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xn.uiframe.PowerfulContainerLayout;
import com.xn.uiframe.R;
import com.xn.uiframe.animation.Easing;
import com.xn.uiframe.exception.UIFrameIllegalArgumentException;
import com.xn.uiframe.exception.UIFrameLayoutAlreadyExistException;
import com.xn.uiframe.interfaces.IContainerManager;

import java.util.List;

/**
 * <p>
 * 定义一个基本视图布局管理器 HeaderLayoutManager.
 * 该布局主要用来实现全屏遮罩视图，包括对话框,加载等待，异常展示这类全屏遮罩视图.
 * <br>
 * 基本视图是指组成界面的各个基本元素的布局,在这个框架中主要定义了几个如下几个基本视图:
 * 1.HeaderLayoutManager
 * 2.TopLayoutManager
 * 3.CenterLayoutManager
 * 4.BottomLayoutManager
 * 基它全屏类型的视图包括: DialogLayoutManager,FullScreenLayoutManager
 * <p>使用方法</p>
 * <code>
 *
     View fullScreen01, fullScreen02;

     @Override
     public FullScreenLayoutManager addExtraFullScreenView(IContainerManager container)
     {
         FullScreenLayoutManager fullScreenLayoutManager = FullScreenLayoutManager.buildLayoutManager(container);
         fullScreen01 = fullScreenLayoutManager.addLayout(R.layout.layout_full_screen_01);
         fullScreen02 = fullScreenLayoutManager.addLayout(R.layout.layout_full_screen_02);
         fullScreen01.setOnClickListener(this);
         fullScreen02.setOnClickListener(this);
         return fullScreenLayoutManager;
     }

 * </code>
 * Created by 陈真 on 2017/6/13.
 * Copyright © 2015 深圳市小牛在线互联网信息咨询有限公司 股东公司：深圳市小牛互联网金融服务有限公司 版权所有 备案号：粤ICP备14079927号  ICP证粤B2-20160194
 * </p>
 */

public class FullScreenLayoutManager extends AbstractLayoutManager {

    public FullScreenLayoutManager(IContainerManager mContainerManager) {
        super(mContainerManager);
    }
    public FullScreenLayoutManager(IContainerManager mContainerManager, int mLayer) {
        super(mContainerManager, mLayer);
    }

    @Override
    public void onLayout(int left, int top, int right, int bottom) {
        for(View view : mViewCollections) {
            /**
             * 提供默认的全屏布局实现,且只有在该View可见的时候，才绘制该视图;
             * */
            if (getVisibility() != View.VISIBLE) {
                continue;
            }

            /**获得当前布局的Margin参数**/
            ViewGroup.MarginLayoutParams marginLayoutParams = getMarginLayoutParams();
            int leftMargin = marginLayoutParams.leftMargin;
            int rightMargin = marginLayoutParams.rightMargin;
            int bottomMargin = marginLayoutParams.bottomMargin;
            int topMargin = marginLayoutParams.topMargin;

            view.layout(left + leftMargin, top + topMargin, right - rightMargin, bottom - bottomMargin);
        }
    }

    /**
     * 根据给定的布局文件，在容器中添加一个视图，并返回当前这个视图对象;
     * 如果容器中已经存在该类型的视图，则不充许再次添加.
     *
     * @param containerLayout 当前界面的顶层容器
     * @return 布局文件加载后的视图对象
     */
    public static FullScreenLayoutManager buildLayoutManager(IContainerManager containerLayout) {
        FullScreenLayoutManager fullScreenLayoutManager = new FullScreenLayoutManager(containerLayout, Layer.LAYER_FULL_SCREEN_EXTRA);
        return fullScreenLayoutManager;
    }

    @Override
    public void setVisibility(int visible) {
        super.setVisibility(visible);
        if(visible == View.VISIBLE && mLayer == Layer.LAYER_DIALOG_SCREEN){
            animateY(Easing.EasingAnimation.EaseOutBounce,1000);
        }
    }

    @Override
    public View addLayout(@LayoutRes int layout) {
        PowerfulContainerLayout powerfulContainer = (PowerfulContainerLayout) mContainerManager;
        View  view = LayoutInflater.from(powerfulContainer.getContext()).inflate(layout, powerfulContainer, false);
        mViewCollections.add(view);
        return view;
    }
}
