package com.xn.uiframe.layout;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xn.uiframe.PowerfulContainerLayout;
import com.xn.uiframe.animation.Easing;
import com.xn.uiframe.animation.UIFrameViewAnimator;
import com.xn.uiframe.interfaces.IContainerManager;
import com.xn.uiframe.interfaces.ILayoutManager;

/**
 * <p>
 * 抽象布局管理类: 主要实现各种布局管理的公性特征，并定义布局的层次关系类型;
 * Created by 陈真 on 2017/6/12.
 * Copyright © 2015 深圳市小牛在线互联网信息咨询有限公司 股东公司：深圳市小牛互联网金融服务有限公司 版权所有 备案号：粤ICP备14079927号  ICP证粤B2-20160194
 * </p>
 */

public abstract class AbstractLayoutManager implements ILayoutManager<View, ILayoutManager> {
    protected IContainerManager mContainerManager;
    private ViewXAnimateListener mViewXAnimateListener;
    private ViewYAnimateListener mViewYAnimateListener;

    public AbstractLayoutManager(IContainerManager mContainerManager) {
        this.initAnimator();
        this.mContainerManager = mContainerManager;
    }


    public AbstractLayoutManager(IContainerManager mContainerManager, int mLayer) {
        this.initAnimator();
        this.mLayer = mLayer;
        this.mContainerManager = mContainerManager;
    }

    private void initAnimator() {
        this.mViewXAnimateListener = new ViewXAnimateListener();
        this.mViewYAnimateListener = new ViewYAnimateListener();
    }

    /**
     * 默认层级为对话框全屏模式
     **/
    protected int mLayer = Layer.LAYER_DIALOG_SCREEN;

    /**
     * 当调用了{@link AbstractLayoutManager#addLayout(int)}
     * 方法后，会保存当前视图对象;
     */
    protected View mView;

    /**
     * 定义UI框架中视图有哪些层级，决定视图在容器中绘制的先后顺序.
     */
    public static class Layer {

        /**
         * 定义Header布局层级，优先绘制Header
         **/
        public static final int LAYER_BASIC_HEADER_PART = 0x10001;

        /**
         * 定义Top布局层级，绘制顺序在Header之后;
         **/
        public static final int LAYER_BASIC_TOP_PART = 0x10002;
        /**
         * 定义Bottom布局层级，绘制顺序在Center之后
         **/
        public static final int LAYER_BASIC_BOTTOM_PART = 0x10003;

        /**
         * Center 布局的绘制依懒于Header,Top,Bottom的宽高占比数据，
         * 所以Center的层级定义要比 Header,Top,Bottom高;
         */
        public static final int LAYER_BASIC_CENTER_PART = 0x10004;
        /**
         * 处于该层级的主要是用于实现进度加载，这种异步等待的全屏界面. 它处于
         * basic part 层级之上.
         **/
        public static final int LAYER_LOAD_SCREEN = 0x10005;

        /**
         * 该层级主要用于来展示异常信息，它处于
         * {@link AbstractLayoutManager.Layer#LAYER_LOAD_SCREEN}层级之上.
         ***/
        public static final int LAYER_ERROR_SCREEN = 0x10006;

        /**
         * 该层级用来备用特殊情况，如果前两层不足以满足需求，可以根据需求使用这一层级;
         * 它处于{@link AbstractLayoutManager.Layer#LAYER_ERROR_SCREEN}层级之上.
         **/
        public static final int LAYER_FULL_SCREEN_EXTRA = 0x10007;

        /**
         * 处于UI的最顶层.该层级主要用来实现对话框的功能,完全替代话框，它的层级处于
         * 它处于{@link AbstractLayoutManager.Layer#LAYER_FULL_SCREEN_EXTRA}层级之上.
         **/
        public static final int LAYER_DIALOG_SCREEN = 0x10008;

    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        /**
         * 当View处于{@link View.VISIBLE} 才测量它的宽高;
         */
        if (getVisibility() != View.VISIBLE) {
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
        mContainerManager.measureChild(mView, basicWidthSpec, basicHeightSpec);

    }

