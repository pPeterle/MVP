package app.mvp.ui.fragment.register;

public interface EmailRegisterContract {

    interface EmailRegisterView {

        void emailIsEmpty();

        void notIsEmail();

        void openPhoneRegister(String email);

    }

    interface EmailRegisterPresenter {

        void callPhoneRegister(String email);

        void onDestroy();

    }

}
