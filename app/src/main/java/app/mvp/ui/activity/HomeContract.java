package app.mvp.ui.activity;

public interface HomeContract {

    interface HomeView {

        void initView();

        void abreLogin();

        void abreRegisterClient();

        void abreRegisterOwner();

    }

    interface HomePresenter {

        void login();

        void registerClient();

        void registerOwner();

        // Destroy a View para evitar Memory Leak
        void onDestroy();

    }
}
