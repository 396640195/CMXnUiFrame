package com.xn.uiframe.interfaces;


/**
 * <p>
 * 该接口主要定义界面通用的行为，包括:显示和隐藏LoadView，HeaderView,TopView,CenterView,DialogView,ErrorView等;
 * Created by xn068074 on 2017/6/13.
 * Copyright © 2015 深圳市小牛在线互联网信息咨询有限公司 股东公司：深圳市小牛互联网金融服务有限公司 版权所有 备案号：粤ICP备14079927号  ICP证粤B2-20160194
 * </p>
 */

public interface IViewCommonBehavior extends IHeaderViewBehavior {

    /**显示加载等待视图界面**/
    void showLoadingView();
    /**隐藏加载等待视图界面**/
    void hideLoadingView();

    /**显示对话框视图界面**/
    void showDialogView();
    /**隐藏对话框视图界面**/
    void hideDialogView();

    /**显示错误信息展示界面**/
    void showErrorView();
    /**隐藏错误信息展示界面**/
    void hideErrorView();

    /**显示中间内容区域视图**/
    void showCenterView();
    /**隐藏中间内容区域视图**/
    void hideCenterView();

    /**显示头部区域视图**/
    void showHeaderView();
    /**隐藏头部区域视图**/
    void hideHeaderView();

    /**显示头部之下的顶部区域视图**/
    void showTopView();
    /**隐藏头部之下的顶部区域视图**/
    void hideTopView();

    /**显示备用全屏视图**/
    void showExtraFullView();
    /**隐藏备用全屏视图**/
    void hideExtraFullView();

    /**设置容器颜色**/
    void setContainerBackgroundColor(int res);
    void setContainerBackgroundResource(int res);
}
