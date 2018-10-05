package app.mvp.ui.fragment.register;

public interface NameRegisterContract {

    interface NameRegisterView {

        void error(int error);

        void openNicknameRegister(String name);

    }

    interface NameRegisterPresenter {

        void callNicknameRegister(String name);

        void onDestroy();

    }

}
