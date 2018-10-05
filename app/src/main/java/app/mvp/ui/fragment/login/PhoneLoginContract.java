package app.mvp.ui.fragment.login;

public interface PhoneLoginContract {

    interface PhoneLoginView {

        void error(int error);

        void openPasswordLogin();

    }

    interface PhoneLoginPresenter {

        void callPasswordLogin(String phone);

        void onDestroy();

    }
}
