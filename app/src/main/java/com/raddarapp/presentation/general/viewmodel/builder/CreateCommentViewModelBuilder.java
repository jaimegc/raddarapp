package com.raddarapp.presentation.general.viewmodel.builder;

import com.raddarapp.presentation.general.viewmodel.CreateCommentViewModel;

public class CreateCommentViewModelBuilder {
    private String key;
    private String body;
    private String creationMoment;

    public CreateCommentViewModelBuilder() {}

    public CreateCommentViewModel build() {
        return new CreateCommentViewModel(key, body, creationMoment);
    }

    public CreateCommentViewModelBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public CreateCommentViewModelBuilder withBody(String body) {
        this.body = body;
        return this;
    }

    public CreateCommentViewModelBuilder withCreationMoment(String creationMoment) {
        this.creationMoment = creationMoment;
        return this;
    }

}
