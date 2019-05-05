package com.raddarapp.presentation.android.fragment;


import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.EditText;

import com.karumi.rosie.view.Presenter;
import com.raddarapp.R;
import com.raddarapp.presentation.android.activity.PromoCodeActivity;
import com.raddarapp.presentation.android.fragment.base.BaseNormalFragment;
import com.raddarapp.presentation.android.utils.AnimationUtils;
import com.raddarapp.presentation.general.presenter.PromoCodePresenter;
import com.wang.avi.AVLoadingIndicatorView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;


public class PromoCodeFragment extends BaseNormalFragment implements PromoCodePresenter.View {

    @BindView(R.id.edit_promo_code) EditText editPromoCodeView;
    @BindView(R.id.loading) AVLoadingIndicatorView loadingView;
    @BindView(R.id.ok) FloatingActionButton okView;

    private AnimationUtils animationUtils = new AnimationUtils();
    private boolean dialogPromoCodeExchangedAlreadyShown = false;

    @Inject @Presenter
    PromoCodePresenter presenter;

    public static PromoCodeFragment newInstance() {
        PromoCodeFragment fragment = new PromoCodeFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_promo_code;
    }

    @Override
    protected void onPrepareFragment(View view) {
        super.onPrepareFragment(view);
    }

    @OnClick(R.id.back)
    public void onBackClicked() {
        getActivity().onBackPressed();
    }

    @OnClick(R.id.ok)
    public void onOkClicked() {
        presenter.promoCode(editPromoCodeView.getText().toString());
    }

    @Override
    public void hideLoading() {
        loadingView.setVisibility(View.GONE);
        enableFocusableView();
    }

    @Override
    public void showLoading() {
        animationUtils.alphaAnimationWithInitial(loadingView, 10, View.VISIBLE, View.VISIBLE, false);
        disableFocusableView();
    }

    @Override
    public void showErrorLocalPromoCodeEmpty() {
        initSnackbarError(R.string.error_local_empty_promo_code);
    }

    @Override
    public void showPromoCodeExchanged() {
        disablePromoCodeExchangedView();
    }

    @Override
    public void showPromoCodeExchangedError() {
        initSnackbar(R.string.error_promo_code);
    }

    private void enableFocusableView() {
        editPromoCodeView.setFocusable(true);
        editPromoCodeView.setFocusableInTouchMode(true);
        okView.setEnabled(true);
        okView.setClickable(true);
        editPromoCodeView.setClickable(true);
        editPromoCodeView.setFocusable(true);
    }

    private void disableFocusableView() {
        editPromoCodeView.setFocusable(false);
        editPromoCodeView.setFocusableInTouchMode(false);
        okView.setEnabled(false);
        okView.setClickable(false);
        editPromoCodeView.setClickable(false);
        editPromoCodeView.setFocusable(false);
    }

    private void disablePromoCodeExchangedView() {
        if (!dialogPromoCodeExchangedAlreadyShown) {
            dialogPromoCodeExchangedAlreadyShown = true;

            if (getActivity() != null) {
                ((PromoCodeActivity) getActivity()).setResultPromoCodeExchanged();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        hideLoading();
    }
}
