package app.mvp.ui.activity.login;

public class LoginPresenter implements LoginContract.LoginPresenter {
    private LoginContract.LoginView view;

    LoginPresenter(LoginContract.LoginView view) {
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
