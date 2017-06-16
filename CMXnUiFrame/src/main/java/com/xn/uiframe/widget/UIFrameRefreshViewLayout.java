package com.xn.uiframe.widget;


import android.content.Context;
import android.util.AttributeSet;

import com.dalong.refreshlayout.FooterView;
import com.dalong.refreshlayout.RefreshLayout;

/**
 * <p>
 * Created by 陈真 on 2017/6/16.
 * Copyright © 2015 深圳市小牛在线互联网信息咨询有限公司 股东公司：深圳市小牛互联网金融服务有限公司 版权所有 备案号：粤ICP备14079927号  ICP证粤B2-20160194
 * </p>
 */

public class UIFrameRefreshViewLayout extends RefreshLayout {

    public UIFrameRefreshViewLayout(Context context) {
        super(context);
    }

    public UIFrameRefreshViewLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    public void init() {
        UIFrameRefreshHeader header = new UIFrameRefreshHeader(getContext());
        FooterView footer = new FooterView(getContext());

        addHeader(header);
        addFooter(footer);
        setOnHeaderListener(header);
        setOnFooterListener(footer);
    }
}
