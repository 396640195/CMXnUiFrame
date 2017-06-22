package com.xn.uiframe.layout;

import android.animation.ValueAnimator;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xn.uiframe.PowerfulContainerLayout;
import com.xn.uiframe.animation.Easing;
import com.xn.uiframe.animation.UIFrameViewAnimator;
import com.xn.uiframe.interfaces.IContainerManager;
import com.xn.uiframe.interfaces.ILayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 抽象布局管理类: 主要实现各种布局管理的公性特征，并定义布局的层次关系类型;
 * Created by 陈真 on 2017/6/12.
 * Copyright © 2015 深圳市小牛在线互联网信息咨询有限公司 股东公司：深圳市小牛互联网金融服务有限公司 版权所有 备案号：粤ICP备14079927号  ICP证粤B2-20160194
 * </p>
 */

public abstract class AbstractLayoutManager implements ILayoutManager<ILayoutManager> {
    protected IContainerManager mContainerManager;
    private ViewXAnimateListener mViewXAnimateListener;

    protected UIFrameViewAnimator mUIFrameViewAnimator;
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
        this.mUIFrameViewAnimator = new UIFrameViewAnimator(new ViewAnimateListener());
    }

    /**
     * 默认层级为对话框全屏模式
     **/
    protected int mLayer = Layer.LAYER_DIALOG_SCREEN;

    /**
     * 当调用了{@link AbstractLayoutManager#addLayout(int)}
     * 方法后，会保存当前视图对象;
     */
    protected List<View> mViewCollections = new ArrayList<>();

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
         * 覆盖在CenterLayout之上的层，用来显示无数据，加载中或异常信息的一个局部视图层级;  与全屏{@link Layer#LAYER_FULL_SCREEN_EXTRA}对应
         */
        public static final int LAYER_BASIC_CENTER_MASK_PART = 0x10005;

        /**
         * 该层级用来备用特殊情况，如果前两层不足以满足需求，可以根据需求使用这一层级;
         * 它处于{@link AbstractLayoutManager.Layer#LAYER_BASIC_CENTER_PART}层级之上.
         **/
        public static final int LAYER_FULL_SCREEN_EXTRA = 0x10010;

        /**
         * 处于UI的最顶层.该层级主要用来实现对话框的功能,完全替代话框，它的层级处于
         * 它处于{@link AbstractLayoutManager.Layer#LAYER_FULL_SCREEN_EXTRA}层级之上.
         **/
        public static final int LAYER_DIALOG_SCREEN = 0x10020;

    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        for(View view:mViewCollections) {
            /**
             * 当View处于{@link View.VISIBLE} 才测量它的宽高;
             */
            if (view.getVisibility() != View.VISIBLE) {
                continue;
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

            int basicWidthSpec = View.MeasureSpec.makeMeasureSpec((int) (basicWidth * this.mUIFrameViewAnimator.getPhaseX()), View.MeasureSpec.EXACTLY);
            int basicHeightSpec = View.MeasureSpec.makeMeasureSpec((int) (basicHeight * this.mUIFrameViewAnimator.getPhaseY()), View.MeasureSpec.EXACTLY);
            mContainerManager.measureChild(view, basicWidthSpec, basicHeightSpec);
        }

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
    public View addLayout(@LayoutRes int layout) {
        if(mViewCollections.size() > 0 ){
            return null;
        }
        PowerfulContainerLayout powerfulContainer = (PowerfulContainerLayout) mContainerManager;
        View  view = LayoutInflater.from(powerfulContainer.getContext()).inflate(layout, powerfulContainer, false);
        mViewCollections.add(view);
        return view;
    }

    @Override
    public int getMeasuredHeight() {
        for(View view:mViewCollections){
            return view.getMeasuredHeight();
        }
        return 0;
    }

    @Override
    public int getMeasuredWidth() {
        for(View view:mViewCollections){
            return view.getMeasuredWidth();
        }
        return 0;
    }

    @Override
    public ViewGroup.MarginLayoutParams getMarginLayoutParams() {
        for(View view:mViewCollections){
            return (ViewGroup.MarginLayoutParams)view.getLayoutParams();
        }
        return null;
    }

    @Override
    public int getLayer() {
        return mLayer;
    }

    @Override
    public void setVisibility(int visible) {
        //当该层级只有一个视图的时候，可以调用该方法;如果有多个层级视图，则在初始化的时候持有返回的对象，再进行操控;
        for(View view:mViewCollections) {
            if(view.getVisibility() == View.VISIBLE && visible == View.GONE) {
                view.setVisibility(visible);
                break;
            }else if(view.getVisibility() == View.GONE && visible == View.VISIBLE){
                view.setVisibility(visible);
                break;
            }
        }
    }

    @Override
    public int getVisibility() {
        //如果该层级有一个视图是要见的，则认为该层级是可见的;
        for(View view:mViewCollections) {
            if(view.getVisibility() == View.VISIBLE) {
                return view.VISIBLE;
            }
        }
        return View.GONE;
    }

    @Override
    public View getContentView() {
        return mViewCollections == null || mViewCollections.size() == 0 ? null : mViewCollections.get(0);
    }

    @Override
    public List<View> getContentViews() {
        return mViewCollections;
    }

    @Override
    public void setClickable(boolean clickable) {
        if(clickable) {
            for (View view : mViewCollections) {
                if (view.getVisibility() == View.VISIBLE) {
                    view.setClickable(true);
                } else {
                    view.setClickable(false);
                }
            }
        }else{
            for (View view : mViewCollections) {
                view.setClickable(false);
            }
        }
    }

    @Override
    public void animateY(long duration) {
         this.mUIFrameViewAnimator.animateY(duration);
    }

    @Override
    public void animateX(long duration) {
        for(View view:mViewCollections) {
            if(view.getVisibility() != View.VISIBLE)continue;
            ValueAnimator valueAnimator = ValueAnimator.ofInt(0, view.getMeasuredWidth());
            valueAnimator.addUpdateListener(mViewXAnimateListener);
            valueAnimator.setDuration(duration);
            valueAnimator.start();
        }
    }

    @Override
    public void animateXY(long xDuration, long yDuration) {
        this.animateX(xDuration);
        this.animateY(yDuration);
    }

    @Override
    public void animateY(Easing.EasingAnimation easing, long duration) {
        this.mUIFrameViewAnimator.animateY(duration,easing);
    }

    @Override
    public void animateX(Easing.EasingAnimation easing, long duration) {
        this.mUIFrameViewAnimator.animateX(duration,easing);
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
            for(View view:mViewCollections) {
                if(view.getVisibility() != View.VISIBLE)continue;
                int value = (int) animation.getAnimatedValue();
                ViewGroup.MarginLayoutParams mp = getMarginLayoutParams();
                mp.width = value;
                mp.height = view.getMeasuredHeight();
                view.setLayoutParams(mp);
            }
        }

    }


    class ViewAnimateListener implements ValueAnimator.AnimatorUpdateListener {

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            animateByLayoutParams(animation);
        }

        private void animateByLayoutParams(ValueAnimator animation) {
           mContainerManager.requestLayout();
        }

    }

}
