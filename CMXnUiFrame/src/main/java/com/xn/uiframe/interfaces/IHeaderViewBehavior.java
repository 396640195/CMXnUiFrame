package com.xn.uiframe.interfaces;

/**
 * Created by xn068074 on 2017/6/13.
 */

public interface IHeaderViewBehavior {
    /**设置头部左边的文字**/
    void setHeaderLeftText(int resource);
    /**设置头部左边的图片资源**/
    void setHeaderLeftImage(int resource);
    /**设置头部中间的文字内容**/
    void setHeaderCenterText(int resource);
    /**设置头部右边的文字内容**/
    void setHeaderRightText(int resource);
    /**设置头部右边的图片资源**/
    void setHeaderRightImage(int resource);
}
