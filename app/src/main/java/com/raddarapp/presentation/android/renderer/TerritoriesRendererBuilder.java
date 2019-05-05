package com.raddarapp.presentation.android.renderer;


import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;
import com.raddarapp.presentation.general.presenter.TerritoriesPresenter;
import com.raddarapp.presentation.general.viewmodel.TerritoryViewModel;
import com.raddarapp.presentation.general.viewmodel.contract.TerritoryViewModelContract;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TerritoriesRendererBuilder extends RendererBuilder<TerritoryViewModelContract> {
    private Map<Class, Class> rendererMapping = new HashMap<>();

    public TerritoriesRendererBuilder(TerritoriesPresenter presenter) {
        List<Renderer<TerritoryViewModelContract>> prototypes = new LinkedList<>();
        prototypes.add(new TerritoriesRenderer(presenter));
        rendererMapping.put(TerritoryViewModel.class, TerritoriesRenderer.class);
        prototypes.add(new LoadMoreTerritoriesRenderer());
        setPrototypes(prototypes);
    }

    @Override
    protected Class getPrototypeClass(TerritoryViewModelContract content) {
        if (content != null) {
            return rendererMapping.get(content.getClass());
        } else {
            return LoadMoreTerritoriesRenderer.class;
        }
    }
}
