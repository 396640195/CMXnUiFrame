package com.xn.uiframe.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xn.uiframe.activity.UIFrameBasicFragment;
import com.xn.uiframe.interfaces.IContainerManager;
import com.xn.uiframe.layout.CenterLayoutManager;

/**
 * Created by xn068074 on 2017/6/19.
 */

public class SimpleFragment extends UIFrameBasicFragment {
    @Override
    public void addCompanionScrollableHeader(CenterLayoutManager container) {
        container.addCompanionScrollableHeader(R.layout.layout_companion_header);
    }

    @Override
    public void addCompanionScrollableFooter(CenterLayoutManager container) {
        container.addCompanionScrollableHeader(R.layout.layout_companion_footer);
    }

    @Override
    public CenterLayoutManager addCenterView(IContainerManager container) {
        return CenterLayoutManager.buildPullRefreshLayout(container);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view = super.onCreateView(inflater, container, savedInstanceState);
        TextView textView = (TextView)view.findViewById(R.id.header_companion);
        Bundle bundle = getArguments();
        String args = bundle.getString("content");
        textView.setText(args);
        return view;
    }

    @Override
    public void onCompanionViewAddFinished(CenterLayoutManager container) {

    }
}
