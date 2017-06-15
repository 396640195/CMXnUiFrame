package com.xn.uiframe.animation;

import android.animation.TimeInterpolator;
import android.annotation.SuppressLint;

/**
 * <p>
 * 动画拦截器
 * Created by 陈真 on 2017/6/12.
 * Copyright © 2015 深圳市小牛在线互联网信息咨询有限公司 股东公司：深圳市小牛互联网金融服务有限公司 版权所有 备案号：粤ICP备14079927号  ICP证粤B2-20160194
 * </p>
 */
@SuppressLint("NewApi")
public interface UIFrameAnimatorInterpolator extends TimeInterpolator {

    @Override
    float getInterpolation(float input);
}
