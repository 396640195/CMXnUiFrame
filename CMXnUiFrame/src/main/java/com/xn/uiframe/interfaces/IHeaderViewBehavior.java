package com.xn.uiframe.interfaces;

import android.widget.TextView;

/**
 * Created by xn068074 on 2017/6/13.
 */

public interface IHeaderViewBehavior {
    /**
     * 设置头部左边的文字
     * @param resource 字符资源ID
     * @return 返回当前的TextView对象
     */
    TextView setHeaderLeftText(int resource);

    /**
     * 设置头部左边的图片资源
     * @param resource 图片资源ID
     * @return 返回当前的TextView对象
     */
    TextView setHeaderLeftImage(int resource);

    /**
     * 设置头部中间的文字内容
     * @param resource 字符资源ID
     * @return 返回当前的TextView对象
     */
    TextView setHeaderCenterText(int resource);

    /**
     * 设置头部右边的文字内容
     * @param resource 字符资源ID
     * @return 返回当前的TextView对象
     */
    TextView setHeaderRightText(int resource);

    /**
     * 设置头部右边的图片资源
     * @param resource 图片资源ID
     * @return 返回当前的TextView对象
     */
    TextView setHeaderRightImage(int resource);
}
