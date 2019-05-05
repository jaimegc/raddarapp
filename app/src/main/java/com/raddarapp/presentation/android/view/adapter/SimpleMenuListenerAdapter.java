package com.raddarapp.presentation.android.view.adapter;

import android.support.design.internal.NavigationMenu;
import android.view.MenuItem;

import com.raddarapp.presentation.android.view.menu.FabSpeedDial;

public class SimpleMenuListenerAdapter implements FabSpeedDial.MenuListener {

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
