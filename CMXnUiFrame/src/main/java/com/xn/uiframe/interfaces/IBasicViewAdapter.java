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
    HeaderLayoutManager addHeaderView(IContainerManager container);

    TopLayoutManager addTopView(IContainerManager container);

    BottomLayoutManager addBottomView(IContainerManager container);

    CenterLayoutManager addCenterView(IContainerManager container);

    FullScreenLayoutManager addDialogView(IContainerManager container);

    FullScreenLayoutManager addErrorView(IContainerManager container);

    FullScreenLayoutManager addLoadingView(IContainerManager container);

    FullScreenLayoutManager addExtraTopView(IContainerManager container);

    boolean isNeedEventBus();

    boolean isNeedPullRefresh();
}
