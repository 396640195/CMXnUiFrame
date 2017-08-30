package com.xn.uiframe.interfaces;

import android.view.View;

import java.util.List;

/**
 * 定义容器接口
 * Created by 陈真 on 2017/6/13.
 * Copyright © 2015 深圳市小牛在线互联网信息咨询有限公司 股东公司：深圳市小牛互联网金融服务有限公司 版权所有 备案号：粤ICP备14079927号  ICP证粤B2-20160194
 */

public interface IContainerManager<T> {
     /**
      * 定义容器添加子布局的方法
      * @param t
      */
     void addLayoutManager(T t);

     /**
      * 定义当前容器是否已经存在该布局
      * @param t
      * @return true: 存在该布类型的布局, false:不存在该类型的布局
      */
     boolean contains(T t);

     /**
      * 请求重新布局
      */
     void requestLayout();

     /**
      * 给定一个View进行测量它的高宽
      * @param view
      * @param widthMeasureSpec
      * @param heightMeasureSpec
      */
     void measureChild(View view, int widthMeasureSpec, int heightMeasureSpec);

     /**
      * 获得容器里的所有布局管理对象
      * @return
      */
     List<ILayoutManager<ILayoutManager>> layoutManagers();

}
