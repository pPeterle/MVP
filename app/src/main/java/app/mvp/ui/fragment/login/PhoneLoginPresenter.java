package app.mvp.ui.fragment.login;

import app.mvp.helper.ValidatorHelper;

public class PhoneLoginPresenter implements PhoneLoginContract.PhoneLoginPresenter {
    private PhoneLoginContract.PhoneLoginView view;

    PhoneLoginPresenter(PhoneLoginContract.PhoneLoginView view) {
        this.view = view;
    }

    @Override
    public void passwordFragment(String phone) {
        if (contentFieldsIsValid(phone)) {
            if (view != null) {
                view.abrePasswordFragment();
            }
        }
    }

    private boolean contentFieldsIsValid(String phone) {
        if (phoneIsEmpty(phone)) {
            if (view != null) {
                view.phoneIsEmpty();
            }
            return false;
        }

        if (notIsPhone(phone)) {
            if (view != null) {
                view.notIsPhone();
            }
            return false;
        }
        return true;
    }

    private boolean phoneIsEmpty(String phone) {
        return ValidatorHelper.isEmpty(phone);
    }

    private boolean notIsPhone(String phone) {
        return !ValidatorHelper.isPhone(phone);
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }
}
