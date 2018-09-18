package app.mvp.ui.fragment.register;

public interface PhoneRegisterContract {

    interface PhoneRegisterView {

        void phoneIsEmpty();

        void notIsPhone();

        void openPasswordRegister(String phone);

    }

    interface PhoneRegisterPresenter {

        void callPasswordRegister(String phone);

        void onDestroy();

    }

}
