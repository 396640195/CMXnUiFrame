package com.xn.uiframe.layout;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xn.uiframe.PowerfulContainerLayout;

/**
 * <p>
 * 抽象布局管理类: 主要实现各种布局管理的公性特征，并定义布局的层次关系类型;
 * Created by 陈真 on 2017/6/12.
 * Copyright © 2015 深圳市小牛在线互联网信息咨询有限公司 股东公司：深圳市小牛互联网金融服务有限公司 版权所有 备案号：粤ICP备14079927号  ICP证粤B2-20160194
 * </p>
 */

public abstract class AbstractLayoutManager implements ILayoutManager<View, ILayoutManager> {

    /**
     * 默认层级为对话框全屏模式
     **/
    protected int mLayer = Layer.LAYER_DIALOG_SCREEN;

    /**
     * 当调用了{@link AbstractLayoutManager#addLayout(PowerfulContainerLayout, int)}
     * 方法后，会保存当前视图对象;
     */
    protected View mView;

    /**
     * 定义UI框架中视图有哪些层级，决定视图在容器中绘制的先后顺序.
     */
    public static class Layer {

        /**
         *定义Header布局层级，优先绘制Header
         **/
        public static final int LAYER_BASIC_HEADER_PART = 0x10001;

        /**
         *定义Bottom布局层级，绘制顺序和Header相同
         **/
        public static final int LAYER_BASIC_BOTTOM_PART = LAYER_BASIC_HEADER_PART;

        /**
         *定义Top布局层级，绘制顺序在Header和Bottom之后;
         **/
        public static final int LAYER_BASIC_TOP_PART = 0x10002;

        /**
         * Center 布局的绘制依懒于Header,Top,Bottom的宽高占比数据，
         * 所以Center的层级定义要比 Header,Top,Bottom高;
         */
        public static final int LYAER_BASIC_CENTER_PART = 0x10003;
        /**
         * 处于该层级的主要是用于实现进度加载，这种异步等待的全屏界面. 它处于
         *  basic part 层级之上.
         **/
        public static final int LAYER_WAIT_SCREEN = 0x10004;

        /**
         * 该层级主要用于来展示异常信息，它处于
         * {@link AbstractLayoutManager.Layer#LAYER_WAIT_SCREEN}层级之上.
         ***/
        public static final int LAYER_ERROR_SCREEN = 0x10005;

        /**
         * 该层级主要用来实现对话框的功能,完全替代话框，它的层级处于
         * {@link AbstractLayoutManager.Layer#LAYER_ERROR_SCREEN}层级之上.
         * 处于 {@link AbstractLayoutManager.Layer#LAYER_SCREEN_TOAST} 之下.
         **/
        public static final int LAYER_DIALOG_SCREEN = 0x10006;

        /**
         * 该层级处于UI的最顶层.
         **/
        public static final int LAYER_SCREEN_TOAST = 0x10007;
    }

    @Override
    public int compareTo(@NonNull ILayoutManager o) {
        if (o instanceof AbstractLayoutManager) {
            AbstractLayoutManager alm = (AbstractLayoutManager) o;
            return alm.mLayer - this.mLayer;
        }
        return 0;
    }

    @Override
    public void onLayout(PowerfulContainerLayout container, int left, int top, int right, int bottom) {
        /**
         * 提供默认的全屏布局实现,且只有在该View可见的时候，才绘制该视图;
         * **/
        if (mView == null || mView.getVisibility() != View.VISIBLE) {
            return;
        }

        //获得当前布局的Margin参数
        ViewGroup.MarginLayoutParams marginLayoutParams = getMarginLayoutParams();
        int leftMargin = marginLayoutParams.leftMargin;
        int rightMargin = marginLayoutParams.rightMargin;
        int topMargin = marginLayoutParams.topMargin;
        int bottomMarin = marginLayoutParams.bottomMargin;

        mView.layout(left+leftMargin, top+topMargin, right - rightMargin, bottom - bottomMarin);
    }

    @Override
    public void onMeasure(PowerfulContainerLayout container, int widthMeasureSpec, int heightMeasureSpec) {

        /**
         * 当View处于{@link View.VISIBLE} or {@link View.INVISIBLE}两种情况下，都要测量它的宽高;
         */
        if (mView == null || mView.getVisibility() == View.GONE) {
            return;
        }

        //获得当前容器布局宽和高
        int containerWidth = View.MeasureSpec.getSize(widthMeasureSpec);
        int containerHeight = View.MeasureSpec.getSize(heightMeasureSpec);

        //获得当前布局的Margin参数
        ViewGroup.MarginLayoutParams marginLayoutParams = getMarginLayoutParams();
        int leftMargin = marginLayoutParams.leftMargin;
        int rightMargin = marginLayoutParams.rightMargin;
        int topMargin = marginLayoutParams.topMargin;
        int bottomMarin = marginLayoutParams.bottomMargin;

        //计算当前布局的测量基准数据
        int basicWidth = containerWidth - leftMargin - rightMargin;
        int basicHeight = containerHeight - topMargin - bottomMarin;

        int basicWidthSpec = View.MeasureSpec.makeMeasureSpec(basicWidth, View.MeasureSpec.EXACTLY);
        int basicHeightSpec = View.MeasureSpec.makeMeasureSpec(basicHeight, View.MeasureSpec.EXACTLY);

        container.measureChild(mView, basicWidthSpec, basicHeightSpec);

    }

    @Override
    public View addLayout(PowerfulContainerLayout container, int layout) {
        View view = LayoutInflater.from(container.getContext()).inflate(layout, container, false);
        return view;
    }

    @Override
    public int getMeasuredHeight() {
        return mView == null ? 0 : mView.getMeasuredHeight();
    }

    @Override
    public ViewGroup.MarginLayoutParams getMarginLayoutParams() {
        return mView == null ? null : (ViewGroup.MarginLayoutParams) mView.getLayoutParams();
    }
}
