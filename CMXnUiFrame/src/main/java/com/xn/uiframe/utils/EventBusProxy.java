package com.xn.uiframe.utils;

import android.os.Handler;
import android.os.Looper;

import org.greenrobot.eventbus.EventBus;

/**
 * <p>
 * EventBus的代理类，主要提供消息的基类的定义，及扩展的延迟发送消息的方法;
 * Created by 陈真 on 2017/6/13.
 * Copyright © 2015 深圳市小牛在线互联网信息咨询有限公司 股东公司：深圳市小牛互联网金融服务有限公司 版权所有 备案号：粤ICP备14079927号  ICP证粤B2-20160194
 * </p>
 */
public class EventBusProxy {

    private static Handler sHandler = new Handler(Looper.getMainLooper());

    /**
     * 发送一个Action的消息,接收该消息的类需要注册订阅EventBus消息;
     *
     * @param action
     */
    public static void dispatcher(Action action) {
        EventBus.getDefault().post(action);
    }

    /**
     * 延迟发送消息，用来替代Handler的postDelay方法.
     *
     * @param action
     * @param secondsDelay
     */
    public static void dispatherDelay(final Action action, long secondsDelay) {
        sHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                EventBusProxy.dispatcher(action);
            }
        }, secondsDelay);
    }

    /**
     * 定义一个通用的消息基类
     *
     * @param <T>
     */
    public static class Action<T> {
        public int action;
        public String extra;
        public T t;

        /**
         * 只需要一个类型的时候调用该构造方法
         **/
        public Action(int action) {
            this.action = action;
        }

        /**
         * 需要附带一个字符串的时候调用该构造方法
         **/
        public Action(int action, String extra) {
            this(action);
            this.extra = extra;
        }

        /**
         * 当一个Integer,String还不构需要带一个复杂对象的时候调用该方法
         **/
        public Action(int action, String extra, T t) {
            this(action, extra);
            this.t = t;
        }

    }
}

