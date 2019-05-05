package com.raddarapp.presentation.android.renderer;

import android.content.Context;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.presentation.android.activity.MyFootprintsAllActivity;
import com.raddarapp.presentation.android.utils.FontUtils;
import com.raddarapp.presentation.android.view.renderer.base.BaseRendererWithIndex;
import com.raddarapp.presentation.general.presenter.contract.MyFootprintsPresenterContract;
import com.raddarapp.presentation.general.viewmodel.contract.MyFootprintViewModelContract;

import butterknife.OnClick;

public class LoadShowAllMyFootprintsRenderer extends BaseRendererWithIndex<MyFootprintViewModelContract> {

  private static final String FONT_NAME = BuildConfig.BASE_FONT_NAME;

  private Context context;
  private final MyFootprintsPresenterContract presenter;

  public LoadShowAllMyFootprintsRenderer(Context context, MyFootprintsPresenterContract presenter) {
    this.context = context;
    this.presenter = presenter;
  }

  @Override
  protected View inflate(LayoutInflater inflater, ViewGroup parent) {
    View view = inflater.inflate(R.layout.item_card_my_footprint_more, parent, false);

    TextView textMyFootprintsMoreView = (TextView) view.findViewById(R.id.text_my_footprints_more);

    FontUtils fontUtils = new FontUtils();
    fontUtils.applyFont(context, FONT_NAME, textMyFootprintsMoreView);

    return view;
  }

  @OnClick(R.id.layer_my_footprints_more)
  public void onMyFootprintsMoreClicked() {
    presenter.onMyFootprintsMoreClicked();
  }
}
