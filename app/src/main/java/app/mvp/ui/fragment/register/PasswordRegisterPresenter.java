package app.mvp.ui.fragment.register;

import app.mvp.R;
import app.mvp.helper.ValidatorHelper;

public class PasswordRegisterPresenter implements PasswordRegisterContract.PasswordRegisterPresenter {
    private PasswordRegisterContract.PasswordRegisterView view;

    PasswordRegisterPresenter(PasswordRegisterContract.PasswordRegisterView view) {
        this.view = view;
    }

    @Override
    public void callPasswordConfirmRegister(String password) {
        if (contentFieldsIsValid(password)) {
            view.openPasswordConfirmRegister(password);
        }
    }

    private boolean contentFieldsIsValid(String password) {
        if (passwordIsEmpty(password)) {
            view.error(R.string.empty_password);
            return false;
        }

        if (notIsPassword(password)) {
            view.error(R.string.invalid_password);
            return false;
        }
        return true;
    }

    private boolean passwordIsEmpty(String password) {
        return ValidatorHelper.isEmpty(password);
    }

    private boolean notIsPassword(String password) {
        return !ValidatorHelper.isPassword(password);
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }
}
