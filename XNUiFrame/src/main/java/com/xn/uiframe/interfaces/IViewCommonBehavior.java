package com.xn.uiframe.interfaces;


import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;

import com.xn.uiframe.ElementView;
import com.xn.uiframe.refreshlayout.OnRefreshListener;

/**
 * <p>
 * 该接口主要定义界面通用的行为，包括:显示和隐藏LoadView，HeaderView,TopView,CenterView,DialogView,ErrorView等;
 * Created by xn068074 on 2017/6/13.
 * Copyright © 2015 深圳市小牛在线互联网信息咨询有限公司 股东公司：深圳市小牛互联网金融服务有限公司 版权所有 备案号：粤ICP备14079927号  ICP证粤B2-20160194
 * </p>
 */

public interface IViewCommonBehavior extends
        IHeaderViewBehavior,
        IAnimateBehavior,
        IPullRefreshBehavior{

    /**
     * 设置界面视图元素是否可见
     *
     * @param elementCategory 界面视图类型
     * @param visible         true:可见  false:不可见
     */
    void setElementViewVisible(ElementView elementCategory, boolean visible);

    /**
     * 返回该元素的可见状态
     *
     * @param elementCategory 界面视图类型
     * @return true:可见  false:不可见
     */
    boolean isElementViewVisible(ElementView elementCategory);

    /**
     * 设置容器颜色
     *
     * @param res 颜色资源ID
     **/
    void setContainerBackgroundColor(@ColorRes int res);

    /**
     * 设置容器背景图片
     *
     * @param res 图片资源ID
     */
    void setContainerBackgroundResource(@DrawableRes int res);

    /**
     * 设置下拉刷新回调接口
     * @param listener
     */
    void setOnRefreshListener(OnRefreshListener listener);
}
