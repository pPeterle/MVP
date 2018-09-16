package app.mvp.ui.activity.home;

public class HomePresenter implements HomeContract.HomePresenter {
    private HomeContract.HomeView view;

    HomePresenter(HomeContract.HomeView view) {
        this.view = view;

        initPresenter();
    }

    private void initPresenter() {
        view.initView();
    }

    @Override
    public void callLogin() {
        view.openLogin();
    }

    @Override
    public void callRegisterClient() {
        view.openRegisterClient();
    }

    @Override
    public void callRegisterOwner() {
        view.openRegisterOwner();
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }
}
