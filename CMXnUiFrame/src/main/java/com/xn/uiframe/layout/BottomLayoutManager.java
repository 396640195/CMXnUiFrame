package com.xn.uiframe.layout;

import android.view.View;
import android.view.ViewGroup;

import com.xn.uiframe.PowerfulContainerLayout;

/**
 * <p>
 * 该布局处于Center布局之下,该布局位置不依赖其它布局,和Header布局有类似独立性;
 * Created by 陈真 on 2017/6/13.
 * Copyright © 2015 深圳市小牛在线互联网信息咨询有限公司 股东公司：深圳市小牛互联网金融服务有限公司 版权所有 备案号：粤ICP备14079927号  ICP证粤B2-20160194
 * </p>
 */

public class BottomLayoutManager extends AbstractLayoutManager {
    public BottomLayoutManager(){
        this.mLayer = Layer.LAYER_BASIC_BOTTOM_PART;
    }
    @Override
    public void onLayout(PowerfulContainerLayout container, int left, int top, int right, int bottom) {
        /**
         * 提供默认的全屏布局实现,且只有在该View可见的时候，才绘制该视图;
         * **/
        if (mView == null || mView.getVisibility() != View.VISIBLE) {
            return;
        }

        //获得当前布局的Margin参数
        ViewGroup.MarginLayoutParams marginLayoutParams = getMarginLayoutParams();
        int leftMargin = marginLayoutParams.leftMargin;
        int rightMargin = marginLayoutParams.rightMargin;
        int bottomMargin = marginLayoutParams.bottomMargin;

        mView.layout(left+leftMargin, bottom - bottomMargin - mView.getMeasuredHeight(), right - rightMargin, bottom - bottomMargin);
    }
}
