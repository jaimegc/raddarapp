package com.raddarapp.presentation.android.renderer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.presentation.android.activity.UserFootprintsAllActivity;
import com.raddarapp.presentation.android.utils.FontUtils;
import com.raddarapp.presentation.android.view.renderer.base.BaseRendererWithIndex;
import com.raddarapp.presentation.general.viewmodel.contract.UserFootprintViewModelContract;

import butterknife.OnClick;

public class LoadShowAllUserFootprintsRenderer extends BaseRendererWithIndex<UserFootprintViewModelContract> {

  private static final String FONT_NAME = BuildConfig.BASE_FONT_NAME;

  private Context context;
  private int indexScreen;
  private String userKey;
  private String footprintKey;

  public LoadShowAllUserFootprintsRenderer(Context context, int indexScreen, String userKey, String footprintKey) {
    this.context = context;
    this.indexScreen = indexScreen;
    this.userKey = userKey;
    this.footprintKey = footprintKey;
  }

  @Override
  protected View inflate(LayoutInflater inflater, ViewGroup parent) {
    View view = inflater.inflate(R.layout.item_card_user_footprint_more, parent, false);

    TextView textUserFootprintsMoreView = (TextView) view.findViewById(R.id.text_user_footprints_more);

    FontUtils fontUtils = new FontUtils();
    fontUtils.applyFont(context, FONT_NAME, textUserFootprintsMoreView);

    return view;
  }

  @OnClick(R.id.layer_user_footprints_more)
  public void onMyFootprintsMoreClicked() {
    UserFootprintsAllActivity.open(context, indexScreen, userKey, footprintKey);
  }
}
