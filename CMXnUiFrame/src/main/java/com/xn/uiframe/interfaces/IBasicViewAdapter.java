package com.xn.uiframe.interfaces;

import com.xn.uiframe.layout.BottomLayoutManager;
import com.xn.uiframe.layout.CenterLayoutManager;
import com.xn.uiframe.layout.FullScreenLayoutManager;
import com.xn.uiframe.layout.HeaderLayoutManager;
import com.xn.uiframe.layout.TopLayoutManager;

/**
 * Created by xn068074 on 2017/6/13.
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
}
