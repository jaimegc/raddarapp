package com.raddarapp.presentation.android.renderer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raddarapp.R;
import com.raddarapp.presentation.android.view.renderer.base.BaseRendererWithIndex;
import com.raddarapp.presentation.general.viewmodel.contract.FootprintRankingViewModelContract;

public class LoadMoreFootprintsRankingRenderer extends BaseRendererWithIndex<FootprintRankingViewModelContract> {

  @Override
  protected View inflate(LayoutInflater inflater, ViewGroup parent) {
    View view = inflater.inflate(R.layout.loading_view_normal_white_transparent, parent, false);

    return view;
  }
}
