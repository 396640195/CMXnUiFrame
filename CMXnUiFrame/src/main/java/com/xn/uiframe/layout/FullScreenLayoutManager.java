package com.xn.uiframe.layout;

import android.view.View;
import android.view.ViewGroup;

import com.xn.uiframe.PowerfulContainerLayout;
import com.xn.uiframe.exception.UIFrameIllegalArgumentException;
import com.xn.uiframe.exception.UIFrameLayoutAlreadyExistException;
import com.xn.uiframe.interfaces.IContainerManager;

/**
 * <p>
 * 该布局主要用来实现全屏遮罩视图，包括对话框,加载等待，异常展示这类全屏遮罩视图.
 * Created by 陈真 on 2017/6/13.
 * Copyright © 2015 深圳市小牛在线互联网信息咨询有限公司 股东公司：深圳市小牛互联网金融服务有限公司 版权所有 备案号：粤ICP备14079927号  ICP证粤B2-20160194
 * </p>
 */

public class FullScreenLayoutManager extends AbstractLayoutManager {

    public FullScreenLayoutManager(int mLayer) {
        super(mLayer);
    }
    @Override
    public void onLayout(PowerfulContainerLayout container, int left, int top, int right, int bottom) {
        /**
         * 提供默认的全屏布局实现,且只有在该View可见的时候，才绘制该视图;
         * **/
        if (getVisibility() != View.VISIBLE) {
            return;
        }

        //获得当前布局的Margin参数
        ViewGroup.MarginLayoutParams marginLayoutParams = getMarginLayoutParams();
        int leftMargin = marginLayoutParams.leftMargin;
        int rightMargin = marginLayoutParams.rightMargin;
        int topMargin = marginLayoutParams.topMargin;
        int bottomMarin = marginLayoutParams.bottomMargin;

        mView.layout(left + leftMargin, top + topMargin, right - rightMargin, bottom - bottomMarin);
    }

    /**
     * 根据给定的布局文件，在容器中添加一个视图，并返回当前这个视图对象;
     * 如果容器中已经存在该类型的视图，则不充许再次添加.
     *
     * @param containerLayout 当前界面的顶层容器
     * @param layout          需要添加的布局文件
     * @param layer           该视图在界面中的层次,
     *                        请参照{@link com.xn.uiframe.layout.AbstractLayoutManager.Layer#LAYER_DIALOG_SCREEN}
     *                        {@link com.xn.uiframe.layout.AbstractLayoutManager.Layer#LAYER_WAIT_SCREEN}
     *                        {@link com.xn.uiframe.layout.AbstractLayoutManager.Layer#LAYER_ERROR_SCREEN}
     *                        {@link com.xn.uiframe.layout.AbstractLayoutManager.Layer#LAYER_FULL_SCREEN_EXTRA}
     * @return 布局文件加载后的视图对象
     */
    public static FullScreenLayoutManager buildView(IContainerManager containerLayout, int layout, int layer) {

        if(layer < Layer.LAYER_WAIT_SCREEN){
            throw new UIFrameIllegalArgumentException("layer参数类型错误，该方法只能添加全屏类型的视图.请查看该接口的参数说明.");
        }
        FullScreenLayoutManager fullScreenLayoutManager = new FullScreenLayoutManager(layer);
        if (containerLayout.contains(fullScreenLayoutManager)) {
            throw new UIFrameLayoutAlreadyExistException("Top视图已经添加到容器当中了，该视图不能重复添加.");
        } else {
            fullScreenLayoutManager.addLayout((PowerfulContainerLayout) containerLayout, layout);
            containerLayout.addLayoutManager(fullScreenLayoutManager);
        }
        return fullScreenLayoutManager;
    }
}
