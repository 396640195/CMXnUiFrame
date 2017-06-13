package com.xn.uiframe.layout;

import android.view.View;
import android.view.ViewGroup;

import com.xn.uiframe.PowerfulContainerLayout;
import com.xn.uiframe.exception.UIFrameLayoutAlreadyExistException;
import com.xn.uiframe.interfaces.IContainerManager;

/**
 * <p>
 * 该布局处于Center布局之下,该布局位置不依赖其它布局,和Header布局有类似独立性;
 * Created by 陈真 on 2017/6/13.
 * Copyright © 2015 深圳市小牛在线互联网信息咨询有限公司 股东公司：深圳市小牛互联网金融服务有限公司 版权所有 备案号：粤ICP备14079927号  ICP证粤B2-20160194
 * </p>
 */

public class BottomLayoutManager extends AbstractLayoutManager {
    public BottomLayoutManager() {
        this.mLayer = Layer.LAYER_BASIC_BOTTOM_PART;
    }

    @Override
    public void onLayout(PowerfulContainerLayout container, int left, int top, int right, int bottom) {
        /**如果不可见，则对该布局不进行处理;**/
        if (getVisibility() != View.VISIBLE) {
            return;
        }

        /**获得当前布局的Margin参数**/
        ViewGroup.MarginLayoutParams marginLayoutParams = getMarginLayoutParams();
        int leftMargin = marginLayoutParams.leftMargin;
        int rightMargin = marginLayoutParams.rightMargin;
        int bottomMargin = marginLayoutParams.bottomMargin;

        mView.layout(left + leftMargin, bottom - bottomMargin - mView.getMeasuredHeight(), right - rightMargin, bottom - bottomMargin);
    }

    /**
     * 根据给定的布局文件，在容器中添加一个视图，并返回当前这个视图对象;
     * 如果容器中已经存在该类型的视图，则不充许再次添加.
     *
     * @param containerLayout 当前界面的顶层容器
     * @param layout          需要添加的布局文件
     * @return 布局文件加载后的视图布局Manager对象
     */
    public static BottomLayoutManager TopLayoutManager(IContainerManager containerLayout, int layout) {
        BottomLayoutManager bottom = new BottomLayoutManager();
        if (containerLayout.contains(bottom)) {
            throw new UIFrameLayoutAlreadyExistException("Bottom视图已经添加到容器当中了，该视图不能重复添加.");
        } else {
            bottom.addLayout((PowerfulContainerLayout) containerLayout, layout);
            containerLayout.addLayoutManager(bottom);
        }
        return bottom;
    }
}
