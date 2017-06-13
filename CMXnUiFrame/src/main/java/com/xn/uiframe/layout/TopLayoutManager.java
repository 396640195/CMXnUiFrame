package com.xn.uiframe.layout;

import android.view.View;
import android.view.ViewGroup;

import com.xn.uiframe.PowerfulContainerLayout;

import java.util.List;

/**
 * <p>
 * 该布局处于Header布局之下,Center布局之上.
 * Created by 陈真 on 2017/6/12.
 * Copyright © 2015 深圳市小牛在线互联网信息咨询有限公司 股东公司：深圳市小牛互联网金融服务有限公司 版权所有 备案号：粤ICP备14079927号  ICP证粤B2-20160194
 * </p>
 */

public class TopLayoutManager extends AbstractLayoutManager {

    public TopLayoutManager(){
        this.mLayer = Layer.LAYER_BASIC_TOP_PART;
    }

    @Override
    public void onLayout(PowerfulContainerLayout containerLayout, int left, int top, int right, int bottom) {

        /**如果不可见，则对该布局不进行处理;**/
        if(mView == null || mView.getVisibility() == View.INVISIBLE || mView.getVisibility() == View.GONE){
            return;
        }

        List<ILayoutManager<View,ILayoutManager>> managers = containerLayout.layoutManagers();
        HeaderLayoutManager headerLayoutManager = null;
        int headerHeight = 0;
        /**获得头部布局对象**/
        for(ILayoutManager layoutManager : managers){
            if(layoutManager instanceof  HeaderLayoutManager){
                headerLayoutManager = (HeaderLayoutManager) layoutManager;
                break;
            }
        }

        if(headerLayoutManager != null){
            ViewGroup.MarginLayoutParams hLayoutParams = headerLayoutManager.getMarginLayoutParams();
            headerHeight = hLayoutParams.topMargin+hLayoutParams.bottomMargin + headerLayoutManager.getMeasuredHeight();
        }
        /**获得当前布局的Margin参数**/
        ViewGroup.MarginLayoutParams marginLayoutParams = getMarginLayoutParams();
        int leftMargin = marginLayoutParams.leftMargin;
        int rightMargin = marginLayoutParams.rightMargin;
        int topMargin = marginLayoutParams.topMargin;

        /**获得头部布局的高度**/
        int topPos = top+headerHeight+topMargin;
        mView.layout(left+leftMargin, topPos, right - rightMargin,topPos+mView.getMeasuredHeight());
    }
}
