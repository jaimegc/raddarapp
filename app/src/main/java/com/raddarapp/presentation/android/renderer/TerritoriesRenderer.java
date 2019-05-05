package com.raddarapp.presentation.android.renderer;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pkmmte.view.CircularImageView;
import com.raddarapp.R;
import com.raddarapp.domain.model.enums.UserRelationshipType;
import com.raddarapp.presentation.android.renderer.base.BaseRenderer;
import com.raddarapp.presentation.general.presenter.TerritoriesPresenter;
import com.raddarapp.presentation.general.viewmodel.TerritoryViewModel;
import com.raddarapp.presentation.general.viewmodel.contract.TerritoryViewModelContract;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.OnClick;

public class TerritoriesRenderer extends BaseRenderer<TerritoryViewModelContract> {

    private final TerritoriesPresenter presenter;

    @BindView(R.id.name_parent) TextView nameParent;
    @BindView(R.id.area) TextView area;
    @BindView(R.id.total_footprints) TextView totalFootprints;
    @BindView(R.id.user_image) CircularImageView imageUser;
    @BindView(R.id.leadername) TextView leadername;
    @BindView(R.id.leader_raddar) TextView leaderRaddar;
    @BindView(R.id.leader_followers) TextView leaderFollowers;
    @BindView(R.id.territory_area) ImageView territoryArea;

    private static final String SPACE = " ";

    public TerritoriesRenderer(TerritoriesPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void render() {
        super.render();
        Context context = getRootView().getContext();
        TerritoryViewModel territoryViewModel = (TerritoryViewModel) getContent();

        int userRelationship = territoryViewModel.getLeaderRelationship();

        nameParent.setText(territoryViewModel.getName() + ", " + territoryViewModel.getParentName());
        area.setText(Html.fromHtml("&nbsp;" + territoryViewModel.getArea() + "&nbsp;" +
                context.getString(R.string.measure_square_meters_html)));
        totalFootprints.setText(SPACE + territoryViewModel.getTotalFootprints());
        leadername.setText(territoryViewModel.getLeadername());
        leaderRaddar.setText(SPACE + territoryViewModel.getLeaderRange() + SPACE + context.getString(R.string.raddar_distance_meters));
        leaderFollowers.setText(territoryViewModel.getLeaderFollowers());

        if (userRelationship == UserRelationshipType.UNKNOWN.getValue()) {
            imageUser.setBorderColor(ContextCompat.getColor(context, R.color.marker_unknown));
        } else if (userRelationship == UserRelationshipType.FOLLOWING.getValue()) {
            imageUser.setBorderColor(ContextCompat.getColor(context, R.color.marker_following));
        } else if (userRelationship == UserRelationshipType.FRIEND.getValue()) {
            imageUser.setBorderColor(ContextCompat.getColor(context, R.color.marker_friend));
        } else {
            imageUser.setBorderColor(ContextCompat.getColor(context, R.color.marker_default));
        }

        Picasso.with(context)
                .load(territoryViewModel.getLeaderImage())
                .placeholder(R.drawable.ic_raddar_normal)
                .fit()
                .centerCrop()
                .into(imageUser);
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.item_card_territory, parent, false);
    }

    @OnClick(R.id.territory_area)
    public void onTerritoryAreaClicked() {
        TerritoryViewModel territoryViewModel = (TerritoryViewModel) getContent();
        presenter.onTerritoryAreaClicked(territoryViewModel);
    }

    /*@OnClick(R.id.card_root)
    public void onItemClicked() {
        FootprintMainViewModel footprintMainViewModel = (FootprintMainViewModel) getContent();
        presenter.onMyFootprintClicked(footprintMainViewModel);
    }

    @OnClick(R.id.menu_like)
    public void onMenuLikeClicked() {
        int[] location = new int[2];
        menuLike.getLocationOnScreen(location);
        presenter.onMenuLikeClicked(location[0]);
    }

    @OnClick(R.id.menu_dislike)
    public void onMenuDislikeClicked() {
        int[] location = new int[2];
        menuDislike.getLocationOnScreen(location);
        presenter.onMenuDislikeClicked(location[0]);
    }

    @OnLongClick(R.id.card_root)
    public boolean onItemLongClicked() {
        FootprintMainViewModel footprintMainViewModel = (FootprintMainViewModel) getContent();
        presenter.onClickedFootprintAnimationSelected(footprintMainViewModel.getUserKey());

        return true;
    }*/
}
