package app.mvp.ui.fragment.register;

import app.mvp.R;
import app.mvp.helper.ValidatorHelper;

public class EmailRegisterPresenter implements EmailRegisterContract.EmailRegisterPresenter {
    private EmailRegisterContract.EmailRegisterView view;

    EmailRegisterPresenter(EmailRegisterContract.EmailRegisterView view) {
        this.view = view;
    }

    @Override
    public void callPhoneRegister(String email) {
        if (contentFieldsIsValid(email)) {
            view.openPhoneRegister(email);
        }
    }

    private boolean contentFieldsIsValid(String email) {
        if (emailIsEmpty(email)) {
            view.error(R.string.empty_email);
            return false;
        }

        if (notIsEmail(email)) {
            view.error(R.string.invalid_email);
            return false;
        }
        return true;
    }

    private boolean emailIsEmpty(String email) {
        return ValidatorHelper.isEmpty(email);
    }

    private boolean notIsEmail(String email) {
        return !ValidatorHelper.isEmail(email);
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }
}
