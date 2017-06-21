package com.xn.uiframe.exception;

/**
 * <p>
 * 框架层参数异常定义
 * Created by 陈真 on 2017/6/12.
 * Copyright © 2015 深圳市小牛在线互联网信息咨询有限公司 股东公司：深圳市小牛互联网金融服务有限公司 版权所有 备案号：粤ICP备14079927号  ICP证粤B2-20160194
 * </p>
 */

public class UIFrameIllegalArgumentException extends RuntimeException {
    public UIFrameIllegalArgumentException(String message) {
        super(message);
    }
}
