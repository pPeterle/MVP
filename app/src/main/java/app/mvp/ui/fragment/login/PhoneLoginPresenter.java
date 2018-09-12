package app.mvp.ui.fragment.login;

import app.mvp.helper.ValidatorHelper;

public class PhoneLoginPresenter implements PhoneLoginContract.PhoneLoginPresenter {
    private PhoneLoginContract.PhoneLoginView view;

    PhoneLoginPresenter(PhoneLoginContract.PhoneLoginView view) {
        this.view = view;
    }

    @Override
    public void callPasswordLogin(String phone) {
        if (contentFieldsIsValid(phone)) {
            view.openPasswordLogin();
        }
    }

    private boolean contentFieldsIsValid(String phone) {
        if (phoneIsEmpty(phone)) {
            view.phoneIsEmpty();
            return false;
        }

        if (notIsPhone(phone)) {
            view.notIsPhone();
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
