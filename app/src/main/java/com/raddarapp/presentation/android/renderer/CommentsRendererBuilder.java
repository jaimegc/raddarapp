package com.raddarapp.presentation.android.renderer;


import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;
import com.raddarapp.presentation.general.presenter.CommentsPresenter;
import com.raddarapp.presentation.general.viewmodel.CommentViewModel;
import com.raddarapp.presentation.general.viewmodel.contract.CommentViewModelContract;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CommentsRendererBuilder extends RendererBuilder<CommentViewModelContract> {
    private Map<Class, Class> rendererMapping = new HashMap<>();

    public CommentsRendererBuilder(CommentsPresenter presenter) {
        List<Renderer<CommentViewModelContract>> prototypes = new LinkedList<>();
        prototypes.add(new CommentsRenderer(presenter));
        rendererMapping.put(CommentViewModel.class, CommentsRenderer.class);
        prototypes.add(new LoadMoreCommentsRenderer());
        setPrototypes(prototypes);
    }

    @Override
    protected Class getPrototypeClass(CommentViewModelContract content) {
        if (content != null) {
            return rendererMapping.get(content.getClass());
        } else {
            return LoadMoreCommentsRenderer.class;
        }
    }
}
