package com.raddarapp.presentation.android.renderer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raddarapp.R;
import com.raddarapp.presentation.android.view.renderer.base.BaseRendererWithIndex;
import com.raddarapp.presentation.general.viewmodel.contract.FootprintMainViewModelContract;

public class LoadMoreFootprintsMainRenderer extends BaseRendererWithIndex<FootprintMainViewModelContract> {

  @Override
  protected View inflate(LayoutInflater inflater, ViewGroup parent) {
    return inflater.inflate(R.layout.empty_view, parent, false);
  }
}
