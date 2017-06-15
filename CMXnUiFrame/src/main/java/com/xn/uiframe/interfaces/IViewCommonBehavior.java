package com.xn.uiframe.interfaces;


import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;

/**
 * <p>
 * 该接口主要定义界面通用的行为，包括:显示和隐藏LoadView，HeaderView,TopView,CenterView,DialogView,ErrorView等;
 * Created by xn068074 on 2017/6/13.
 * Copyright © 2015 深圳市小牛在线互联网信息咨询有限公司 股东公司：深圳市小牛互联网金融服务有限公司 版权所有 备案号：粤ICP备14079927号  ICP证粤B2-20160194
 * </p>
 */

public interface IViewCommonBehavior extends IHeaderViewBehavior {

    /**
     * 设置界面视图元素是否可见
     *
     * @param elementCategory 界面视图类型
     * @param visible         true:可见  false:不可见
     */
    void setElementViewVisible(ViewElementCategory elementCategory, boolean visible);

    /**
     * 返回该元素的可见状态
     *
     * @param elementCategory 界面视图类型
     * @return true:可见  false:不可见
     */
    boolean isElementViewVisible(ViewElementCategory elementCategory);

    /**
     * 设置容器颜色
     *
     * @param res 颜色资源ID
     **/
    void setContainerBackgroundColor(@ColorInt int res);

    /**
     * 设置容器背景图片
     *
     * @param res 图片资源ID
     */
    void setContainerBackgroundResource(@DrawableRes int res);

    /**
     * 针对该视图进行Y轴动画
     *
     * @param elementCategory 界面视图元素类型
     * @param seconds
     */
    void animateY(ViewElementCategory elementCategory, long seconds);

    /**
     * 针对该视图进行X轴动画
     *
     * @param elementCategory 界面视图元素类型
     * @param seconds
     */
    void animateX(ViewElementCategory elementCategory, long seconds);

    /**
     * 针对该视图进行XY轴动画
     *
     * @param elementCategory 界面视图元素类型
     * @param seconds
     */
    void animateXY(ViewElementCategory elementCategory, long seconds);
}
