package app.mvp.ui.activity;

public class HomePresenter implements HomeContract.HomePresenter {
    private HomeContract.HomeView view;

    HomePresenter(HomeContract.HomeView view) {
        this.view = view;

        initPresenter();
    }

    private void initPresenter() {
        if (view != null) {
            view.initView();
        }
    }

    @Override
    public void login() {
        if (view != null) {
            view.abreLogin();
        }
    }

    @Override
    public void registerClient() {
        if (view != null) {
            view.abreRegisterClient();
        }
    }

    @Override
    public void registerOwner() {
        if (view != null) {
            view.abreRegisterOwner();
        }
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }
}
