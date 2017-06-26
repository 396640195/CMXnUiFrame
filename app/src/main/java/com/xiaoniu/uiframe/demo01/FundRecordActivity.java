package com.xiaoniu.uiframe.demo01;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.xiaoniu.uiframe.R;
import com.xiaoniu.uiframe.demo.SimplePullRefreshActivity;
import com.xn.uiframe.ElementView;
import com.xn.uiframe.activity.UIFrameBasicActivity;
import com.xn.uiframe.interfaces.IContainerManager;
import com.xn.uiframe.layout.CenterLayoutManager;
import com.xn.uiframe.layout.CenterMaskLayoutManager;
import com.xn.uiframe.layout.HeaderLayoutManager;
import com.xn.uiframe.layout.TopLayoutManager;
import com.xn.uiframe.utils.EventBusProxy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xn068074 on 2017/6/26.
 */

public class FundRecordActivity extends UIFrameBasicActivity implements View.OnClickListener{

    public static void startMe(Activity from) {
        Intent intent = new Intent();
        intent.setClass(from, FundRecordActivity.class);
        from.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContainerBackgroundColor(R.color.background_color);
    }


    @Override
    public HeaderLayoutManager addHeaderView(IContainerManager container) {
        HeaderLayoutManager hlm = HeaderLayoutManager.buildLayoutManager(container);
        hlm.setHeaderLeftImage(R.mipmap.arrow_left_normal,0.8f);
        hlm.setHeaderCenterText("资金详情");
        hlm.setHeaderLineColor(R.color.background_color);
        return hlm;
    }

    @Override
    public TopLayoutManager addTopView(IContainerManager container) {
        TopLayoutManager tlm = TopLayoutManager.buildLayoutManager(container,R.layout.fund_layout_top);
        View v = tlm.getContentView();
        TextView left = (TextView)v.findViewById(R.id.left);
        TextView right = (TextView)v.findViewById(R.id.right);

        Drawable drawable = getResources().getDrawable(R.mipmap.arrow_down);
        drawable.setBounds(0,0,(int)(drawable.getIntrinsicWidth()*0.7f),(int)(drawable.getIntrinsicHeight()*0.7f));

        left.setCompoundDrawables(null,null,drawable,null);
        right.setCompoundDrawables(null,null,drawable,null);

        left.setOnClickListener(this);
        right.setOnClickListener(this);

        return tlm;
    }


    @Override
    public CenterLayoutManager addCenterView(IContainerManager container) {
        CenterLayoutManager clm = CenterLayoutManager.buildPullRefreshLayoutWithListView(container);
        return clm;
    }

    @Override
    public void onCompanionViewAddFinished(CenterLayoutManager container) {
        ListView listview = container.getListView();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            list.add("测试的" + i);
        }
        FundRecordAdapter frd = new FundRecordAdapter(this);
        frd.setDatas(list);
        listview.setAdapter(frd);

        listview.setDivider(getResources().getDrawable(R.drawable.translucency));
    }

    View type,time;
    @Override
    public CenterMaskLayoutManager addCenterMaskView(IContainerManager container) {
        CenterMaskLayoutManager clm = CenterMaskLayoutManager.buildLayoutManager(container);
        type = clm.addLayout(R.layout.fund_type_layout);
        return clm;
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

    @Override
    public void onAllViewConstructed() {
        ListView listView = (ListView)type.findViewById(R.id.type_list);

        List<String> list = new ArrayList<>();
        list.add("全部类型");
        list.add("充值");
        list.add("提现");
        list.add("奖励");
        FundTypeAdapter adapter= new FundTypeAdapter(this);
        adapter.setDatas(list);
        listView.setAdapter(adapter);

        type.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == type){
            type.setVisibility(View.GONE);
        }else if(v.getId() == R.id.left){
            type.setVisibility(View.VISIBLE);
            animateY(ElementView.CenterMaskView,500);
        }else if(v.getId() == R.id.right){

        }
    }

    @Override
    public void onLeftHeaderClicked() {
        finish();
    }
}
