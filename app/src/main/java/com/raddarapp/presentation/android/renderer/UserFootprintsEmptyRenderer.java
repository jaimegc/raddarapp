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

import butterknife.OnClick;

public class UserFootprintsEmptyRenderer extends BaseRendererWithIndex<UserFootprintViewModelContract> {

    private static final String FONT_NAME = BuildConfig.BASE_FONT_NAME;

    private Context context;

    public UserFootprintsEmptyRenderer(Context context) {
        this.context = context;
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_card_user_footprint_empty, parent, false);

        TextView textUserFootprintsEmptyView = (TextView) view.findViewById(R.id.text_user_footprints_empty);

        FontUtils fontUtils = new FontUtils();
        fontUtils.applyFont(context, FONT_NAME, textUserFootprintsEmptyView);

        return view;
    }
}
