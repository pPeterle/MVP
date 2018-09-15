package app.mvp.ui.fragment.login;

import app.mvp.model.User;

public interface PasswordLoginContract {

    interface PasswordLoginView {

        void passwordIsEmpty();

        void notIsPassword();

        void errorProcess();

        void openDashboard(User resp);

    }

    interface PasswordLoginPresenter {

        void callLoginProcess(User user);

        void onDestroy();

    }

}
