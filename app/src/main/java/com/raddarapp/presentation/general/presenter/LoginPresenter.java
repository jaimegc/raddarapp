package com.raddarapp.presentation.general.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.view.RosiePresenter;

import javax.inject.Inject;

// Empty presenter
public class LoginPresenter extends RosiePresenter<LoginPresenter.View> {

    @Inject
    public LoginPresenter(UseCaseHandler useCaseHandler) {
        super(useCaseHandler);
    }

    public interface View extends RosiePresenter.View {
    }
}
