package app.mvp.ui.fragment.login;

import app.mvp.model.User;

public class PasswordLoginModel implements PasswordLoginContract.PasswordLoginModel {

    @Override
    public void gravar(User resp) {
        // Grava no SharedPreferences
    }
}
