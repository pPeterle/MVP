package app.mvp.ui.fragment.register;

import app.mvp.R;
import app.mvp.helper.ValidatorHelper;

public class NameRegisterPresenter implements NameRegisterContract.NameRegisterPresenter {
    private NameRegisterContract.NameRegisterView view;

    NameRegisterPresenter(NameRegisterContract.NameRegisterView view) {
        this.view = view;
    }

    @Override
    public void callNicknameRegister(String name) {
        if (contentFieldsIsValid(name)) {
            view.openNicknameRegister(name);
        }
    }

    private boolean contentFieldsIsValid(String name) {
        if (nameIsEmpty(name)) {
            view.error(R.string.empty_name);
            return false;
        }

        if (notIsFullname(name)) {
            view.error(R.string.register_phrase_fullname);
            return false;
        }
        return true;
    }

    private boolean nameIsEmpty(String name) {
        return ValidatorHelper.isEmpty(name);
    }

    private boolean notIsFullname(String string) {
        return !string.contains(" ");
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }
}
