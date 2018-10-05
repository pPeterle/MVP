package app.mvp.ui.fragment.register;

public interface NicknameRegisterContract {

    interface NicknameRegisterView {

        void error(int error);

        void openEmailRegister(String nickname);

    }

    interface NicknameRegisterPresenter {

        void callEmailRegister(String nickname);

        void onDestroy();

    }

}
