package app.mvp.ui.activity;

public interface HomeContract {

    interface HomeView {

        void abreLogin();

        void abreRegisterClient();

        void abreRegisterOwner();

    }

    interface HomePresenter {

        // Destroy a View para evitar Memory Leak
        void destroy();

    }
}
