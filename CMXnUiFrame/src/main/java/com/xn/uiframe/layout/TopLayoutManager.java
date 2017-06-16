package com.xn.uiframe.layout;

import android.view.View;
import android.view.ViewGroup;

import com.xn.uiframe.exception.UIFrameLayoutAlreadyExistException;
import com.xn.uiframe.interfaces.IContainerManager;
import com.xn.uiframe.interfaces.ILayoutManager;

import java.util.List;

/**
 * <p>
 * 定义一个基本视图布局管理器 TopLayoutManager.
 * <br>
 * 基本视图是指组成界面的各个基本元素的布局,在这个框架中主要定义了几个如下几个基本视图:
 * 1.HeaderLayoutManager
 * 2.TopLayoutManager
 * 3.CenterLayoutManager
 * 4.BottomLayoutManager
 * 基它全屏类型的视图包括: Dialog,LoadView,ErrorView,ExtraView(备用全屏视图)
 * 这几个全屏视图都通过 FullScreenLayoutManager 来实现，只需要给定它的类型参数指定它属于哪个视图类型;
 * <p>
 * 该布局处于Header布局之下,Center布局之上.
 * Created by 陈真 on 2017/6/12.
 * Copyright © 2015 深圳市小牛在线互联网信息咨询有限公司 股东公司：深圳市小牛互联网金融服务有限公司 版权所有 备案号：粤ICP备14079927号  ICP证粤B2-20160194
 * </p>
 */

public class TopLayoutManager extends AbstractLayoutManager {
    public TopLayoutManager(IContainerManager mContainerManager) {
        super(mContainerManager);
        this.mLayer = Layer.LAYER_BASIC_TOP_PART;
    }

    @Override
    public void onLayout(int left, int top, int right, int bottom) {

        /**如果不可见，则对该布局不进行处理;**/
        if (getVisibility() != View.VISIBLE) {
            return;
        }

        List<ILayoutManager<View, ILayoutManager>> managers = mContainerManager.layoutManagers();
        HeaderLayoutManager headerLayoutManager = null;
        int headerHeight = 0;
        /**获得头部布局对象**/
        for (ILayoutManager layoutManager : managers) {
            if (layoutManager instanceof HeaderLayoutManager) {
                headerLayoutManager = (HeaderLayoutManager) layoutManager;
                break;
            }
        }

        if (headerLayoutManager != null && headerLayoutManager.getVisibility() != View.GONE) {
            ViewGroup.MarginLayoutParams hLayoutParams = headerLayoutManager.getMarginLayoutParams();
            headerHeight = hLayoutParams.topMargin + hLayoutParams.bottomMargin + headerLayoutManager.getMeasuredHeight();
        }
        /**获得当前布局的Margin参数**/
        ViewGroup.MarginLayoutParams marginLayoutParams = getMarginLayoutParams();
        int leftMargin = marginLayoutParams.leftMargin;
        int rightMargin = marginLayoutParams.rightMargin;
        int topMargin = marginLayoutParams.topMargin;

        /**获得头部布局的高度**/
        int topPos = top + headerHeight + topMargin;
        mView.layout(left + leftMargin, topPos, right - rightMargin, topPos + mView.getMeasuredHeight());

    }

    /**
     * 根据给定的布局文件，在容器中添加一个视图，并返回当前这个视图对象;
     * 如果容器中已经存在该类型的视图，则不充许再次添加.
     *
     * @param containerLayout 当前界面的顶层容器
     * @param layout          需要添加的布局文件
     * @return 布局文件加载后的视图布局Manager对象
     */
    public static TopLayoutManager buildLayout(IContainerManager containerLayout, int layout) {

        TopLayoutManager topLayoutManager = new TopLayoutManager(containerLayout);

        if (containerLayout.contains(topLayoutManager)) {
            throw new UIFrameLayoutAlreadyExistException("Top视图已经添加到容器当中了，该视图不能重复添加.");
        } else {
            topLayoutManager.addLayout(layout);
            containerLayout.addLayoutManager(topLayoutManager);
        }
        return topLayoutManager;
    }

}
