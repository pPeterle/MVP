package app.mvp.ui.activity.home;

public interface HomeContract {

    interface HomeView {

        void initView();

        void openLogin();

        void openRegisterClient();

        void openRegisterOwner();

    }

    interface HomePresenter {

        void callLogin();

        void callRegisterClient();

        void callRegisterOwner();

        // Destroy a View para evitar Memory Leak
        void onDestroy();

    }
}
