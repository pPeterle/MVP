package app.mvp.ui.activity.register;

public class RegisterPresenter implements RegisterContract.RegisterPresenter {
    private RegisterContract.RegisterView view;

    RegisterPresenter(RegisterContract.RegisterView view) {
        this.view = view;

        initPresenter();
    }

    private void initPresenter() {
        view.initView();
    }

    @Override
    public void onBackPressed(int count) {
        if (count == 0) {
            view.openHome();
        } else {
            view.popBackStack();
        }
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }
}
