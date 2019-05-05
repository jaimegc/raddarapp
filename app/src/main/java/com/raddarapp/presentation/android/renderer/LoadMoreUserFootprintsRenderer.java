package com.raddarapp.presentation.android.renderer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.presentation.android.utils.FontUtils;
import com.raddarapp.presentation.android.view.renderer.base.BaseRendererWithIndex;
import com.raddarapp.presentation.general.viewmodel.contract.UserFootprintViewModelContract;

public class LoadMoreUserFootprintsRenderer extends BaseRendererWithIndex<UserFootprintViewModelContract> {

  private static final String FONT_NAME = BuildConfig.BASE_FONT_NAME;

  private Context context;

  public LoadMoreUserFootprintsRenderer(Context context) {
    this.context = context;
  }

  @Override
  protected View inflate(LayoutInflater inflater, ViewGroup parent) {
    View view = inflater.inflate(R.layout.loading_view_normal_blue, parent, false);

    TextView textLoadingView = (TextView) view.findViewById(R.id.text_loading);

    FontUtils fontUtils = new FontUtils();
    fontUtils.applyFont(context, FONT_NAME, textLoadingView);

    return view;
  }
}
