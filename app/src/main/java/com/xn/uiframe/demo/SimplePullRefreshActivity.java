package com.xn.uiframe.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.xn.uiframe.interfaces.IContainerManager;
import com.xn.uiframe.layout.CenterLayoutManager;

public class SimplePullRefreshActivity extends BasicSimpleActivity {

    private SimpleFragment mHomeFragment;
    private ListFragment mSetFragment;
    private GeneralFragment mAccountFragment;

    public static void startMe(Activity from) {
        Intent intent = new Intent();
        intent.setClass(from, SimplePullRefreshActivity.class);
        from.startActivity(intent);
    }

    @Override
    public CenterLayoutManager addCenterView(IContainerManager container) {
        CenterLayoutManager clt = CenterLayoutManager.buildGeneralLayoutManager(container, R.layout.layout_center_fragment);
        return clt;
    }

    @Override
    public void onTabSelected(int index) {
        switch (index) {
            case 1:
                if (mHomeFragment == null) {
                    mHomeFragment = new SimpleFragment();
                    Bundle bundleHome = new Bundle();
                    bundleHome.putString("content", "From 首页");
                    mHomeFragment.setArguments(bundleHome);
                    addUIFrameFragment(mHomeFragment);
                }else {
                    changeUIFragment(mHomeFragment);
                }
                break;
            case 2:
                if (mAccountFragment == null) {
                    Bundle accountBD = new Bundle();
                    accountBD.putString("content", "From帐号");
                    mAccountFragment = new GeneralFragment();
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
                    mSetFragment = new ListFragment();
                    mSetFragment.setArguments(setBD);
                    addUIFrameFragment(mSetFragment);
                } else {
                    changeUIFragment(mSetFragment);
                }


        }
    }

    @Override
    public void onAllViewConstructed() {
        mTabViewHolder.setSelected(1);
        setHeaderCenterText("下拉刷新");

    }

}
