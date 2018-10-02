package app.mvp.ui.fragment.register;

import app.mvp.model.User;

public interface PasswordConfirmRegisterContract {

    interface PasswordConfirmRegisterModel {

        void request(User user);

        void onPause();

    }

    interface PasswordConfirmRegisterView {

        void passwordIsEmpty();

        void notIsSamePassword();

        void notIsChecked();

        void errorRequest();

        void errorRegister();

        void openDashboard();

    }

    interface PasswordConfirmRegisterPresenter {

        void callDashboard(User user);

        void errorRequest();

        void errorRegister();

        void openDashboard(User resp);

        void onPause();

        void onDestroy();

    }

}
