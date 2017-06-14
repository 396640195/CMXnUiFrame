package com.xn.uiframe.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.xn.uiframe.activity.UIFrameBasicActivity;
import com.xn.uiframe.interfaces.IContainerManager;
import com.xn.uiframe.layout.AbstractLayoutManager;
import com.xn.uiframe.layout.BottomLayoutManager;
import com.xn.uiframe.layout.CenterLayoutManager;
import com.xn.uiframe.layout.FullScreenLayoutManager;
import com.xn.uiframe.layout.HeaderLayoutManager;
import com.xn.uiframe.layout.TopLayoutManager;

public class MainActivity extends UIFrameBasicActivity implements View.OnClickListener {
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

        v.findViewById(R.id.ok).setOnClickListener(this);
        v.findViewById(R.id.show_dialog_view).setOnClickListener(this);
        v.findViewById(R.id.show_load_view).setOnClickListener(this);
        v.findViewById(R.id.show_top_view).setOnClickListener(this);

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
        view.findViewById(R.id.ok_button).setOnClickListener(this);
        return fsm;
    }

    @Override
    public FullScreenLayoutManager addLoadingView(IContainerManager container) {
        FullScreenLayoutManager fullScreenLayoutManager = FullScreenLayoutManager.buildLayout(container, R.layout.layout_loading_view, AbstractLayoutManager.Layer.LAYER_LOAD_SCREEN);
        View view = fullScreenLayoutManager.getContentView();
        view.findViewById(R.id.load_test_button).setOnClickListener(this);
        return fullScreenLayoutManager;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ok:
                setHeaderViewVisible(isHeaderViewVisible() ? false : true);
                break;
            case R.id.show_dialog:
                setDialogViewVisible(isDialogViewVisible() ? false : true);
                break;
            case R.id.show_load_view:
                setLoadViewVisible(isLoadViewVisible() ? false : true);
                break;
            case R.id.show_top_view:
                setTopViewVisible(isTopViewVisible() ? false : true);
                break;
            case R.id.load_test_button:
                Toast.makeText(MainActivity.this, "Load View clicked.", Toast.LENGTH_LONG).show();
                setLoadViewVisible(false);
                break;
            case R.id.ok_button:
                setDialogViewVisible(false);
                break;
        }
    }
}
