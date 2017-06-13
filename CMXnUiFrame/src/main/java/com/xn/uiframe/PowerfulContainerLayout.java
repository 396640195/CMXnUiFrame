package com.xn.uiframe;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.xn.uiframe.layout.ILayoutManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 *   UI框架容器类:
 *   该容器类主要用来实现应用界面开发过程中共性模块的布局，不再需要复杂多层次的layout文件叠加造成的层次过深，效率低的问题;
 * 在这个布局中包括通用的头部Header,Top,Center,Bottom几个部分,这几个部分组成一个通用的整屏界面; 且按需动态添加到容器当中;
 * <br>
 *   除了上面所述的几个部分，还整合了遮罩层的概念,按照布局层次的先后，我们可以用这个遮罩层实现如下几个功能:
 *   1.网络请求,加载进度等场景需要弹出来的进度界面;
 *   2.在应用中经常要弹出一个对话框,在这里可以直接设置一个布局来替代对话框的使用;
 *   3.异常处理界面,包括网络请求失败，无数据，重试等界面;
 * Created by 陈真 on 2017/6/12.
 * Copyright © 2015 深圳市小牛在线互联网信息咨询有限公司 股东公司：深圳市小牛互联网金融服务有限公司 版权所有 备案号：粤ICP备14079927号  ICP证粤B2-20160194
 * </p>
 */

public class PowerfulContainerLayout extends ViewGroup {

    private List<ILayoutManager<View,ILayoutManager>> mLayoutManagers;

    public PowerfulContainerLayout(Context context) {
        super(context);
        this.initLayoutManagers();
    }

    public PowerfulContainerLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.initLayoutManagers();
    }

    public PowerfulContainerLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initLayoutManagers();
    }

    /**
     * 初始化容器中布局集合
     */
    private void initLayoutManagers(){
        mLayoutManagers = new ArrayList<>();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for(ILayoutManager<View,ILayoutManager> manager: mLayoutManagers){
            manager.onLayout(this,l,t,r,b);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /**先排序，按顺序进行测量**/
        Collections.sort(mLayoutManagers);
        for(ILayoutManager<View,ILayoutManager> manager: mLayoutManagers){
            manager.onMeasure(this,widthMeasureSpec,heightMeasureSpec);
        }
    }

    public void  measureChild(View view, int widthMeasureSpec, int heightMeasureSpec){
        super.measureChild(view,widthMeasureSpec,heightMeasureSpec);
    }

    public List<ILayoutManager<View,ILayoutManager>> layoutManagers(){
        return mLayoutManagers;
    }

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
