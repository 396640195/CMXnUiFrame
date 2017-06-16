package com.xn.uiframe.demo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dalong.refreshlayout.OnRefreshListener;
import com.xn.uiframe.widget.UIFrameRefreshViewLayout;

import java.util.ArrayList;
import java.util.List;

public class SimplePullRefreshActivity extends Activity {
    private UIFrameRefreshViewLayout refreshview;
    private ListView listview;

    public List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_center_pull_refresh);
        initData();
        refreshview = (UIFrameRefreshViewLayout) findViewById(R.id.refresh_layout);
        refreshview.setCanRefresh(false);
        refreshview.setCanLoad(false);
        listview = (ListView) findViewById(R.id.normal_list_view);
        listview.addHeaderView(LayoutInflater.from(this).inflate(R.layout.layout_list_header,null,false));
        refreshview.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.removeMessages(0);
                mHandler.sendEmptyMessageDelayed(0, 3000);
            }

            @Override
            public void onLoadMore() {
                mHandler.removeMessages(1);
                mHandler.sendEmptyMessageDelayed(1, 3000);
            }
        });

        listview.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list));
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    refreshview.stopRefresh(true);
                    break;
                case 1:
                    refreshview.stopLoadMore(true);
                    break;
            }
        }
    };

    public void initData() {
        for (int i = 0; i < 15; i++) {
            list.add("测试的" + i);
        }
    }
}
