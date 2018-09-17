package app.mvp.ui.fragment.register;

public interface NicknameRegisterContract {

    interface NicknameRegisterView {

        void nicknameIsEmpty();

        void openEmailRegister(String nickname);

    }

    interface NicknameRegisterPresenter {

        void callEmailRegister(String nickname);

        void onDestroy();

    }

}
