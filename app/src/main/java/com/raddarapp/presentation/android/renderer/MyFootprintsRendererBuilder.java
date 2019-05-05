package com.raddarapp.presentation.android.renderer;


import android.app.Activity;
import android.content.Context;

import com.raddarapp.presentation.general.presenter.contract.MyFootprintsPresenterContract;
import com.raddarapp.presentation.android.view.renderer.RendererBuilderWithIndex;
import com.raddarapp.presentation.android.view.renderer.RendererWithIndex;
import com.raddarapp.presentation.general.viewmodel.MyFootprintEmptyViewModel;
import com.raddarapp.presentation.general.viewmodel.MyFootprintViewModel;
import com.raddarapp.presentation.general.viewmodel.contract.MyFootprintViewModelContract;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MyFootprintsRendererBuilder extends RendererBuilderWithIndex<MyFootprintViewModelContract> {
    private Map<Class, Class> rendererMapping = new HashMap<>();
    private boolean allMyFootprints;

    public MyFootprintsRendererBuilder(Activity activity, MyFootprintsPresenterContract presenter, boolean allMyFootprints, Context context) {
        List<RendererWithIndex<MyFootprintViewModelContract>> prototypes = new LinkedList<>();
        this.allMyFootprints = allMyFootprints;
        prototypes.add(new MyFootprintsRenderer(activity, presenter));
        rendererMapping.put(MyFootprintViewModel.class, MyFootprintsRenderer.class);
        prototypes.add(new MyFootprintsEmptyRenderer(context));
        rendererMapping.put(MyFootprintEmptyViewModel.class, MyFootprintsEmptyRenderer.class);
        prototypes.add(new LoadMoreHideMyFootprintsRenderer());
        prototypes.add(new LoadShowAllMyFootprintsRenderer(context, presenter));
        setPrototypes(prototypes);
    }

    @Override
    protected Class getPrototypeClass(MyFootprintViewModelContract content) {
        if (content != null) {
            return rendererMapping.get(content.getClass());
        } else {
            return allMyFootprints ? LoadMoreHideMyFootprintsRenderer.class : LoadShowAllMyFootprintsRenderer.class;
        }
    }
}
