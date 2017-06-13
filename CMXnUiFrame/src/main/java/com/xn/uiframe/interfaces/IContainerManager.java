package com.xn.uiframe.interfaces;

/**
 * Created by xn068074 on 2017/6/13.
 */

public interface IContainerManager<T> {
     void addLayoutManager(T t);
     boolean contains(T t);
}
