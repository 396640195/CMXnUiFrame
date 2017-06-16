package com.xn.uiframe.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.xn.uiframe.activity.UIFrameBasicActivity;
import com.xn.uiframe.animation.Easing;
import com.xn.uiframe.interfaces.IContainerManager;
import com.xn.uiframe.ViewElement;
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
        v.findViewById(R.id.relayout).setOnClickListener(this);

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
        setElementViewVisible(ViewElement.DialogView, false);
        return fsm;
    }

//    @Override
//    public FullScreenLayoutManager addLoadingView(IContainerManager container) {
//        FullScreenLayoutManager fullScreenLayoutManager = FullScreenLayoutManager.buildLayout(container, R.layout.layout_loading_view, AbstractLayoutManager.Layer.LAYER_LOAD_SCREEN);
//        View view = fullScreenLayoutManager.getContentView();
//        view.findViewById(R.id.ok_button_of_load_view).setOnClickListener(this);
//        setElementViewVisible(ViewElement.LoadView,false);
//        return fullScreenLayoutManager;
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_of_header_control:
                boolean visible = isElementViewVisible(ViewElement.HeaderView);
                setElementViewVisible(ViewElement.HeaderView, visible ? false : true);
                break;
            case R.id.show_dialog_view:
                setElementViewVisible(ViewElement.DialogView, isElementViewVisible(ViewElement.DialogView) ? false : true);
                break;
            case R.id.show_load_view:
                setElementViewVisible(ViewElement.LoadView, isElementViewVisible(ViewElement.LoadView) ? false : true);
                break;
            case R.id.show_top_view:
                setElementViewVisible(ViewElement.TopView, isElementViewVisible(ViewElement.TopView) ? false : true);
                break;
            case R.id.ok_button_of_load_view:
                Toast.makeText(BasicSimpleActivity.this, "Load View clicked.", Toast.LENGTH_LONG).show();
                setElementViewVisible(ViewElement.LoadView, false);
                break;
            case R.id.ok_button_of_dialog:
                setElementViewVisible(ViewElement.DialogView, false);
                break;

            case R.id.animate_header:
                animateX(ViewElement.HeaderView, Easing.EasingAnimation.EaseOutBounce, 500);
                break;
            case R.id.animate_top:
                animateX(ViewElement.TopView, Easing.EasingAnimation.EaseOutBounce, 500);
                break;
            case R.id.animate_center:
                animateY(ViewElement.CenterView, Easing.EasingAnimation.EaseOutBounce,1500);
                break;
            case R.id.animate_bottom:
                animateX(ViewElement.BottomView, Easing.EasingAnimation.EaseInQuart, 500);
                break;
            case R.id.relayout:

                break;
        }
    }
}
