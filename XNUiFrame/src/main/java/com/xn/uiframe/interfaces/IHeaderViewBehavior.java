package com.xn.uiframe.interfaces;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.widget.TextView;

import com.xn.uiframe.layout.HeaderLayoutManager;

/**
 * 定义头部视图的具有的操控功能.
 * Created by 陈真 on 2017/6/13.
 * Copyright © 2015 深圳市小牛在线互联网信息咨询有限公司 股东公司：深圳市小牛互联网金融服务有限公司 版权所有 备案号：粤ICP备14079927号  ICP证粤B2-20160194
 */

public interface IHeaderViewBehavior {
    /**
     * 设置头部左边的文字
     *
     * @param resource 字符资源ID
     * @return 返回当前的TextView对象
     */
    TextView setHeaderLeftText(@StringRes int resource);

    /**
     * 设置头部左边的文字
     *
     * @param content 字符
     * @return 返回当前的TextView对象
     */
    TextView setHeaderLeftText(String content);

    /**
     * 设置头部左边的图片资源
     *
     * @param resource 图片资源ID
     * @return 返回当前的TextView对象
     */
    TextView setHeaderLeftImage(@DrawableRes int resource);

    /**
     * 设置头部左边的图片资源
     *
     * @param resource    图片Drawable
     * @param scaleFactor 缩放因子 浮点
     * @return 返回当前的TextView对象
     */
    TextView setHeaderLeftImage(@DrawableRes int resource, float scaleFactor);

    /**
     * 设置头部中间的文字内容
     *
     * @param resource 字符资源ID
     * @return 返回当前的TextView对象
     */
    TextView setHeaderCenterText(@StringRes int resource);

    /**
     * 设置头部中间的文字内容
     *
     * @param content 字符
     * @return 返回当前的TextView对象
     */
    TextView setHeaderCenterText(String content);

    /**
     * 设置头部右边的文字内容
     *
     * @param resource 字符资源ID
     * @return 返回当前的TextView对象
     */
    TextView setHeaderRightText(@StringRes int resource);

    /**
     * 设置头部右边的文字内容
     *
     * @param content 字符资
     * @return 返回当前的TextView对象
     */
    TextView setHeaderRightText(String content);

    /**
     * 设置头部右边的图片资源
     *
     * @param resource 图片资源ID
     * @return 返回当前的TextView对象
     */
    TextView setHeaderRightImage(@DrawableRes int resource);

    /**
     * 设置头部左边的图片资源
     *
     * @param resource    图片Drawable
     * @param scaleFactor 缩放因子 浮点
     * @return 返回当前的TextView对象
     */
    TextView setHeaderRightImage(@DrawableRes int resource, float scaleFactor);

    /**
     * 设置头部视图的点击事件
     *
     * @param lister
     */
    void setOnHeaderClickLister(HeaderLayoutManager.OnHeaderViewClickListener lister);
}
