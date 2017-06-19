package com.xn.uiframe.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.xn.uiframe.interfaces.IContainerManager;
import com.xn.uiframe.layout.CenterLayoutManager;
import com.xn.uiframe.layout.FullScreenLayoutManager;
import com.xn.uiframe.layout.TopLayoutManager;

public class SimplePullRefreshActivity extends BasicSimpleActivity {

    private SimpleFragment mHomeFragment = new SimpleFragment();
    private SimpleFragment mSetFragment = new SimpleFragment();
    private SimpleFragment mAccountFragment = new SimpleFragment();

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
                changeUIFragment(mAccountFragment);
                break;
            case 2:
                changeUIFragment(mSetFragment);
                break;
            default:
                changeUIFragment(mHomeFragment);
        }
    }

    @Override
    public void onAllViewConstructed() {

        Bundle bundleHome = new Bundle();
        bundleHome.putString("content", "HomeContent");
        mHomeFragment.setArguments(bundleHome);

        Bundle accountBD = new Bundle();
        accountBD.putString("content", "AccountContent");
        mHomeFragment.setArguments(accountBD);

        Bundle setBD = new Bundle();
        setBD.putString("content", "SetContent");
        mHomeFragment.setArguments(setBD);

        addUIFrameFragment(mHomeFragment);
    }
}
