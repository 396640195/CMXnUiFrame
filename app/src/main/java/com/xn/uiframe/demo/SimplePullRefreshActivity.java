package com.xn.uiframe.demo;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.xn.uiframe.ElementView;
import com.xn.uiframe.animation.Easing;
import com.xn.uiframe.interfaces.IContainerManager;
import com.xn.uiframe.layout.BottomLayoutManager;
import com.xn.uiframe.layout.CenterLayoutManager;
import com.xn.uiframe.utils.EventBusProxy;

import java.util.ArrayList;
import java.util.List;

public class SimplePullRefreshActivity extends BasicSimpleActivity {
    @Override
    public CenterLayoutManager addCenterView(IContainerManager container) {
        return CenterLayoutManager.buildPullRefreshLayout(container);
    }

    @Override
    public void addCompanionScrollableHeader(CenterLayoutManager container) {
        container.addCompanionScrollableHeader(R.layout.layout_companion_header);
    }

    @Override
    public void addCompanionScrollableFooter(CenterLayoutManager container) {
        container.addCompanionScrollableFooter(R.layout.layout_companion_footer);
    }

    @Override
    public BottomLayoutManager addBottomView(IContainerManager container) {

        BottomLayoutManager blm = BottomLayoutManager.buildLayout(container, R.layout.layout_bottom_of_pull_refresh);
        View v = blm.getContentView();

        v.findViewById(R.id.show_top_view).setOnClickListener(this);

        v.findViewById(R.id.animate_header).setOnClickListener(this);
        v.findViewById(R.id.animate_top).setOnClickListener(this);
        v.findViewById(R.id.animate_bottom).setOnClickListener(this);
        v.findViewById(R.id.animate_center).setOnClickListener(this);
        v.findViewById(R.id.pull_refresh).setOnClickListener(this);
        return blm;
    }
    @Override
    public void onCompanionViewAddFinished(CenterLayoutManager container) {
        ListView listview = container.getListView();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            list.add("测试的" + i);
        }
        listview.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list));
    }

    public static void startMe(Activity from){
        Intent intent = new Intent();
        intent.setClass(from,SimplePullRefreshActivity.class);
        from.startActivity(intent);
    }

    @Override
    public void onRefresh() {
        EventBusProxy.dispatcherOnMainThreadDelay(new Runnable() {
            @Override
            public void run() {
                stopRefresh(true);
                setElementViewVisible(ElementView.CenterMaskView,true);
            }
        }, 2000);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.show_top_view:
                setElementViewVisible(ElementView.CenterMaskView,false);
                break;
            case R.id.animate_header:
                animateY(ElementView.HeaderView, Easing.EasingAnimation.EaseOutBounce, 500);
                break;
            case R.id.animate_top:
                animateY(ElementView.TopView, Easing.EasingAnimation.EaseOutBounce, 500);
                break;
            case R.id.animate_center:
                animateX(ElementView.CenterView, Easing.EasingAnimation.EaseOutBounce, 1500);
                break;
            case R.id.animate_bottom:
                animateY(ElementView.BottomView, Easing.EasingAnimation.EaseInQuart, 500);
                break;
            case R.id.pull_refresh:
                finish();
                break;
        }
    }
}
