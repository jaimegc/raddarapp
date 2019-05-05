package com.raddarapp.presentation.general.viewmodel;

import com.raddarapp.presentation.general.viewmodel.contract.CreateCommentViewModelContract;

public class CreateCommentViewModel implements CreateCommentViewModelContract {

    private final String key;
    private final String body;
    private final String creationMoment;

    public CreateCommentViewModel(String key, String body, String creationMoment) {
        this.key = key;
        this.body = body;
        this.creationMoment = creationMoment;
    }

    public String getKey() {
        return key;
    }

    public String getBody() {
        return body;
    }

    public String getCreationMoment() {
        return creationMoment;
    }

}
