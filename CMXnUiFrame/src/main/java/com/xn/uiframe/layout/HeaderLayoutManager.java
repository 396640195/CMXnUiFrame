package com.xn.uiframe.layout;

import android.view.View;
import android.view.ViewGroup;

import com.xn.uiframe.PowerfulContainerLayout;

/**
 * <p>
 * 该布局主要是定义一个层级，负责展示一个页面的头部，该头部可能包括返回图标，文件等信息. 具体内容取决于addLayout传进来的res布局文件.
 * Created by 陈真 on 2017/6/12.
 * Copyright © 2015 深圳市小牛在线互联网信息咨询有限公司 股东公司：深圳市小牛互联网金融服务有限公司 版权所有 备案号：粤ICP备14079927号  ICP证粤B2-20160194
 * </p>
 */

public class HeaderLayoutManager extends AbstractLayoutManager {

    public HeaderLayoutManager(){
        this.mLayer = Layer.LAYER_BASIC_HEADER_PART;
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
        int topMargin = marginLayoutParams.topMargin;
        mView.layout(left+leftMargin, top+topMargin, right - rightMargin, top + topMargin + mView.getMeasuredHeight());
    }

}
