package app.mvp.ui.fragment.login;

public interface PhoneLoginContract {

    interface PhoneLoginView {

        void phoneIsEmpty();

        void notIsPhone();

        void abrePasswordFragment();

    }

    interface PhoneLoginPresenter {

        void passwordFragment(String phone);

        void onDestroy();

    }
}
