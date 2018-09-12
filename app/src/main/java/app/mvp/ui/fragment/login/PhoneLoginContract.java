package app.mvp.ui.fragment.login;

public interface PhoneLoginContract {

    interface PhoneLoginView {

        void phoneIsEmpty();

        void notIsPhone();

        void openPasswordLogin();

    }

    interface PhoneLoginPresenter {

        void callPasswordLogin(String phone);

        void onDestroy();

    }
}
