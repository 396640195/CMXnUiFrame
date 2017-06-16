package com.xn.uiframe.interfaces;

/**
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
}
