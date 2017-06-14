package com.xn.uiframe.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.xn.uiframe.activity.UIFrameBasicActivity;
import com.xn.uiframe.interfaces.IContainerManager;
import com.xn.uiframe.layout.AbstractLayoutManager;
import com.xn.uiframe.layout.BottomLayoutManager;
import com.xn.uiframe.layout.CenterLayoutManager;
import com.xn.uiframe.layout.FullScreenLayoutManager;
import com.xn.uiframe.layout.HeaderLayoutManager;
import com.xn.uiframe.layout.TopLayoutManager;

public class MainActivity extends UIFrameBasicActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        this.setContainerBackgroundResource(R.drawable.icon_background);
        //this.setContainerBackgroundColor(R.color.colorPrimary);
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
        v.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideHeaderView();
            }
        });
        return blm;
    }

    @Override
    public CenterLayoutManager addCenterView(IContainerManager container) {
        return CenterLayoutManager.buildLayout(container, R.layout.layout_center);
    }

    @Override
    public FullScreenLayoutManager addDialogView(IContainerManager container) {
        FullScreenLayoutManager fsm  = FullScreenLayoutManager.buildLayout(container, R.layout.layout_dialog, AbstractLayoutManager.Layer.LAYER_DIALOG_SCREEN);
        View view = fsm.getContentView();
        view.findViewById(R.id.dialogok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideDialogView();
            }
        });
        return  fsm;
    }

    @Override
    public FullScreenLayoutManager addLoadingView(IContainerManager container) {
        return FullScreenLayoutManager.buildLayout(container, R.layout.layout_loading_view, AbstractLayoutManager.Layer.LAYER_LOAD_SCREEN);
    }

}
