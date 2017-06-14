package com.xn.uiframe.interfaces;

import com.xn.uiframe.layout.BottomLayoutManager;
import com.xn.uiframe.layout.CenterLayoutManager;
import com.xn.uiframe.layout.FullScreenLayoutManager;
import com.xn.uiframe.layout.HeaderLayoutManager;
import com.xn.uiframe.layout.TopLayoutManager;

/**
 * 界面视图适配器，定义了界面具有哪些元素，在具体界面中实现这些接口即可;
 * Created by 陈真 on 2017/6/13.
 * Copyright © 2015 深圳市小牛在线互联网信息咨询有限公司 股东公司：深圳市小牛互联网金融服务有限公司 版权所有 备案号：粤ICP备14079927号  ICP证粤B2-20160194
 */

public interface IBasicViewAdapter {
    /**
     * 在界面中添加一个头部视图,该视图处于界面最上面部分;
     * @param container 容器接口的实例对象
     * @return 返回头部布局管理对象 {@link HeaderLayoutManager}
     */
    HeaderLayoutManager addHeaderView(IContainerManager container);

    /**
     * 在界面中添加一个顶部视图,该视图处于界面的Header视图之下,Center视图之上;
     * @param container 容器接口的实例对象
     * @return 返回头部布局管理对象 {@link TopLayoutManager}
     */
    TopLayoutManager addTopView(IContainerManager container);

    /**
     * 在界面中添加一个底部视图,该视图处于界面的Center视图之下;
     * @param container 容器接口的实例对象
     * @return 返回头部布局管理对象 {@link BottomLayoutManager}
     */
    BottomLayoutManager addBottomView(IContainerManager container);
    /**
     * 在界面中添加一个中间视图,该视图处于界面的中间位置，主要用来展示界面的主要内容;
     * @param container 容器接口的实例对象
     * @return 返回头部布局管理对象 {@link CenterLayoutManager}
     */
    CenterLayoutManager addCenterView(IContainerManager container);
    /**
     * 在界面中添加一个全屏视图,该视图处于界面的最上层;
     * @param container 容器接口的实例对象
     * @return 返回头部布局管理对象 {@link FullScreenLayoutManager}
     */
    FullScreenLayoutManager addDialogView(IContainerManager container);
    /**
     * 在界面中添加一个全屏视图,该视图处于Dialog视图之下，LoadView之上;
     * @param container 容器接口的实例对象
     * @return 返回头部布局管理对象 {@link FullScreenLayoutManager}
     */
    FullScreenLayoutManager addErrorView(IContainerManager container);
    /**
     * 在界面中添加一个全屏视图,该视图处于基本视图之上，ErrorView视图之下;
     * @param container 容器接口的实例对象
     * @return 返回头部布局管理对象 {@link FullScreenLayoutManager}
     */
    FullScreenLayoutManager addLoadingView(IContainerManager container);
    /**
     * 在界面中添加一个全屏视图,该视图处于Dialog视图之下;
     * @param container 容器接口的实例对象
     * @return 返回头部布局管理对象 {@link FullScreenLayoutManager}
     */
    FullScreenLayoutManager addExtraTopView(IContainerManager container);

    /**
     * 是否要使用EventBus
     * @return
     */
    boolean isNeedEventBus();

    /**
     * 是否需要使用下拉刷新的功能
     * @return
     */
    boolean isNeedPullRefresh();
}
