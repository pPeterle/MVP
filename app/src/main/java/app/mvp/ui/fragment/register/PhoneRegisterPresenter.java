package app.mvp.ui.fragment.register;

import app.mvp.helper.ValidatorHelper;

public class PhoneRegisterPresenter implements PhoneRegisterContract.PhoneRegisterPresenter {
    private PhoneRegisterContract.PhoneRegisterView view;

    PhoneRegisterPresenter(PhoneRegisterContract.PhoneRegisterView view) {
        this.view = view;
    }

    @Override
    public void callPasswordRegister(String phone) {
        if (contentFieldsIsValid(phone)) {
            view.openPasswordRegister(phone);
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
