package app.mvp.ui.fragment.login;

import app.mvp.model.User;

public interface PasswordLoginContract {

    interface PasswordLoginView {

        void passwordIsEmpty();

        void notIsPassword();

        void onFailure();

        void errorLogin();

        void openDashboard();

    }

    interface PasswordLoginPresenter {

        void callLoginProcess(User user);

        void onPause();

        void onDestroy();

    }

}
