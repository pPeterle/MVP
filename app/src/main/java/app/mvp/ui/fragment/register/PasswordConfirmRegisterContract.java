package app.mvp.ui.fragment.register;

import app.mvp.model.User;

public interface PasswordConfirmRegisterContract {

    interface PasswordConfirmRegisterModel {

        void request(User user);

        void onPause();

    }

    interface PasswordConfirmRegisterView {

        void error(int error);

        void openDashboard();

    }

    interface PasswordConfirmRegisterPresenter {

        void callDashboard(User user);

        void error(int error);

        void openDashboard(User resp);

        void onPause();

        void onDestroy();

    }

}
