package com.raddarapp.presentation.android.renderer;


import android.app.Activity;

import com.raddarapp.presentation.android.view.renderer.RendererBuilderWithIndex;
import com.raddarapp.presentation.android.view.renderer.RendererWithIndex;
import com.raddarapp.presentation.general.presenter.FootprintsRankingPresenter;
import com.raddarapp.presentation.general.viewmodel.FootprintRankingViewModel;
import com.raddarapp.presentation.general.viewmodel.contract.FootprintRankingViewModelContract;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FootprintsRankingRendererBuilder extends RendererBuilderWithIndex<FootprintRankingViewModelContract> {
    private Map<Class, Class> rendererMapping = new HashMap<>();

    public FootprintsRankingRendererBuilder(Activity activity, FootprintsRankingPresenter presenter, int height) {
        List<RendererWithIndex<FootprintRankingViewModelContract>> prototypes = new LinkedList<>();
        prototypes.add(new FootprintsRankingRenderer(activity, presenter, height));
        rendererMapping.put(FootprintRankingViewModel.class, FootprintsRankingRenderer.class);
        prototypes.add(new LoadMoreFootprintsRankingRenderer());
        setPrototypes(prototypes);
    }

    @Override
    protected Class getPrototypeClass(FootprintRankingViewModelContract content) {
        if (content != null) {
            return rendererMapping.get(content.getClass());
        } else {
            return LoadMoreFootprintsRankingRenderer.class;
        }
    }
}
