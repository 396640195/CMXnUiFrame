package com.xiaoniu.uiframe.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.xiaoniu.uiframe.R;
import com.xn.uiframe.ElementView;
import com.xn.uiframe.activity.UIFrameBasicActivity;
import com.xn.uiframe.animation.Easing;
import com.xn.uiframe.interfaces.IContainerManager;
import com.xn.uiframe.layout.BottomLayoutManager;
import com.xn.uiframe.layout.CenterLayoutManager;
import com.xn.uiframe.layout.CenterMaskLayoutManager;
import com.xn.uiframe.layout.DialogLayoutManager;
import com.xn.uiframe.layout.FullScreenLayoutManager;
import com.xn.uiframe.layout.HeaderLayoutManager;
import com.xn.uiframe.layout.TopLayoutManager;
import com.xn.uiframe.utils.EventBusProxy;

public class BasicSimpleActivity extends UIFrameBasicActivity implements View.OnClickListener, TabViewHolder.OnTabSelectListener {
    protected TabViewHolder mTabViewHolder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //this.setContainerBackgroundResource(R.drawable.icon_background);
        //this.setContainerBackgroundColor(getResources().getColor(R.color.ui_frame_ripple_color));
    }

    @Override
    public HeaderLayoutManager addHeaderView(IContainerManager container) {
        //HeaderLayoutManager hlm = HeaderLayoutManager.buildLayoutManager(container, R.layout.layout_header);
        HeaderLayoutManager hlm = HeaderLayoutManager.buildLayoutManager(container);
        hlm.setHeaderLeftImage(R.mipmap.arrow_left_normal,0.8f);
        hlm.setHeaderRightText("更多");
        return hlm;
    }

    @Override
    public TopLayoutManager addTopView(IContainerManager container) {
        TopLayoutManager tlm = TopLayoutManager.buildLayoutManager(container, R.layout.layout_top);
        tlm.setVisibility(View.GONE);
        return tlm;
    }

    @Override
    public BottomLayoutManager addBottomView(IContainerManager container) {
        BottomLayoutManager blm = BottomLayoutManager.buildLayout(container, R.layout.layout_simple_bottom);
        View view = blm.getContentView();
        mTabViewHolder = new TabViewHolder((LinearLayout) view, this, this);
        return blm;
    }

    @Override
    public CenterLayoutManager addCenterView(IContainerManager container) {
        CenterLayoutManager clt = CenterLayoutManager.buildGeneralLayoutManager(container, R.layout.layout_center);
        return clt;
    }

    private View mMask01, mMask02;

    @Override
    public CenterMaskLayoutManager addCenterMaskView(IContainerManager container) {
        CenterMaskLayoutManager clt = CenterMaskLayoutManager.buildLayoutManager(container);
        mMask01 = clt.addLayout(R.layout.layout_center_mask01);
        mMask02 = clt.addLayout(R.layout.layout_center_mask02);
        //只有一个mask视图可以用这个方法
        //clt.setVisibility(View.GONE);
        mMask01.setVisibility(View.GONE);
        mMask02.setVisibility(View.GONE);

        mMask01.setOnClickListener(this);
        mMask02.setOnClickListener(this);

        return clt;
    }

    private View mDialog01, mDialog02;

    @Override
    public DialogLayoutManager addDialogView(IContainerManager container) {
        DialogLayoutManager fsm = DialogLayoutManager.buildLayoutManager(container);

        mDialog01 = fsm.addLayout(R.layout.layout_dialog_01);
        mDialog01.findViewById(R.id.ok_button_of_dialog_01).setOnClickListener(this);
        mDialog01.setVisibility(View.GONE);

        mDialog02 = fsm.addLayout(R.layout.layout_dialog_02);
        mDialog02.findViewById(R.id.ok_button_of_dialog_02).setOnClickListener(this);
        mDialog02.setVisibility(View.GONE);

        return fsm;
    }

    View fullScreen01, fullScreen02;

    @Override
    public FullScreenLayoutManager addExtraFullScreenView(IContainerManager container) {
        FullScreenLayoutManager fullScreenLayoutManager = FullScreenLayoutManager.buildLayoutManager(container);
        fullScreen01 = fullScreenLayoutManager.addLayout(R.layout.layout_full_screen_01);
        fullScreen02 = fullScreenLayoutManager.addLayout(R.layout.layout_full_screen_02);
        fullScreen01.setOnClickListener(this);
        fullScreen02.setOnClickListener(this);

        fullScreen01.setVisibility(View.GONE);
        fullScreen02.setVisibility(View.GONE);

        return fullScreenLayoutManager;
    }

    @Override
    public void onClick(View v) {
        if (v == fullScreen01) {
            fullScreen01.setVisibility(View.GONE);
        } else if (v == fullScreen02) {
            fullScreen02.setVisibility(View.GONE);
        } else if (v == mMask01) {
            mMask01.setVisibility(View.GONE);
        } else if (v == mMask02) {
            mMask02.setVisibility(View.GONE);
        } else {

            switch (v.getId()) {
                case R.id.button_of_header_control:
                    boolean visible = isElementViewVisible(ElementView.HeaderView);
                    setElementViewVisible(ElementView.HeaderView, visible ? false : true);
                    break;
                case R.id.show_dialog_view:
                    setElementViewVisible(ElementView.DialogView, isElementViewVisible(ElementView.DialogView) ? false : true);
                    break;

                case R.id.show_top_view:
                    setElementViewVisible(ElementView.TopView, isElementViewVisible(ElementView.TopView) ? false : true);
                    break;

                case R.id.ok_button_of_dialog_01:
                    //如果只有一个对话框，可以调用这个方法
                    //setElementViewVisible(ElementView.DialogView, false);
                    mDialog01.setVisibility(View.GONE);
                    break;
                case R.id.ok_button_of_dialog_02:
                    //如果只有一个对话框，可以调用这个方法
                    //setElementViewVisible(ElementView.DialogView, false);
                    mDialog02.setVisibility(View.GONE);
                    break;
                case R.id.animate_header:
                    animateX(ElementView.HeaderView, Easing.EasingAnimation.EaseInQuart, 1000);
                    break;
                case R.id.animate_top:
                    animateX(ElementView.TopView, Easing.EasingAnimation.EaseInQuart, 1000);
                    break;
                case R.id.animate_center:
                    animateX(ElementView.CenterView, Easing.EasingAnimation.EaseOutBounce, 1500);
                    break;
                case R.id.animate_bottom:
                    animateX(ElementView.BottomView, Easing.EasingAnimation.EaseInQuart, 500);
                    break;
                case R.id.pull_refresh:
                    SimplePullRefreshActivity.startMe(this);
                    break;
            }
        }
    }

    @Override
    public void onRefresh() {
        EventBusProxy.dispatcherOnMainThreadDelay(new Runnable() {
            @Override
            public void run() {
                mMask01.setVisibility(View.VISIBLE);
                mMask02.setVisibility(View.VISIBLE);
                animateY(ElementView.CenterMaskView, Easing.EasingAnimation.EaseInOutQuart, 1000);
                stopRefresh(true);
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        EventBusProxy.dispatcherOnMainThreadDelay(new Runnable() {
            @Override
            public void run() {
                stopLoadMore(true);
            }
        }, 2000);
    }

    @Override
    public void onTabSelected(int index) {
        switch (index) {
            case 1:
                //只有一个mask视图，可以调用该方法
                setElementViewVisible(ElementView.CenterMaskView, !isElementViewVisible(ElementView.CenterMaskView));
                mMask01.setVisibility(View.VISIBLE);
                mMask02.setVisibility(View.VISIBLE);
                animateY(ElementView.CenterMaskView, Easing.EasingAnimation.EaseInOutQuart, 1000);
                break;
            case 2:
                setElementViewVisible(ElementView.TopView, !isElementViewVisible(ElementView.TopView));
                setElementViewVisible(ElementView.HeaderView, !isElementViewVisible(ElementView.HeaderView));
                animateXY(ElementView.HeaderView,  2000,5800);
                animateXY(ElementView.TopView,3800,5000);
                break;
            default:
                SimplePullRefreshActivity.startMe(this);
        }

    }

    @Override
    public void onLeftHeaderClicked() {
        finish();
    }

    @Override
    public void onRightHeaderClicked() {
        //多个视图需要持有该view再操控
        mDialog02.setVisibility(View.VISIBLE);
        animateY(ElementView.BottomView, 1000);
        //针对一个对话框视图，可以用此方法;
        setElementViewVisible(ElementView.DialogView, true);
    }
}
