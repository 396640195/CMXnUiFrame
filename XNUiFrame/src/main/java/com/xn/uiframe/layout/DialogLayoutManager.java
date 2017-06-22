package com.xn.uiframe.layout;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;

import com.xn.uiframe.PowerfulContainerLayout;
import com.xn.uiframe.R;
import com.xn.uiframe.interfaces.IContainerManager;

/**
 * Created by xn068074 on 2017/6/22.
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
