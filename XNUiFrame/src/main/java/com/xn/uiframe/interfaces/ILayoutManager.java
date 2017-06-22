package com.xn.uiframe.interfaces;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;

import com.xn.uiframe.animation.Easing;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 布局管理接口:
 * UI框架主要分为容器、布局两个层次关系,每个布局委托给布局管理接口来实现具体逻辑;
 * 容器里添加一个布局Layout返回添加后的View对象,这样方便接口的使用.
 * 在UI框架中容器直接面向的是布局*layout.xml, 所以，这里定义为容器与布局的层次关系，而非容器与View的层次关系;
 * </p>
 * <br>
 * <p>
 * Created by 陈真 on 2017/6/12.
 * Copyright © 2015 深圳市小牛在线互联网信息咨询有限公司 股东公司：深圳市小牛互联网金融服务有限公司 版权所有 备案号：粤ICP备14079927号  ICP证粤B2-20160194
 * </p>
 */

public interface ILayoutManager<T> extends Comparable<T>, Serializable {
    /**
     * @param layout          布局文件
     * @return 返回添加布局之后对应的View实例对象
     */
    View addLayout(@LayoutRes int layout);

    /**
     * 针对添加进来的布局文件对象在容器中进行布局
     *
     * @param left   左上角的x位置
     * @param top    左上角的y位置
     * @param right  右上触的x位置
     * @param bottom 右上触的y位置
     */
    void onLayout(int left, int top, int right, int bottom);


    /**
     * 测量布局文件在容器中的高宽
     *
     * @param widthMeasureSpec  测量宽的基准值
     * @param heightMeasureSpec 测量高的基准值
     */
    void onMeasure(int widthMeasureSpec, int heightMeasureSpec);

    /**
     * 获取当前布局测量的高度.
     *
     * @return
     */
    int getMeasuredHeight();

    /**
     * 获取当前布局测量的宽度.
     *
     * @return
     */
    int getMeasuredWidth();

    /**
     * 获得当前布局的参数信息
     *
     * @return
     */
    ViewGroup.MarginLayoutParams getMarginLayoutParams();

    /**
     * 设置当前层级是否可以点击
     * @param clickable
     */
    void setClickable(boolean clickable);
    /**
     * 返回当前布局所在的层级
     *
     * @return
     */
    int getLayer();

    /**
     * 设置当前布局是否可见.
     *
     * @param visible
     * @see {@link android.view.View#VISIBLE },{@link android.view.View#GONE },{@link android.view.View#INVISIBLE }
     */
    void setVisibility(int visible);

    int getVisibility();

    /**
     * 获取该布局的View对象
     **/
    List<View> getContentViews();

    /**
     * 获取该布局的View对象
     **/
    View getContentView();

    /**
     * 针对该视图进行Y轴动画
     *
     * @param duration 动画时间
     */
    void animateY(long duration);

    /**
     * 针对该视图进行X轴动画
     *
     * @param duration 动画时间
     */
    void animateX(long duration);

    /**
     * 针对该视图进行XY轴动画
     *
     * @param xDuration x轴动画时间
     * @param yDuration y轴动画时间
     */
    void animateXY(long xDuration, long yDuration);

    /**
     * 针对该视图进行Y轴动画
     *
     * @param easing   动画效果类型 参见{@link com.xn.uiframe.animation.Easing.EasingAnimation}
     * @param duration
     */
    void animateY(Easing.EasingAnimation easing, long duration);

    /**
     * 针对该视图进行X轴动画
     *
     * @param easing   动画效果类型 参见{@link com.xn.uiframe.animation.Easing.EasingAnimation}
     * @param duration
     */
    void animateX(Easing.EasingAnimation easing, long duration);

    /**
     * 针对该视图进行XY轴动画
     *
     * @param easing    动画效果类型 参见{@link com.xn.uiframe.animation.Easing.EasingAnimation}
     * @param xDuration x轴动画时间
     * @param yDuration y轴动画时间
     */
    void animateXY(Easing.EasingAnimation easing, long xDuration, long yDuration);
}
