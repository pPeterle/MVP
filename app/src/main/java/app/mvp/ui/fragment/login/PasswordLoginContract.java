package app.mvp.ui.fragment.login;

import app.mvp.model.User;

public interface PasswordLoginContract {

    interface PasswordLoginView {

        void passwordIsEmpty();

        void notIsPassword();

        void onFailure();

        void errorLogin();

        void openDashboard(User resp);

    }

    interface PasswordLoginPresenter {

        void callLoginProcess(User user);

        void onPause(User user);

        void onDestroy();

    }

}
