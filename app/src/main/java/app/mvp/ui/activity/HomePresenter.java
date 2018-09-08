package app.mvp.ui.activity;

public class HomePresenter implements HomeContract.HomePresenter {
    private HomeContract.HomeView view;

    HomePresenter(HomeContract.HomeView view) {
        this.view = view;
    }

    @Override
    public void destroy() {
        this.view = null;
    }
}
