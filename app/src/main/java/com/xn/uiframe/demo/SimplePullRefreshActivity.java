package com.xn.uiframe.demo;

import android.app.Activity;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.xn.uiframe.interfaces.IContainerManager;
import com.xn.uiframe.layout.CenterLayoutManager;

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
}
