package app.mvp.ui.fragment.login;

public interface PhoneLoginContract {

    interface PhoneLoginView {

        void abrePasswordFragment();

    }

    interface PhoneLoginPresenter {

        void passwordFragment();

        void onDestroy();

    }
}
