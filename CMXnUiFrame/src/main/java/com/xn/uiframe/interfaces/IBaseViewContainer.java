package com.xn.uiframe.interfaces;

import android.view.View;

/**
 * <p>
 * 容器接口定义，主要定义了一个创建视图的接口，
 * {@link com.xn.uiframe.activity.UIFrameBasicActivity},
 * {@link com.xn.uiframe.activity.UIFrameBasicFragment}和
 * {@link IBaseViewContainer}同时继承了{@link IViewCommonBehavior}接口，
 * 使得该容器IBaseViewContainer和UIFrameBasicActivity同时具有操控视图的能力; 统一了操作视图的接口行为，最终UI中操作视图都转接给容器来操作;
 * Created by 陈真 on 2017/6/13.
 * Copyright © 2015 深圳市小牛在线互联网信息咨询有限公司 股东公司：深圳市小牛互联网金融服务有限公司 版权所有 备案号：粤ICP备14079927号  ICP证粤B2-20160194
 * </p>
 */

public interface IBaseViewContainer extends IViewCommonBehavior{
    /**
     * 定义创建视图的方法
     * @return 返回创建的视图对象
     */
    View onCreateView();
}
