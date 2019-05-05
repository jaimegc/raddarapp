package com.raddarapp.presentation.android.renderer;


import android.content.Context;

import com.raddarapp.presentation.android.view.renderer.RendererBuilderWithIndex;
import com.raddarapp.presentation.android.view.renderer.RendererWithIndex;
import com.raddarapp.presentation.general.presenter.contract.MyUsersRankingPresenterContract;
import com.raddarapp.presentation.general.viewmodel.MyLeaderKingRankingViewModel;
import com.raddarapp.presentation.general.viewmodel.MyLeaderRankingViewModel;
import com.raddarapp.presentation.general.viewmodel.MyUserRankingViewModel;
import com.raddarapp.presentation.general.viewmodel.contract.MyUserRankingViewModelContract;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MyUsersRankingRendererBuilder extends RendererBuilderWithIndex<MyUserRankingViewModelContract> {
    private Map<Class, Class> rendererMapping = new HashMap<>();
    private boolean allUserRankings;

    public MyUsersRankingRendererBuilder(MyUsersRankingPresenterContract presenter, boolean allUserRankings, Context context) {
        List<RendererWithIndex<MyUserRankingViewModelContract>> prototypes = new LinkedList<>();
        this.allUserRankings = allUserRankings;
        prototypes.add(new MyUsersRankingRenderer(presenter));
        rendererMapping.put(MyUserRankingViewModel.class, MyUsersRankingRenderer.class);
        prototypes.add(new LoadMoreMyUsersRankingRenderer(context));
        prototypes.add(new LoadShowAllUsersRankingRenderer(context));
        prototypes.add(new MyLeaderRankingRenderer(presenter));
        rendererMapping.put(MyLeaderRankingViewModel.class, MyLeaderRankingRenderer.class);
        prototypes.add(new MyLeaderKingRankingRenderer(presenter));
        rendererMapping.put(MyLeaderKingRankingViewModel.class, MyLeaderKingRankingRenderer.class);
        setPrototypes(prototypes);
    }

    @Override
    protected Class getPrototypeClass(MyUserRankingViewModelContract content) {
        if (content != null) {
            return rendererMapping.get(content.getClass());
        } else {
            return allUserRankings ? LoadMoreMyUsersRankingRenderer.class : LoadShowAllUsersRankingRenderer.class;
        }
    }
}
