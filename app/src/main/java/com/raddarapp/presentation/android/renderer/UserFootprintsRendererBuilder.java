package com.raddarapp.presentation.android.renderer;


import android.app.Activity;

import com.raddarapp.presentation.android.utils.PreferencesUtils;
import com.raddarapp.presentation.android.view.renderer.RendererBuilderWithIndex;
import com.raddarapp.presentation.android.view.renderer.RendererWithIndex;
import com.raddarapp.presentation.general.presenter.contract.UserFootprintsPresenterContract;
import com.raddarapp.presentation.general.viewmodel.UserFootprintEmptyViewModel;
import com.raddarapp.presentation.general.viewmodel.UserFootprintViewModel;
import com.raddarapp.presentation.general.viewmodel.contract.UserFootprintViewModelContract;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class UserFootprintsRendererBuilder extends RendererBuilderWithIndex<UserFootprintViewModelContract> {
    private Map<Class, Class> rendererMapping = new HashMap<>();
    private boolean allUserFootprints;
    private PreferencesUtils preferencesUtils;

    public UserFootprintsRendererBuilder(Activity activity, UserFootprintsPresenterContract presenter,
        boolean allUserFootprints, int indexScreen, String userKey, String footprintKey) {

        preferencesUtils = new PreferencesUtils(activity);
        List<RendererWithIndex<UserFootprintViewModelContract>> prototypes = new LinkedList<>();
        this.allUserFootprints = allUserFootprints;
        prototypes.add(new UserFootprintsRenderer(activity, presenter, preferencesUtils.getMyUserLevel()));
        rendererMapping.put(UserFootprintViewModel.class, UserFootprintsRenderer.class);
        prototypes.add(new UserFootprintsEmptyRenderer(activity));
        rendererMapping.put(UserFootprintEmptyViewModel.class, UserFootprintsEmptyRenderer.class);
        prototypes.add(new LoadMoreHideUserFootprintsRenderer());
        prototypes.add(new LoadShowAllUserFootprintsRenderer(activity, indexScreen, userKey, footprintKey));
        setPrototypes(prototypes);
    }

    @Override
    protected Class getPrototypeClass(UserFootprintViewModelContract content) {
        if (content != null) {
            return rendererMapping.get(content.getClass());
        } else {
            return allUserFootprints ? LoadMoreHideUserFootprintsRenderer.class : LoadShowAllUserFootprintsRenderer.class;
        }
    }
}
