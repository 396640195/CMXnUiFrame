package com.xn.uiframe.interfaces;

import com.xn.uiframe.layout.BottomLayoutManager;
import com.xn.uiframe.layout.CenterLayoutManager;
import com.xn.uiframe.layout.CenterMaskLayoutManager;
import com.xn.uiframe.layout.DialogLayoutManager;
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
     * @return 返回Top布局管理对象 {@link TopLayoutManager}
     */
    TopLayoutManager addTopView(IContainerManager container);

    /**
     * 在界面中添加一个底部视图,该视图处于界面的Center视图之下;
     * @param container 容器接口的实例对象
     * @return 返回Bottom布局管理对象 {@link BottomLayoutManager}
     */
    BottomLayoutManager addBottomView(IContainerManager container);
    /**
     * 在界面中添加一个中间视图,该视图处于界面的中间位置，主要用来展示界面的主要内容;
     * @param container 容器接口的实例对象
     * @return 返回Center布局管理对象 {@link CenterLayoutManager}
     */
    CenterLayoutManager addCenterView(IContainerManager container);

    /**
     * 在中间视图之上添加一个覆盖层,用来实现某些特殊需求;
     * @param container 容器接口的实例对象
     * @return 返回Center Mask布局管理对象 {@link CenterLayoutManager}
     */
    CenterMaskLayoutManager addCenterMaskView(IContainerManager container);
    /**
     * 在界面中添加一个全屏视图,该视图处于界面的最上层;
     * @param container 容器接口的实例对象
     * @return 返回DialogLayoutManager布局管理对象 {@link DialogLayoutManager}
     */
    DialogLayoutManager addDialogView(IContainerManager container);
    /**
     * 在界面中添加一个全屏视图,该视图处于Dialog视图之下;
     * @param container 容器接口的实例对象
     * @return 返回FullScreenLayoutManager布局管理对象 {@link FullScreenLayoutManager}
     */
    FullScreenLayoutManager addExtraFullScreenView(IContainerManager container);

    /**
     * 给中间可滚动的列表添加头部伴随视图
     * @param container
     * @return 返回添加的头部伴随视图对象
     */
    void addCompanionScrollableHeader(CenterLayoutManager container);
    /**
     * 给中间可滚动的列表添加底部伴随视图
     * @param container
     * @return 返回添加的底部伴随视图对象
     */
    void addCompanionScrollableFooter(CenterLayoutManager container);

    /**
     * 当伴随视图添加完毕后，提供一个回调接口，让ListView设置适配器;
     * @param container
     */
    void onCompanionViewAddFinished(CenterLayoutManager container);

    /**
     * 当所有视图构造完成之后调用
     */
    void onAllViewConstructed();
    /**
     * 是否要使用EventBus
     * @return
     */
    boolean isNeedEventBus();

}
