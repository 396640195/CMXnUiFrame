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
     * 设置加载等待视图界面的显示和隐藏
     *
     * @param visible true:显示-可见状态,false:隐藏-不可见状态
     */
    void setLoadViewVisible(boolean visible);

    /**
     * 加载等待视图界面是否为可见状态
     *
     * @return true:显示-可见状态,false:隐藏-不可见状态
     */
    boolean isLoadViewVisible();

    /**
     * 设置对话框层视图是否可以显示
     *
     * @param visible true:显示-可见状态,false:隐藏-不可见状态
     */
    void setDialogViewVisible(boolean visible);

    /**
     * 对话框视图界面是否处于可见状态
     *
     * @return true:显示-可见状态,false:隐藏-不可见状态
     **/
    boolean isDialogViewVisible();

    /**
     * 设置错误异常界面是否显示
     *
     * @param visible true:显示-可见状态,false:隐藏-不可见状态
     */
    void setErrorViewVisible(boolean visible);

    /**
     * 错误信息展示视图是否处于可见状态
     *
     * @return true:显示-可见状态,false:隐藏-不可见状态
     **/
    boolean isErrorViewVisible();

    /**
     * 设置中间内容区域视图是否为可见状态
     *
     * @param visible true:显示-可见状态,false:隐藏-不可见状态
     **/
    void setCenterViewVisible(boolean visible);

    /**
     * 中间内容区域视图是否处于可见状态
     *
     * @return true:显示-可见状态,false:隐藏-不可见状态
     **/
    boolean isCenterViewVisible();

    /**
     * 设置头部视图是否为可见状态
     *
     * @param visible true:显示-可见状态,false:隐藏-不可见状态
     **/
    void setHeaderViewVisible(boolean visible);

    /**
     * 隐藏头部区域视图
     *
     * @return true:显示-可见状态,false:隐藏-不可见状态
     **/
    boolean isHeaderViewVisible();

    /**
     * 设置头部之下与中间区域之间的顶部视图是否可见
     *
     * @param visible true:显示-可见状态,false:隐藏-不可见状态
     **/
    void setTopViewVisible(boolean visible);

    /**
     * 顶部区域是否处于可见状态
     *
     * @return true:显示-可见状态,false:隐藏-不可见状态
     **/
    boolean isTopViewVisible();

    /**
     * 设置备用全屏视图是否为可见状态
     *
     * @param visible true:显示-可见状态,false:隐藏-不可见状态
     **/
    void setExtraFullViewVisible(boolean visible);

    /**
     * 备用全屏视图是否处于可见状态
     *
     * @return true:显示-可见状态,false:隐藏-不可见状态
     **/
    boolean isExtraFullViewVisible();

    /**
     * 设置底部视图是否为可见状态
     *
     * @param visible true:显示-可见状态,false:隐藏-不可见状态
     **/
    void setBottomViewVisible(boolean visible);

    /**
     * 底部视图是否处于可见状态
     *
     * @return true:显示-可见状态,false:隐藏-不可见状态
     **/
    boolean isBottomViewVisible();

    /**
     * 设置容器颜色
     *
     * @param res 颜色资源ID
     **/
    void setContainerBackgroundColor(@ColorInt int res);

    /**
     * 设置容器背景图片
     * @param res 图片资源ID
     */
    void setContainerBackgroundResource(@DrawableRes int res);
}
