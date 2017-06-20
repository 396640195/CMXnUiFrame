package com.xn.uiframe.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.xn.uiframe.interfaces.IContainerManager;
import com.xn.uiframe.layout.CenterLayoutManager;
import com.xn.uiframe.layout.FullScreenLayoutManager;
import com.xn.uiframe.layout.TopLayoutManager;

public class SimplePullRefreshActivity extends BasicSimpleActivity {

    private SimpleFragment mHomeFragment;
    private SimpleFragment mSetFragment;
    private SimpleFragment mAccountFragment;

    public static void startMe(Activity from) {
        Intent intent = new Intent();
        intent.setClass(from, SimplePullRefreshActivity.class);
        from.startActivity(intent);
    }

    @Override
    public FullScreenLayoutManager addDialogView(IContainerManager container) {
        return null;
    }

    @Override
    public TopLayoutManager addTopView(IContainerManager container) {
        return null;
    }

    @Override
    public CenterLayoutManager addCenterView(IContainerManager container) {
        CenterLayoutManager clt = CenterLayoutManager.buildGeneralLayout(container, R.layout.layout_center_fragment);
        return clt;
    }

    @Override
    public void onTabSelected(int index) {
        switch (index) {
            case 1:
                changeUIFragment(mHomeFragment);
                break;
            case 2:
                if (mAccountFragment == null) {
                    Bundle accountBD = new Bundle();
                    accountBD.putString("content", "From帐号");
                    mAccountFragment = new SimpleFragment();
                    mAccountFragment.setArguments(accountBD);
                    addUIFrameFragment(mAccountFragment);
                } else {
                    changeUIFragment(mAccountFragment);
                }
                break;
            default:

                if (mSetFragment == null) {
                    Bundle setBD = new Bundle();
                    setBD.putString("content", "From设置");
                    mSetFragment = new SimpleFragment();
                    mSetFragment.setArguments(setBD);
                    addUIFrameFragment(mSetFragment);
                } else {
                    changeUIFragment(mSetFragment);
                }


        }
    }

    @Override
    public void onAllViewConstructed() {

        mHomeFragment = new SimpleFragment();
        Bundle bundleHome = new Bundle();
        bundleHome.putString("content", "From 首页");
        mHomeFragment.setArguments(bundleHome);
        addUIFrameFragment(mHomeFragment);

        setHeaderCenterText("下拉刷新");
    }

}
