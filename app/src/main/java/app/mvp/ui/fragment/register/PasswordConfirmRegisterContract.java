package app.mvp.ui.fragment.register;

import app.mvp.model.User;

public interface PasswordConfirmRegisterContract {

    interface PasswordConfirmRegisterView {

        void passwordIsEmpty();

        void notIsSamePassword();

        void notIsChecked();

        void onFailure();

        void errorRegister();

        void openDashboard(User resp);

    }

    interface PasswordConfirmRegisterPresenter {

        void callDashboard(User user);

        void onPause();

        void onDestroy();

    }

}
