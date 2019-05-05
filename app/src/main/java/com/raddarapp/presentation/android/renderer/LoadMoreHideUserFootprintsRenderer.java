package com.raddarapp.presentation.android.renderer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raddarapp.R;
import com.raddarapp.presentation.android.view.renderer.base.BaseRendererWithIndex;
import com.raddarapp.presentation.general.viewmodel.contract.UserFootprintViewModelContract;

public class LoadMoreHideUserFootprintsRenderer extends BaseRendererWithIndex<UserFootprintViewModelContract> {

  @Override
  protected View inflate(LayoutInflater inflater, ViewGroup parent) {
    View view = inflater.inflate(R.layout.loading_view_hide, parent, false);

    return view;
  }
}
