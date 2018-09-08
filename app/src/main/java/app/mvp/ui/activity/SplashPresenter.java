package app.mvp.ui.activity;

import app.mvp.session.Session;

public class SplashPresenter implements SplashContract.SplashPresenter {
    private SplashContract.SplashView view;

    SplashPresenter(SplashContract.SplashView view) {
        this.view = view;
    }

    @Override
    public void logged(Session session) {

        if (!session.isLoggedIn()) {
            view.abreHome();
        } else {
            view.abreDashboard();
        }
    }

    @Override
    public void destroy() {
        this.view = null;
    }
}
