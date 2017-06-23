package com.xn.uiframe.layout;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;

import com.xn.uiframe.PowerfulContainerLayout;
import com.xn.uiframe.R;
import com.xn.uiframe.interfaces.IContainerManager;

/**
 * 对话框视图管理,可以添加多个对话框布局视图,显示和绘制的顺序按添加的顺序;
 *
 * <p>使用方法:</p>
 * <code>
             private View mDialog01, mDialog02;
             @Override
             public DialogLayoutManager addDialogView(IContainerManager container) {

                 DialogLayoutManager fsm = DialogLayoutManager.buildLayoutManager(container);

                 mDialog01 = fsm.addLayout(R.layout.layout_dialog_01);
                 mDialog01.findViewById(R.id.ok_button_of_dialog_01).setOnClickListener(this);
                 mDialog01.setVisibility(View.GONE);

                 mDialog02 = fsm.addLayout(R.layout.layout_dialog_02);
                 mDialog02.findViewById(R.id.ok_button_of_dialog_02).setOnClickListener(this);
                 mDialog02.setVisibility(View.GONE);

                 //如果只有一个视图,可以调用
                 //setElementViewVisible(ElementView.CenterView,false);
             return fsm;
 }
 * </code>
 * Created by xn068074 on 2017/6/22.
 * Copyright © 2015 深圳市小牛在线互联网信息咨询有限公司 股东公司：深圳市小牛互联网金融服务有限公司 版权所有 备案号：粤ICP备14079927号  ICP证粤B2-20160194
 */

public class DialogLayoutManager extends FullScreenLayoutManager {

    public DialogLayoutManager(IContainerManager mContainerManager, int mLayer) {
        super(mContainerManager, mLayer);
    }

    /**
     * 根据给定的布局文件，在容器中添加一个视图，并返回当前这个视图对象;
     * 如果容器中已经存在该类型的视图，则不充许再次添加.
     *
     * @param containerLayout 当前界面的顶层容器
     * @return 布局文件加载后的视图对象
     */
    public static DialogLayoutManager buildLayoutManager(IContainerManager containerLayout) {
        DialogLayoutManager fullScreenLayoutManager = new DialogLayoutManager(containerLayout,Layer.LAYER_DIALOG_SCREEN);
        return fullScreenLayoutManager;
    }

    @Override
    public View addLayout(@LayoutRes int layout) {
        PowerfulContainerLayout powerfulContainer = (PowerfulContainerLayout) mContainerManager;
        View  view = LayoutInflater.from(powerfulContainer.getContext()).inflate(layout, powerfulContainer, false);
        mViewCollections.add(view);
        return view;
    }
}
