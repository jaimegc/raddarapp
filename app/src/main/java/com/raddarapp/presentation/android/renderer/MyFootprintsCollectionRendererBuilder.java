package com.raddarapp.presentation.android.renderer;


import android.app.Activity;

import com.raddarapp.presentation.android.view.renderer.RendererBuilderWithIndex;
import com.raddarapp.presentation.android.view.renderer.RendererWithIndex;
import com.raddarapp.presentation.general.presenter.MyFootprintsCollectionPresenter;
import com.raddarapp.presentation.general.viewmodel.MyFootprintCollectionViewModel;
import com.raddarapp.presentation.general.viewmodel.contract.MyFootprintCollectionViewModelContract;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MyFootprintsCollectionRendererBuilder extends RendererBuilderWithIndex<MyFootprintCollectionViewModelContract> {
    private Map<Class, Class> rendererMapping = new HashMap<>();

    public MyFootprintsCollectionRendererBuilder(Activity activity, MyFootprintsCollectionPresenter presenter) {
        List<RendererWithIndex<MyFootprintCollectionViewModelContract>> prototypes = new LinkedList<>();
        prototypes.add(new MyFootprintsCollectionRenderer(activity, presenter));
        rendererMapping.put(MyFootprintCollectionViewModel.class, MyFootprintsCollectionRenderer.class);
        prototypes.add(new LoadMoreHideMyFootprintsCollectionRenderer());
        setPrototypes(prototypes);
    }

    @Override
    protected Class getPrototypeClass(MyFootprintCollectionViewModelContract content) {
        if (content != null) {
            return rendererMapping.get(content.getClass());
        } else {
            return LoadMoreHideMyFootprintsCollectionRenderer.class;
        }
    }
}
