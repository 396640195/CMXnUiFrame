package com.xn.uiframe.layout;

import android.view.ViewGroup;

import com.xn.uiframe.PowerfulContainerLayout;

import java.io.Serializable;

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

public interface ILayoutManager<T, E> extends Comparable<E>, Serializable {
    /**
     * @param container PowerfulContainerLayout 父容器对象
     * @param layout    布局文件
     * @return 返回添加布局之后对应的View实例对象
     */
    T addLayout(PowerfulContainerLayout container, int layout);


    /**
     * 针对添加进来的布局文件对象在容器中进行布局
     *
     * @param container PowerfulContainerLayout 父容器对象
     * @param left      左上角的x位置
     * @param top       左上角的y位置
     * @param right     右上触的x位置
     * @param bottom    右上触的y位置
     */
    void onLayout(PowerfulContainerLayout container, int left, int top, int right, int bottom);


    /**
     * 测量布局文件在容器中的高宽
     *
     * @param container         PowerfulContainerLayout 父容器对象
     * @param widthMeasureSpec  测量宽的基准值
     * @param heightMeasureSpec 测量高的基准值
     */
    void onMeasure(PowerfulContainerLayout container, int widthMeasureSpec, int heightMeasureSpec);

    /**
     * 获取当前布局测量的高度.
     * @return
     */
    int getMeasuredHeight();

    /**
     * 获得当前布局的参数信息
     * @return
     */
    ViewGroup.MarginLayoutParams getMarginLayoutParams();
}
