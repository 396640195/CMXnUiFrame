package com.xn.uiframe.interfaces;

import android.support.annotation.LayoutRes;
import android.view.View;

/**
 * 针对中间视图Center布局需要一个PullRefresh功能的列表时,定义一个头部和底部伴随视图的管理接口;
 * 可以随着ListView一起滚动的伴随视图管理的定义,可以利用这个特性来实现来实现一些特需求;
 * Created by 陈真 on 2017/6/16.
 */

public interface ICompanionViewManager {
    /**
     * 添加一个头部伴随视图布局，该视图可以跟随ListView一起上下滑动.
     * @param layout 视图布局资源id
     * @return  layout的视图对象
     */
    View addCompanionScrollableHeader(@LayoutRes int layout);
    /**
     * 添加一个底部伴随视图布局，该视图可以跟随ListView一起上下滑动.
     * @param layout 视图布局资源id
     * @return layout的视图对象
     */
    View addCompanionScrollableFooter(@LayoutRes int layout);
}
