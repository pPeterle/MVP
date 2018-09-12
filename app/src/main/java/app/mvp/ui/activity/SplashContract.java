package app.mvp.ui.activity;

import app.mvp.session.Session;

public interface SplashContract {

    interface SplashView {

        void initView();

        void openHome();

        void openDashboard();

    }

    interface SplashPresenter {

        // Verificar se o usuário está logado
        void callSession(Session session);

        // Destroy a View para evitar Memory Leak
        void onDestroy();

    }
}
