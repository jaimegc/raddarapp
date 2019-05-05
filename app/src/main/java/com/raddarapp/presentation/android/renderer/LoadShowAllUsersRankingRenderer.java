package com.raddarapp.presentation.android.renderer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.presentation.android.activity.MyUsersRankingAllActivity;
import com.raddarapp.presentation.android.utils.FontUtils;
import com.raddarapp.presentation.android.view.renderer.base.BaseRendererWithIndex;
import com.raddarapp.presentation.general.viewmodel.contract.MyUserRankingViewModelContract;

import butterknife.OnClick;

public class LoadShowAllUsersRankingRenderer extends BaseRendererWithIndex<MyUserRankingViewModelContract> {

  private static final String FONT_NAME = BuildConfig.BASE_FONT_NAME;

  private Context context;

  public LoadShowAllUsersRankingRenderer(Context context) {
    this.context = context;
  }

  @Override
  protected View inflate(LayoutInflater inflater, ViewGroup parent) {
    View view = inflater.inflate(R.layout.item_user_ranking_more, parent, false);

    TextView textRankingMoreView = (TextView) view.findViewById(R.id.text_ranking_more);

    FontUtils fontUtils = new FontUtils();
    fontUtils.applyFont(context, FONT_NAME, textRankingMoreView);

    return view;
  }

  @OnClick(R.id.layer_more_ranking)
  public void onMoreMyFootprintsClicked() {
    MyUsersRankingAllActivity.openNewTask(context);
  }
}
