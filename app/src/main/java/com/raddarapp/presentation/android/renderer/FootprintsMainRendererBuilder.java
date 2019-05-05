package com.raddarapp.presentation.android.renderer;


import com.raddarapp.presentation.android.view.renderer.RendererBuilderWithIndex;
import com.raddarapp.presentation.android.view.renderer.RendererWithIndex;
import com.raddarapp.presentation.general.presenter.FootprintsMainPresenter;
import com.raddarapp.presentation.general.viewmodel.FootprintInsigniaMainViewModel;
import com.raddarapp.presentation.general.viewmodel.FootprintMainViewModel;
import com.raddarapp.presentation.general.viewmodel.contract.FootprintMainViewModelContract;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FootprintsMainRendererBuilder extends RendererBuilderWithIndex<FootprintMainViewModelContract> {
    private Map<Class, Class> rendererMapping = new HashMap<>();

    public FootprintsMainRendererBuilder(FootprintsMainPresenter presenter) {
        List<RendererWithIndex<FootprintMainViewModelContract>> prototypes = new LinkedList<>();
        prototypes.add(new FootprintsMainRenderer(presenter));
        rendererMapping.put(FootprintMainViewModel.class, FootprintsMainRenderer.class);
        prototypes.add(new FootprintsInsigniaMainRenderer(presenter));
        rendererMapping.put(FootprintInsigniaMainViewModel.class, FootprintsInsigniaMainRenderer.class);
        prototypes.add(new LoadMoreFootprintsMainRenderer());
        setPrototypes(prototypes);
    }

    @Override
    protected Class getPrototypeClass(FootprintMainViewModelContract content) {
        if (content != null) {
            return rendererMapping.get(content.getClass());
        } else {
            return LoadMoreFootprintsMainRenderer.class;
        }
    }
}