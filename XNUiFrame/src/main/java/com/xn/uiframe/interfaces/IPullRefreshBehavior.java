package com.xn.uiframe.interfaces;

/**
 * pull refresh接口定义
 * Created by 陈真 on 2017/6/16.
 */
public interface IPullRefreshBehavior {
    /**
     * 停止刷新
     * @param isSuccess
     */
    void stopRefresh(boolean isSuccess);
    /**
     * 停止加载更多
     * @param isSuccess
     */
    void stopLoadMore(boolean isSuccess);

    /**
     * 是否开启下拉刷新功能
     * @param enable
     */
    void enableRefresh(boolean enable);

    /**
     * 是否开启上拉加载更多功能;
     * @param enable
     */
    void enableLoadMore(boolean enable);
}
