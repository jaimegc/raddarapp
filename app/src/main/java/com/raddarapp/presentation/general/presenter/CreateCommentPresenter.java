package com.raddarapp.presentation.general.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.karumi.rosie.view.RosiePresenter;
import com.raddarapp.domain.model.builder.CreateCommentBuilder;
import com.raddarapp.domain.model.mapper.CreateCommentToCommentModelMapper;
import com.raddarapp.domain.usecase.CreateComment;
import com.raddarapp.presentation.android.error.local.ErrorLocalCode;
import com.raddarapp.presentation.general.presenter.base.BasePresenterNormal;
import com.raddarapp.presentation.general.validation.ValidationCreateComment;
import com.raddarapp.presentation.general.validation.view.ValidationCreateCommentView;
import com.raddarapp.presentation.general.viewmodel.CreateCommentViewModel;
import com.raddarapp.presentation.general.viewmodel.mapper.CreateCommentToCreateCommentViewModelMapper;

import javax.inject.Inject;

public class CreateCommentPresenter extends RosiePresenter<CreateCommentPresenter.View> {

    private final CreateCommentToCreateCommentViewModelMapper mapper;
    private final CreateComment createCommentUseCase;
    private final ValidationCreateComment validationCreateComment;
    private CommentsFootprintDetailsPresenter commentsFootprintsDetailsPresenter;
    private CommentsPresenter commentsPresenter;
    private CreateCommentToCommentModelMapper mapperForCache;

    @Inject
    public CreateCommentPresenter(UseCaseHandler useCaseHandler, CreateCommentToCreateCommentViewModelMapper mapper,
            CreateCommentToCommentModelMapper mapperForCache, CreateComment createCommentUseCase,
            ValidationCreateComment validationCreateComment) {
        super(useCaseHandler);
        this.mapper = mapper;
        this.createCommentUseCase = createCommentUseCase;
        this.mapperForCache = mapperForCache;
        this.validationCreateComment = validationCreateComment;
    }

    public void setCommentsPresenter(CommentsFootprintDetailsPresenter commentsFootprintsDetailsPresenter) {
        this.commentsFootprintsDetailsPresenter = commentsFootprintsDetailsPresenter;
    }

    public void setCommentsPresenter(CommentsPresenter commentsPresenter) {
        this.commentsPresenter = commentsPresenter;
    }

    private void createComment(String footprintKey, com.raddarapp.domain.model.CreateComment createComment, boolean isFromFootprintDetails) {
        getView().showCreateCommentLoading();

        // Here because we don't need the id (if not, in onSuccess())
        if (isFromFootprintDetails) {
            commentsFootprintsDetailsPresenter.addCommentInCache(mapperForCache.map(createComment));
        } else {
            commentsPresenter.addCommentInCache(mapperForCache.map(createComment));
        }

        createUseCaseCall(createCommentUseCase)
                .args(footprintKey, createComment)
                .useCaseName(CreateComment.USE_CASE_CREATE_COMMENT)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void createComment(com.raddarapp.domain.model.CreateComment createdComment) {
                        if (createdComment.getKey() != null && !createdComment.getKey().isEmpty()) {
                            showCreatedComment(createdComment);
                        } else {
                            showError();
                        }
                    }
                })
                .onError(error -> {
                    showError();
                    return false;
                }).execute();
    }

    private void showCreatedComment(com.raddarapp.domain.model.CreateComment createdComment) {
        CreateCommentViewModel createdCommentViewModel = mapper.map(createdComment);

        try {
            getView().showCreatedComment(createdCommentViewModel);
            getView().hideCreateCommentLoading();
        } catch (Exception e) {}
    }

    public void onCreateCommentClicked(String footprintMainKey, String body, boolean isFromFootprintDetails) {
        getView().showCreateCommentLoading();

        // FIXME: Check values
        com.raddarapp.domain.model.CreateComment createComment = new CreateCommentBuilder()
                .withBody(body)
                .build();

        createComment(footprintMainKey, createComment, isFromFootprintDetails);
    }

    public boolean isValidComment(String comment) {
        ErrorLocalCode code = validationCreateComment.validateCreateComment(comment);

        if (code == ErrorLocalCode.SUCCESS) {
            return true;
        } else {
            showErrorLocal(code);
            return false;
        }
    }

    private void showError() {
        try {
            getView().hideCreateCommentLoading();
            getView().showCreateCommentError();
        } catch (Exception e) {}
    }

    private void showErrorLocal(ErrorLocalCode code) {
        switch (code) {
            case EMPTY_COMMENT:
                getView().showErrorLocalCommentEmpty();
                break;
        }

        getView().hideCreateCommentLoading();
    }

    public interface View extends BasePresenterNormal.View, ValidationCreateCommentView {
        void showCreatedComment(CreateCommentViewModel createCommentViewModel);

        void showCreateCommentError();

        void showCreateCommentLoading();

        void hideCreateCommentLoading();
    }
}
