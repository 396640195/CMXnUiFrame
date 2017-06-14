package com.xn.uiframe.layout;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xn.uiframe.PowerfulContainerLayout;
import com.xn.uiframe.R;
import com.xn.uiframe.exception.UIFrameLayoutAlreadyExistException;
import com.xn.uiframe.interfaces.IContainerManager;
import com.xn.uiframe.interfaces.IHeaderViewBehavior;

/**
 * <p>
 * 该布局主要是定义一个层级，负责展示一个页面的头部，该头部可能包括返回图标，文件等信息. 具体内容取决于addLayout传进来的res布局文件.
 * Created by 陈真 on 2017/6/12.
 * Copyright © 2015 深圳市小牛在线互联网信息咨询有限公司 股东公司：深圳市小牛互联网金融服务有限公司 版权所有 备案号：粤ICP备14079927号  ICP证粤B2-20160194
 * </p>
 */

public class HeaderLayoutManager extends AbstractLayoutManager implements IHeaderViewBehavior {

    public HeaderLayoutManager() {
        this.mLayer = Layer.LAYER_BASIC_HEADER_PART;
    }

    @Override
    public void onLayout(PowerfulContainerLayout container, int left, int top, int right, int bottom) {

        /**如果不可见，则对该布局不进行处理;**/
        if (mView == null || getVisibility() != View.VISIBLE) {
            return;
        }

        //获得当前布局的Margin参数
        /**获得当前布局的Margin参数**/
        ViewGroup.MarginLayoutParams marginLayoutParams = getMarginLayoutParams();
        int leftMargin = marginLayoutParams.leftMargin;
        int rightMargin = marginLayoutParams.rightMargin;
        int topMargin = marginLayoutParams.topMargin;
        mView.layout(left + leftMargin, top + topMargin, right - rightMargin, top + topMargin + mView.getMeasuredHeight());
    }

    /**
     * 根据给定的布局文件，在容器中添加一个视图，并返回当前这个视图对象;
     * 如果容器中已经存在该类型的视图，则不充许再次添加.
     *
     * @param containerLayout 当前界面的顶层容器
     * @param layout          需要添加的布局文件
     * @return 布局文件加载后的视图布局Manager对象
     */
    public static HeaderLayoutManager buildLayout(IContainerManager containerLayout, int layout) {
        HeaderLayoutManager header = new HeaderLayoutManager();
        if (containerLayout.contains(header)) {
            throw new UIFrameLayoutAlreadyExistException("Header视图已经添加到容器当中了，该视图不能重复添加.");
        } else {
            header.addLayout((PowerfulContainerLayout) containerLayout, layout);
            containerLayout.addLayoutManager(header);
        }
        return header;
    }

    @Override
    public TextView setHeaderLeftText(int resource) {
        TextView textView = (TextView) mView.findViewById(R.id.ui_frame_header_left);
        textView.setText(resource);
        return textView;
    }

    @Override
    public TextView setHeaderLeftImage(int resource) {
        TextView textView = (TextView) mView.findViewById(R.id.ui_frame_header_left);
        Drawable drawable = textView.getContext().getResources().getDrawable(resource);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(drawable, null, null, null);
        return textView;
    }

    @Override
    public TextView setHeaderCenterText(int resource) {
        TextView textView = (TextView) mView.findViewById(R.id.ui_frame_header_center);
        textView.setText(resource);
        return textView;
    }

    @Override
    public TextView setHeaderRightText(int resource) {
        TextView textView = (TextView) mView.findViewById(R.id.ui_frame_header_right);
        textView.setText(resource);
        return textView;
    }

    @Override
    public TextView setHeaderRightImage(int resource) {
        TextView textView = (TextView) mView.findViewById(R.id.ui_frame_header_right);
        Drawable drawable = textView.getContext().getResources().getDrawable(resource);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(drawable, null, null, null);
        return textView;
    }
}
