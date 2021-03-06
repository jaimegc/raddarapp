package com.raddarapp.presentation.android.view.adapter;

import android.support.design.internal.NavigationMenu;
import android.view.MenuItem;

import com.raddarapp.presentation.android.view.menu.FabSpeedDialInfluencersSettings;

public class SimpleMenuInfluencersListenerAdapter implements FabSpeedDialInfluencersSettings.MenuListener {

    @Override
    public boolean onPrepareMenu(NavigationMenu navigationMenu) {

        return true;
    }

    @Override
    public boolean onMenuItemSelected(MenuItem menuItem) {
        return false;
    }

    @Override
    public void onMenuClosed() {
    }
}
