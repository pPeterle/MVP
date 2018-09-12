package app.mvp.ui.activity;

import app.mvp.session.Session;

public class SplashPresenter implements SplashContract.SplashPresenter {
    private SplashContract.SplashView view;

    SplashPresenter(SplashContract.SplashView view) {
        this.view = view;

        initPresenter();
    }

    private void initPresenter() {
        view.initView();
    }

    @Override
    public void callSession(Session session) {
        if (!session.isLoggedIn()) {
            view.openHome();
        } else {
            view.openDashboard();
        }
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }
}
