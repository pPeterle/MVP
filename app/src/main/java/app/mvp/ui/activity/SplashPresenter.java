package app.mvp.ui.activity;

import app.mvp.session.Session;

public class SplashPresenter implements SplashContract.SplashPresenter {
    private SplashContract.SplashView view;

    SplashPresenter(SplashContract.SplashView view) {
        this.view = view;

        initPresenter();
    }

    private void initPresenter() {
        if (view != null) {
            view.initView();
        }
    }

    @Override
    public void logged(Session session) {
        if (view != null) {
            if (!session.isLoggedIn()) {
                view.abreHome();
            } else {
                view.abreDashboard();
            }
        }
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }
}
