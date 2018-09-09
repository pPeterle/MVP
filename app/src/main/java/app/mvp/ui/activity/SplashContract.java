package app.mvp.ui.activity;

import app.mvp.session.Session;

public interface SplashContract {

    interface SplashView {

        void abreHome();

        void abreDashboard();

    }

    interface SplashPresenter {

        // Verificar se o usuário está logado
        void logged(Session session);

        // Destroy a View para evitar Memory Leak
        void onDestroy();

    }
}