    @Override
    public int compareTo(@NonNull ILayoutManager o) {
        if (o instanceof AbstractLayoutManager) {
            AbstractLayoutManager alm = (AbstractLayoutManager) o;
            return -alm.mLayer + this.mLayer;
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AbstractLayoutManager) {
            AbstractLayoutManager alm = (AbstractLayoutManager) obj;
            return alm.getLayer() == this.getLayer();
        }
        return false;
    }

    @Override
    public View addLayout(int layout) {
        PowerfulContainerLayout powerfulContainer = (PowerfulContainerLayout) mContainerManager;
        mView = LayoutInflater.from(powerfulContainer.getContext()).inflate(layout, powerfulContainer, false);
        return mView;
    }

    @Override
    public int getMeasuredHeight() {
        return mView == null ? 0 : mView.getMeasuredHeight();
    }

    @Override
    public int getMeasuredWidth() {
        return mView == null ? 0 : mView.getMeasuredWidth();
    }

    @Override
    public ViewGroup.MarginLayoutParams getMarginLayoutParams() {
        return mView == null ? null : (ViewGroup.MarginLayoutParams) mView.getLayoutParams();
    }

    @Override
    public int getLayer() {
        return mLayer;
    }

    @Override
    public void setVisible(int visible) {
        if (mView != null) {
            mView.setVisibility(visible);
            mContainerManager.requestLayout();
        }
    }

    @Override
    public int getVisibility() {
        return mView == null ? View.GONE : mView.getVisibility();
    }

    @Override
    public View getContentView() {
        return mView;
    }

    @Override
    public void animateY(long duration) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, mView.getMeasuredHeight());
        valueAnimator.addUpdateListener(mViewYAnimateListener);
        valueAnimator.setDuration(duration);
        valueAnimator.start();
    }

    @Override
    public void animateX(long duration) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, mView.getMeasuredWidth());
        valueAnimator.addUpdateListener(mViewXAnimateListener);
        valueAnimator.setDuration(duration);
        valueAnimator.start();
    }

    @Override
    public void animateXY(long xDuration, long yDuration) {
        this.animateX(xDuration);
        this.animateY(yDuration);
    }

    @Override
    public void animateY(Easing.EasingAnimation easing, long duration) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, mView.getMeasuredHeight());
        valueAnimator.addUpdateListener(mViewYAnimateListener);
        valueAnimator.setInterpolator(Easing.getEasingFunctionFromOption(easing));
        valueAnimator.setDuration(duration);
        valueAnimator.start();
    }

    @Override
    public void animateX(Easing.EasingAnimation easing, long duration) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, mView.getMeasuredWidth());
        valueAnimator.addUpdateListener(mViewXAnimateListener);
        //valueAnimator.addListener();
        valueAnimator.setInterpolator(Easing.getEasingFunctionFromOption(easing));
        valueAnimator.setDuration(duration);
        valueAnimator.start();
    }

    @Override
    public void animateXY(Easing.EasingAnimation easing, long xDuration, long yDuration) {
        this.animateX(easing, xDuration);
        this.animateY(easing, yDuration);
    }


    class ViewXAnimateListener implements ValueAnimator.AnimatorUpdateListener {

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            animateByLayoutParams(animation);
        }

        private void animateByLayoutParams(ValueAnimator animation) {
            int value = (int) animation.getAnimatedValue();
            ViewGroup.MarginLayoutParams mp = getMarginLayoutParams();
            mp.width = value;
            mp.height = mView.getMeasuredHeight();
            mView.setLayoutParams(mp);
        }

    }

    class ViewYAnimateListener implements ValueAnimator.AnimatorUpdateListener {

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            animateByLayoutParams(animation);
        }

        private void animateByLayoutParams(ValueAnimator animation) {
            int value = (int) animation.getAnimatedValue();
            ViewGroup.MarginLayoutParams mp = getMarginLayoutParams();
            mp.height = value;
            mp.width = mView.getMeasuredWidth();
            mView.setLayoutParams(mp);
        }

    }

    class ViewAnimatorListener implements Animator.AnimatorListener{
        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {

        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    }

}
