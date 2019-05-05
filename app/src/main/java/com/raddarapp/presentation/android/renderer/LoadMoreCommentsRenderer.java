package com.raddarapp.presentation.android.renderer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raddarapp.R;
import com.raddarapp.presentation.android.renderer.base.BaseRenderer;
import com.raddarapp.presentation.general.viewmodel.contract.CommentViewModelContract;

public class LoadMoreCommentsRenderer extends BaseRenderer<CommentViewModelContract> {

  @Override
  protected View inflate(LayoutInflater inflater, ViewGroup parent) {
    return inflater.inflate(R.layout.loading_view_normal_blue, parent, false);
  }
}
