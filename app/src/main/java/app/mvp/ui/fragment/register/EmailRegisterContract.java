package app.mvp.ui.fragment.register;

public interface EmailRegisterContract {

    interface EmailRegisterView {

        void error(int error);

        void openPhoneRegister(String email);

    }

    interface EmailRegisterPresenter {

        void callPhoneRegister(String email);

        void onDestroy();

    }

}
