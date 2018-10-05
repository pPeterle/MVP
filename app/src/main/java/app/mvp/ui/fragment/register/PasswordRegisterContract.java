package app.mvp.ui.fragment.register;

public interface PasswordRegisterContract {

    interface PasswordRegisterView {

        void error(int error);

        void openPasswordConfirmRegister(String password);

    }

    interface PasswordRegisterPresenter {

        void callPasswordConfirmRegister(String password);

        void onDestroy();

    }

}
