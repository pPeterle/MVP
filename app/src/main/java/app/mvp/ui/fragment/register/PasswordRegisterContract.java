package app.mvp.ui.fragment.register;

public interface PasswordRegisterContract {

    interface PasswordRegisterView {

        void passwordIsEmpty();

        void notIsPassword();

        void openPasswordConfirmRegister(String password);

    }

    interface PasswordRegisterPresenter {

        void callPasswordConfirmRegister(String password);

        void onDestroy();

    }

}
