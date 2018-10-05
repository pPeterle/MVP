package app.mvp.ui.fragment.register;

public interface PhoneRegisterContract {

    interface PhoneRegisterView {

        void error(int error);

        void openPasswordRegister(String phone);

    }

    interface PhoneRegisterPresenter {

        void callPasswordRegister(String phone);

        void onDestroy();

    }

}
