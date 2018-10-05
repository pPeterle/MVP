package app.mvp.ui.fragment.register;

import app.mvp.R;
import app.mvp.helper.ValidatorHelper;

public class NicknameRegisterPresenter implements NicknameRegisterContract.NicknameRegisterPresenter {
    private NicknameRegisterContract.NicknameRegisterView view;

    NicknameRegisterPresenter(NicknameRegisterContract.NicknameRegisterView view) {
        this.view = view;
    }

    @Override
    public void callEmailRegister(String nickname) {
        if (contentFieldsIsValid(nickname)) {
            view.openEmailRegister(nickname);
        }
    }

    private boolean contentFieldsIsValid(String nickname) {
        if (nicknameIsEmpty(nickname)) {
            view.error(R.string.empty_nickname);
            return false;
        }
        return true;
    }

    private boolean nicknameIsEmpty(String nickname) {
        return ValidatorHelper.isEmpty(nickname);
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }
}
