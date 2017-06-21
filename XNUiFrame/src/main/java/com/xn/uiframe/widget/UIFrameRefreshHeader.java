package com.xn.uiframe.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xn.uiframe.R;
import com.xn.uiframe.refreshlayout.OnHeaderListener;

/**
 * <p>
 * Created by 陈真 on 2017/6/16.
 * Copyright © 2015 深圳市小牛在线互联网信息咨询有限公司 股东公司：深圳市小牛互联网金融服务有限公司 版权所有 备案号：粤ICP备14079927号  ICP证粤B2-20160194
 * </p>
 */

public class UIFrameRefreshHeader extends RelativeLayout implements OnHeaderListener {

    // 初始状态
    public static final int INIT = 0;
    // 释放刷新
    public static final int RELEASE_TO_REFRESH = 1;
    // 正在刷新
    public static final int REFRESHING = 2;
    // 操作完毕
    public static final int DONE = 5;
    // 当前状态
    private int state = INIT;
    // 刷新成功
    private  RotateAnimation refreshingAnimation;
    private  RotateAnimation rotateAnimation;
    private View headerView;
    // 下拉的箭头
    private ImageView pullView;
    // 正在刷新的图标
    private ImageView refreshingView;
    // 刷新结果图标
    private ImageView refreshStateImageView;
    // 刷新结果：成功或失败
    private TextView refreshStateTextView;
    private boolean isRefreshAfter=false;


    public UIFrameRefreshHeader(Context context) {
        super(context);

        headerView= LayoutInflater.from(context).inflate(R.layout.ui_frame_refresh_head, this, true);
        // 初始化下拉布局
        pullView = (ImageView) headerView.findViewById(R.id.ui_frame_pull_flag_icon);
        refreshStateTextView = (TextView) headerView.findViewById(R.id.ui_frame_refresh_header_status_text);
        refreshingView = (ImageView)headerView.findViewById(R.id.ui_frame_loading_icon);
        refreshStateImageView = (ImageView) headerView.findViewById(R.id.ui_frame_refresh_status_image);

        rotateAnimation = (RotateAnimation) AnimationUtils.loadAnimation(context, R.anim.ui_frame_refresh_icon_reverse_anim);
        refreshingAnimation = (RotateAnimation) AnimationUtils.loadAnimation(context, R.anim.ui_frame_refresh_icon_rotating);
        // 添加匀速转动动画
        LinearInterpolator lir = new LinearInterpolator();
        rotateAnimation.setInterpolator(lir);
        refreshingAnimation.setInterpolator(lir);
        measureView(headerView);
        changeState(INIT);
    }
    private void measureView(View child) {
        ViewGroup.LayoutParams p = child.getLayoutParams();
        if (p == null) {
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
        int lpHeight = p.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight, MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        }
        child.measure(childWidthSpec, childHeightSpec);
    }
    @Override
    public void onRefreshBefore(int scrollY, int refreshHeight, int headerHeight) {
        changeState(INIT);
    }

    /**
     *
     * @param scrollY 下拉移动的y值
     * @param refreshHeight 刷新的高度
     * @param headerHeight  header总高度
     */
    @Override
    public void onRefreshAfter(int scrollY, int refreshHeight, int headerHeight) {
        changeState(RELEASE_TO_REFRESH);
    }

    @Override
    public void onRefreshReady(int scrollY, int refreshHeight, int headerHeight) {
    }

    @Override
    public void onRefreshing(int scrollY, int refreshHeight, int headerHeight) {
        changeState(REFRESHING);
    }

    @Override
    public void onRefreshComplete(int scrollY, int refreshHeight, int headerHeight, boolean isRefreshSuccess) {
        changeState(DONE);
        // 刷新成功
        refreshStateImageView.setVisibility(View.VISIBLE);
        if(isRefreshSuccess){
            refreshStateTextView.setText(R.string.ui_frame_refresh_succeed);
            refreshStateImageView.setBackgroundResource(R.mipmap.ui_frame_refresh_succeed_icon);
        }else{
            refreshStateTextView.setText(R.string.ui_frame_refresh_fail);
            refreshStateImageView.setBackgroundResource(R.mipmap.ui_frame_refresh_failed_icon);
        }


    }

    @Override
    public void onRefreshCancel(int scrollY, int refreshHeight, int headerHeight) {
        changeState(DONE);
        // 刷新失败
        refreshStateImageView.setVisibility(View.VISIBLE);
        refreshStateTextView.setText(R.string.ui_frame_refresh_cancel);
        refreshStateImageView.setBackgroundResource(R.mipmap.ui_frame_refresh_failed_icon);
    }


    private void changeState(int to) {
        state = to;
        switch (state) {
            case INIT:
                // 下拉布局初始状态
                isRefreshAfter=false;
                refreshStateImageView.setVisibility(View.GONE);
                refreshStateTextView.setText(R.string.ui_frame_pull_to_refresh);
                pullView.clearAnimation();
                pullView.setVisibility(View.VISIBLE);
                break;
            case RELEASE_TO_REFRESH:
                // 释放刷新状态
                pullView.setVisibility(View.VISIBLE);
                if(!isRefreshAfter){
                    isRefreshAfter=true;
                    pullView.startAnimation(rotateAnimation);
                }
              
                refreshStateTextView.setText(R.string.ui_frame_release_to_refresh);

                break;
            case REFRESHING:
                // 正在刷新状态
                pullView.clearAnimation();
                refreshingView.setVisibility(View.VISIBLE);
                pullView.setVisibility(View.INVISIBLE);
                refreshingView.startAnimation(refreshingAnimation);
                refreshStateTextView.setText(R.string.ui_frame_refreshing);
                break;
            case DONE:
                refreshingView.clearAnimation();
                refreshingView.setVisibility(View.GONE);
                break;
        }
    }
}
