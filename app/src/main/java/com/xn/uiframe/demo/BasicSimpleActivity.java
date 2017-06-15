package com.xn.uiframe.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.xn.uiframe.activity.UIFrameBasicActivity;
import com.xn.uiframe.animation.Easing;
import com.xn.uiframe.interfaces.IContainerManager;
import com.xn.uiframe.interfaces.ViewElementCategory;
import com.xn.uiframe.layout.AbstractLayoutManager;
import com.xn.uiframe.layout.BottomLayoutManager;
import com.xn.uiframe.layout.CenterLayoutManager;
import com.xn.uiframe.layout.FullScreenLayoutManager;
import com.xn.uiframe.layout.HeaderLayoutManager;
import com.xn.uiframe.layout.TopLayoutManager;

public class BasicSimpleActivity extends UIFrameBasicActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //this.setContainerBackgroundResource(R.drawable.icon_background);
        //this.setContainerBackgroundColor(getResources().getColor(R.color.ui_frame_ripple_color));
    }

    @Override
    public HeaderLayoutManager addHeaderView(IContainerManager container) {
        return HeaderLayoutManager.buildLayout(container, R.layout.layout_header);
    }

    @Override
    public TopLayoutManager addTopView(IContainerManager container) {
        return TopLayoutManager.buildLayout(container, R.layout.layout_top);
    }

    @Override
    public BottomLayoutManager addBottomView(IContainerManager container) {

        BottomLayoutManager blm = BottomLayoutManager.buildLayout(container, R.layout.layout_bottom);
        View v = blm.getContentView();

        v.findViewById(R.id.button_of_header_control).setOnClickListener(this);
        v.findViewById(R.id.show_dialog_view).setOnClickListener(this);
        v.findViewById(R.id.show_load_view).setOnClickListener(this);
        v.findViewById(R.id.show_top_view).setOnClickListener(this);

        v.findViewById(R.id.animate_header).setOnClickListener(this);
        v.findViewById(R.id.animate_top).setOnClickListener(this);
        v.findViewById(R.id.animate_bottom).setOnClickListener(this);
        v.findViewById(R.id.animate_center).setOnClickListener(this);


        return blm;
    }

    @Override
    public CenterLayoutManager addCenterView(IContainerManager container) {
        return CenterLayoutManager.buildLayout(container, R.layout.layout_center);
    }

    @Override
    public FullScreenLayoutManager addDialogView(IContainerManager container) {
        FullScreenLayoutManager fsm = FullScreenLayoutManager.buildLayout(container, R.layout.layout_dialog, AbstractLayoutManager.Layer.LAYER_DIALOG_SCREEN);
        View view = fsm.getContentView();
        view.findViewById(R.id.ok_button_of_dialog).setOnClickListener(this);
        setElementViewVisible(ViewElementCategory.DialogView, false);
        return fsm;
    }

    @Override
    public FullScreenLayoutManager addLoadingView(IContainerManager container) {
        FullScreenLayoutManager fullScreenLayoutManager = FullScreenLayoutManager.buildLayout(container, R.layout.layout_loading_view, AbstractLayoutManager.Layer.LAYER_LOAD_SCREEN);
        View view = fullScreenLayoutManager.getContentView();
        view.findViewById(R.id.ok_button_of_load_view).setOnClickListener(this);
        view.setVisibility(View.GONE);
        return fullScreenLayoutManager;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_of_header_control:
                boolean visible = isElementViewVisible(ViewElementCategory.HeaderView);
                setElementViewVisible(ViewElementCategory.HeaderView, visible ? false : true);
                break;
            case R.id.show_dialog_view:
                setElementViewVisible(ViewElementCategory.DialogView, isElementViewVisible(ViewElementCategory.DialogView) ? false : true);
                break;
            case R.id.show_load_view:
                setElementViewVisible(ViewElementCategory.LoadView, isElementViewVisible(ViewElementCategory.LoadView) ? false : true);
                break;
            case R.id.show_top_view:
                setElementViewVisible(ViewElementCategory.TopView, isElementViewVisible(ViewElementCategory.TopView) ? false : true);
                break;
            case R.id.ok_button_of_load_view:
                Toast.makeText(BasicSimpleActivity.this, "Load View clicked.", Toast.LENGTH_LONG).show();
                setElementViewVisible(ViewElementCategory.LoadView, false);
                break;
            case R.id.ok_button_of_dialog:
                setElementViewVisible(ViewElementCategory.DialogView, false);
                break;

            case R.id.animate_header:
                animateX(ViewElementCategory.HeaderView, Easing.EasingAnimation.EaseOutBounce, 500);
                break;
            case R.id.animate_top:
                animateY(ViewElementCategory.TopView, Easing.EasingAnimation.EaseOutBounce, 500);
                break;
            case R.id.animate_center:
                animateY(ViewElementCategory.CenterView, Easing.EasingAnimation.EaseOutBounce, 1500);
                break;
            case R.id.animate_bottom:
                animateY(ViewElementCategory.BottomView, Easing.EasingAnimation.EaseInQuart, 500);
                break;
        }
    }
}
