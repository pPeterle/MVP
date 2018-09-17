package app.mvp.ui.fragment.register;

import android.widget.CheckBox;

import app.mvp.model.User;

public interface PasswordConfirmRegisterContract {

    interface PasswordConfirmRegisterView {

        void passwordIsEmpty();

        void notIsSamePassword();

        void notIsChecked();

        void onFailure();

        void errorRegister();

        void openDashboard(User user);

    }

    interface PasswordConfirmRegisterPresenter {

        void callDashboard(User user, CheckBox checkBox);

        void onPause();

        void onDestroy();

    }

}
