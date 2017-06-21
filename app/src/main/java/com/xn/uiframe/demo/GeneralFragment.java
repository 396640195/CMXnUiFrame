package com.xn.uiframe.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.xn.uiframe.activity.UIFrameBasicFragment;
import com.xn.uiframe.interfaces.IContainerManager;
import com.xn.uiframe.layout.CenterLayoutManager;
import com.xn.uiframe.utils.EventBusProxy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xn068074 on 2017/6/19.
 */

public class GeneralFragment extends UIFrameBasicFragment {

    @Override
    public CenterLayoutManager addCenterView(IContainerManager container) {
        CenterLayoutManager clm = CenterLayoutManager.buildGeneralLayout(container,R.layout.layout_center_general);
        return clm;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view = super.onCreateView(inflater, container, savedInstanceState);
        TextView textView = (TextView)view.findViewById(R.id.header_companion);
        Bundle bundle = getArguments();
        if(bundle != null && textView != null) {
            String args = bundle.getString("content");
            textView.setText(args);
        }
        return view;
    }


    @Override
    public void onRefresh() {
        EventBusProxy.dispatcherOnMainThreadDelay(new Runnable() {
            @Override
            public void run() {
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
}